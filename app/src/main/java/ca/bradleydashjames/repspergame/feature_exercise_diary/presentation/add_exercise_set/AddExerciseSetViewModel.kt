package ca.bradleydashjames.repspergame.feature_exercise_diary.presentation.add_exercise_set

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.Exercise
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.ExerciseSet
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.use_case.ExerciseDiaryUseCases
import ca.bradleydashjames.repspergame.feature_exercise_diary.presentation.exercise_history.ExerciseHistoryState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

@HiltViewModel
class AddExerciseSetViewModel @Inject constructor(
    private val exerciseDiaryUseCases: ExerciseDiaryUseCases
) : ViewModel() {
    /*
    values are kept in separate state variables so that it doesn't redraw when a change occurs
    which would happen if everything was kept in a single state class like in ExerciseHistoryState
    A wrapper class is created since all three games values will have an Int and String per game
    */
    private val _gameValueOne = mutableStateOf(GameValuesState())
    val gameValueOne: State<GameValuesState> = _gameValueOne

    private val _gameValueTwo = mutableStateOf(GameValuesState())
    val gameValueTwo: State<GameValuesState> = _gameValueTwo

    private val _gameValueThree = mutableStateOf(GameValuesState())
    val gameValueThree: State<GameValuesState> = _gameValueThree

    private val _gameRatio = mutableIntStateOf(0)
    val gameRatio: State<Int> = _gameRatio

    private val _gameName = mutableStateOf(String())
    val gameName: State<String> = _gameName

    private val _gameIcon = mutableStateOf(String())
    val gameIcon: State<String> = _gameIcon

    private val _exerciseColor = mutableIntStateOf(Exercise.exerciseColors.random().toArgb())
    val exerciseColor: State<Int> = _exerciseColor

    private val _exerciseName = mutableStateOf(String())
    val exerciseName: State<String> = _exerciseName

    fun onEvent(event: AddExerciseSetEvent) {
        when(event) {
            is AddExerciseSetEvent.ChangeExercise -> {
                TODO()
            }
            is AddExerciseSetEvent.ChangeGame -> {
                //TODO Change _gameValueOne.text x3, _gameName and _gameIcon
            }
            is AddExerciseSetEvent.ChangeRepsOne -> {
                _gameValueOne.value = gameValueOne.value.copy(
                    value = event.reps
                )
            }
            is AddExerciseSetEvent.ChangeRepsTwo -> {
                _gameValueTwo.value = gameValueTwo.value.copy(
                    value = event.reps
                )
            }
            is AddExerciseSetEvent.ChangeRepsThree -> {
                _gameValueThree.value = gameValueThree.value.copy(
                    value = event.reps
                )
            }
            is AddExerciseSetEvent.SaveSet -> {
                TODO()
            }
        }
    }

    /*
    this eventFlow state is use to keep track of events that should only happen one time, such as
    showing a snackbar, because you don't want to rotate the screen and have the snackbar show again
    I am wondering if SavedStateHandle now includes this??
    */
    private val _eventFlow = MutableSharedFlow<UiEvent>()
    // this requires a public version as well
    val eventFlow = _eventFlow.asSharedFlow()

    sealed class UiEvent {
        data class ShowSnackbar(val message: String): UiEvent()
        object SaveExerciseSet: UiEvent()
    }

}