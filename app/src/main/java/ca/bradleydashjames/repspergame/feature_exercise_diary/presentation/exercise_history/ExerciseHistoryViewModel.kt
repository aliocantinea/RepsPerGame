package ca.bradleydashjames.repspergame.feature_exercise_diary.presentation.exercise_history

import android.view.AbsSavedState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.ExerciseSet
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.use_case.ExerciseDiaryUseCases
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.utility.ExerciseOrder
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.utility.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExerciseHistoryViewModel @Inject constructor(
    /*
    Ran into so many issues getting this to work, the reference video I am following uses an older
    version of dagger-hilt and the documentation for it on android dev is confusing since I am still
    trying to understand how all the pieces fit together. In the end an out of date dependency in
    the gradle file was the issues but I still have to modify how saveStateHandle interacts with
    the app and the di
    */
    /*
    https://developersbreach.com/savedstatehandle-viewmodel-android/
    savedStateHandle seems to be a way to pass saved variables from one screen to another.
    if this is the case then why would I use a state data class in the presentation package?
    */
    //savedStateHandle: SavedStateHandle,
    private val exerciseDiaryUseCases: ExerciseDiaryUseCases
): ViewModel() {

    /*
    This seems to act the same as the savedStateHandle from above
    passing a saved value from one component to another
    */
    private val _state = mutableStateOf(ExerciseHistoryState())
    val state: State<ExerciseHistoryState> = _state


    // to keep track of the last deleted note this var is set when deleting and passed on restore snack bar
    private var recentlyDeletedExerciseSet: ExerciseSet? = null

    // to keep track of the current flow of exerciseSets showing
    private var getSetsJob: Job? = null

    // when the app first loads this initializes the history loading into a flow list to display
    init {
        getSets(ExerciseOrder.Date(OrderType.Descending))
    }

    /*
    So far I can see why it is useful to break out the business logic to the domain layer since if that
    was included in the view model this one file alone would be huge! I am finding it hard to keep track of
    where items are, especially when I get errors because I imported the wrong item, but I think with time
    this will become more second nature. It is always challenging at first
    */

    fun onEvent(event: ExerciseHistoryEvent) {
        when(event) {
            is ExerciseHistoryEvent.Order -> {
                /*
                this checks that the radio selection is the same as previous, if so no change happens
                class is compared because without it the if will compare references, which are different
                if the order classes were data classes the comparison would work but it would have to change
                the structure. The orderType is an object so it is comparable directly
                */
                if(state.value.exerciseOrder::class == event.exerciseOrder::class &&
                        state.value.exerciseOrder.orderType == event.exerciseOrder.orderType) {
                    return
                }
                getSets(event.exerciseOrder)

            }
            is ExerciseHistoryEvent.DeleteExerciseSet -> {
                viewModelScope.launch {
                    // this is a operator function it overrides teh invoke operator and can call it like a fun
                    exerciseDiaryUseCases.deleteSet(event.exerciseSet)
                    recentlyDeletedExerciseSet = event.exerciseSet
                }
            }
            is ExerciseHistoryEvent.RestoreExerciseSet -> {
                // another option is to get an instance to the deleted note and then pass that to the add note in the UI or
                viewModelScope.launch {
                    // since this is a nullable var it needs to have a fallback for it not being there
                    // which is this case is just to return to the same state when it launched
                    exerciseDiaryUseCases.addSet(recentlyDeletedExerciseSet ?: return@launch)
                    // this is to ensure that you don't reinsert the same exerciseSet over and over
                    recentlyDeletedExerciseSet = null
                }
            }
            is ExerciseHistoryEvent.ToggleOrderSelection -> {
                _state.value = state.value.copy(
                    // takes a copy of the current state and makes it false with ! (or not) and sends it back to toggle
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )
            }
            /*
            I am not sure if this is the best way to pass in the toggle, I feel like state has to
            be per item or all of the flow list items will toggle on at one time. I need to maybe
            put a toggle true/false into the actual ExerciseItem component but I don't want the
            business logic to be there so that it follows clean architecture principals

            decided to include the remember state variable in the composable instead of here.
            not sure if this really fits with teh clean architecture principals but it has to be a
            local value or all items will change together when state is changed
            https://developer.android.com/jetpack/compose/state
            */
            /*
            is ExerciseHistoryEvent.ToggleExerciseItemInfo -> {
            // should this use savedStateHandle and pass in the exerciseSet Id as the key?
            _state.value = state.value.copy(
            isExerciseItemInfoVisible = !state.value.isExerciseItemInfoVisible
            )
            }
            */
        }
    }

    private fun getSets(exerciseOrder: ExerciseOrder) {
        // This will cancel the current flow list of exerciseSets
        // and then reassign it to a new job calling for a new flow list
        getSetsJob?.cancel()
        getSetsJob = exerciseDiaryUseCases.getSets(exerciseOrder)
            .onEach { exerciseSets ->
                _state.value = state.value.copy(
                    exerciseSets = exerciseSets,
                    exerciseOrder = exerciseOrder

                )
            }. launchIn(viewModelScope)
    }
}