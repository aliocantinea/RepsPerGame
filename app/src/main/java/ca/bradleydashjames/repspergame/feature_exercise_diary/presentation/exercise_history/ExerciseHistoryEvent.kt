package ca.bradleydashjames.repspergame.feature_exercise_diary.presentation.exercise_history

import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.ExerciseSet
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.utility.ExerciseOrder

sealed class ExerciseHistoryEvent {
    data class Order(val exerciseOrder: ExerciseOrder): ExerciseHistoryEvent()
    data class DeleteExerciseSet(val exerciseSet: ExerciseSet): ExerciseHistoryEvent()
    object RestoreExerciseSet: ExerciseHistoryEvent()
    object ToggleOrderSelection: ExerciseHistoryEvent()

}
