package ca.bradleydashjames.repspergame.feature_exercise_diary.presentation.add_exercise_set

sealed class AddExerciseSetEvent {
    data class ChangeGame(val gameName: String): AddExerciseSetEvent()
    data class ChangeExercise(val exerciseName: String): AddExerciseSetEvent()
    data class ChangeRepsOne(val reps: Int): AddExerciseSetEvent()
    data class ChangeRepsTwo(val reps: Int): AddExerciseSetEvent()
    data class ChangeRepsThree(val reps: Int): AddExerciseSetEvent()
    object SaveSet: AddExerciseSetEvent()
}

