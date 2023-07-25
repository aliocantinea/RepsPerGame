package ca.bradleydashjames.repspergame.feature_exercise_diary.presentation.exercise_history.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ca.bradleydashjames.repspergame.R
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.ExerciseSet


@Composable
fun ExerciseItemInfo(
    exerciseSet: ExerciseSet,
    modifier: Modifier = Modifier,
    onDeleteClick: (ExerciseSet) -> Unit,
    //onEditClick: (ExerciseSet) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(
            onClick = {
                // needs to be passed up so that the scaffold in the screen composable can action
                // and show snackbar to allow for restore
                onDeleteClick(exerciseSet)
            }
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(R.string.delete_set)
            )
        }
        Text(text = exerciseSet.reps.toString().plus(R.string.reps_of).plus(exerciseSet.exerciseName))
        /*
            IconButton(
                onClick = {
                onEditClick(exerciseSet)
                }
            ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = stringResource(R.string.edit_set)
            )
         */
    }
}
