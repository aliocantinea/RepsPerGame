package ca.bradleydashjames.repspergame.feature_exercise_diary.presentation.exercise_history.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ca.bradleydashjames.repspergame.R
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.utility.ExerciseOrder
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.utility.OrderType

@Composable
fun OrderSelection(
    modifier: Modifier = Modifier,
    exerciseOrder: ExerciseOrder = ExerciseOrder.Date(OrderType.Descending),
    onOrderChange: (ExerciseOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = stringResource(R.string.date),
                selected = exerciseOrder is ExerciseOrder.Date,
                /*
                when following along with a video the orderType is copied in from the exerciseOrder.orderType
                for some reason I don't understand this gives an error
                I will set it to OrderType.Descending since this is the default anyways

                The reason is I was passing the wrong type into the constructor of the ExerciseOrder sealed class
                */
                onSelect = { onOrderChange(ExerciseOrder.Date(exerciseOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = stringResource(R.string.game),
                selected = exerciseOrder is ExerciseOrder.Game,
                onSelect = { onOrderChange(ExerciseOrder.Game(exerciseOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = stringResource(R.string.exercise),
                selected = exerciseOrder is ExerciseOrder.Exercise,
                onSelect = { onOrderChange(ExerciseOrder.Exercise(exerciseOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = stringResource(R.string.ascending),
                selected = exerciseOrder.orderType is OrderType.Ascending,
                // to make this easier to change, a copy function is added to the OrderSelection class
                onSelect = { onOrderChange(exerciseOrder.copy(OrderType.Ascending)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = stringResource(R.string.descending),
                selected = exerciseOrder is ExerciseOrder.Exercise,
                onSelect = { onOrderChange(exerciseOrder.copy(OrderType.Descending)) }
            )
        }
    }
}