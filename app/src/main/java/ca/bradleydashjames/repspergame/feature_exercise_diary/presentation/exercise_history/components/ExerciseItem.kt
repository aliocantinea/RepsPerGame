package ca.bradleydashjames.repspergame.feature_exercise_diary.presentation.exercise_history.components

import android.service.autofill.OnClickAction
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.RememberObserver
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.Exercise
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.ExerciseSet
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.Game
import ca.bradleydashjames.repspergame.feature_exercise_diary.presentation.exercise_history.ExerciseHistoryEvent
import ca.bradleydashjames.repspergame.feature_exercise_diary.presentation.exercise_history.ExerciseHistoryViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseItem(
    exerciseSet: ExerciseSet,
    //TODO add game: Game,
    modifier: Modifier = Modifier,
    onDeleteClick: (ExerciseSet) -> Unit,
    //OnEditClick: (ExerciseSet) -> Unit,
) {
    val infoShowing = remember { mutableStateOf(false)}

    Card(
        // Didn't understand the value requirements at first, now I see you need to use the method to set it
        // https://stackoverflow.com/questions/74775494/how-to-set-card-elevation-in-jetpack-compose-with-material3
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .clickable {
                infoShowing.value = !infoShowing.value
            }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,

        ) {
            //TODO add icon and progress bar for reps done
            //use exerciseSet.gameName as the search to return game.icon
            //do the same for exercise to get exercise.color

        }
        Spacer(modifier = Modifier.width(8.dp))
        //https://developer.android.com/jetpack/compose/animation/composables-modifiers#animatedvisibility
        AnimatedVisibility(
            visible = infoShowing.value,
            enter = fadeIn() + slideInVertically(),
            exit = fadeOut() + slideOutVertically()
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            ExerciseItemInfo(
                exerciseSet = exerciseSet,
                onDeleteClick = {
                    onDeleteClick(it)
                }
                // onEditClick = {
                //  onEditClick(it)
                // }
            )
        }

    }
}