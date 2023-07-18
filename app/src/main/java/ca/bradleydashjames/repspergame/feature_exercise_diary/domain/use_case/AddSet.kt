package ca.bradleydashjames.repspergame.feature_exercise_diary.domain.use_case

import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.ExerciseSet
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.InvalidExerciseSetException
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.repository.ExerciseRepository

class AddSet(
    private val repository: ExerciseRepository
) {
    @Throws(InvalidExerciseSetException::class)
    suspend operator fun invoke(exerciseSet: ExerciseSet) {
        // you want to make sure that you validate the fields before inserting
        if(exerciseSet.gameName.isBlank()) {
            throw InvalidExerciseSetException("Please select game played.")
        }
        if(exerciseSet.exerciseName.isBlank()) {
            throw InvalidExerciseSetException("Please add an exercise.")
        }
        if(exerciseSet.reps < 1) {
            throw InvalidExerciseSetException("Please complete more reps.")
        }
        repository.insertSet(exerciseSet)
    }
}