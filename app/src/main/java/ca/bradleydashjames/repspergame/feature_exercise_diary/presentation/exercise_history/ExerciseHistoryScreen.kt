package ca.bradleydashjames.repspergame.feature_exercise_diary.presentation.exercise_history

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.bradleydashjames.repspergame.R
import ca.bradleydashjames.repspergame.feature_exercise_diary.presentation.exercise_history.components.ExerciseItem
import ca.bradleydashjames.repspergame.feature_exercise_diary.presentation.exercise_history.components.OrderSelection
import kotlinx.coroutines.launch

@Composable
fun ExerciseHistoryScreen(
    navController: NavController,
    // not sure how to include savedStateHandle from new Dagger-Hilt view model into the screen
    viewModel: ExerciseHistoryViewModel
) {
    val state = viewModel.state.value
    // Snackbar has changed copied reference from https://m3.material.io/components/snackbar/overview
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val deleted = stringResource(R.string.set_deleted)
    val undo = stringResource(R.string.undo)

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        // seems like all components are placed in content = { } to apply innerPadding from Scaffold
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.exercise_history),
                    style = MaterialTheme.typography.titleMedium
                )
                IconButton(
                    onClick = {
                        viewModel.onEvent(ExerciseHistoryEvent.ToggleOrderSelection)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = stringResource(R.string.sort_menu)
                    )
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSelection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    exerciseOrder = state.exerciseOrder,
                    onOrderChange = {
                        viewModel.onEvent(ExerciseHistoryEvent.Order(it))
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                // have to make sure you select this overload with the List<T> of items so you can pass
                // state items
                items(state.exerciseSets) { exerciseSet ->
                    ExerciseItem(
                        exerciseSet = exerciseSet,
                        modifier = Modifier
                            .fillMaxWidth(),
                            /*
                            clicking item in lazy row toggles visibility in state
                            only worry is this will be for ALL items since it is a single variable
                            might want to pass exerciseItemInfoShowing = false and then clickable
                            to call a function to change that on the ExerciseItem val

                            moved clickable to the exercise item component with a local state val
                            to keep track of individual items being expanded
                            https://developer.android.com/jetpack/compose/state
                            */
                        onDeleteClick = {
                            viewModel.onEvent(ExerciseHistoryEvent.DeleteExerciseSet(it))
                            // not sure if I can launch the snackbar from outside of the scaffold constructor
                            scope.launch {
                                val result = snackbarHostState.showSnackbar(
                                    // because this is run in a coroutine values have to be passed in
                                    // not created on launch
                                    message = deleted,
                                    actionLabel = undo
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(ExerciseHistoryEvent.RestoreExerciseSet)
                                }
                            }
                        },
                        //onEditCLick = {
                        //  TODO take it(ExerciseSet) and pass it to an edit screen when implemented
                        // }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}
