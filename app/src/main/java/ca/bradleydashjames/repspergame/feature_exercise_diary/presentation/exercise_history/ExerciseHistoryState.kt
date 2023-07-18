package ca.bradleydashjames.repspergame.feature_exercise_diary.presentation.exercise_history

import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.ExerciseSet
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.utility.ExerciseOrder
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.utility.OrderType

data class ExerciseHistoryState(
    val exerciseSets: List<ExerciseSet> = emptyList(),
    val exerciseOrder: ExerciseOrder = ExerciseOrder.Date(OrderType.Descending),
    val isOrderSectionVisible: Boolean = false
)
