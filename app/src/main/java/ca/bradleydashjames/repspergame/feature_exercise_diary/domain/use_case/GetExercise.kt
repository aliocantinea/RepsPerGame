package ca.bradleydashjames.repspergame.feature_exercise_diary.domain.use_case

import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.Exercise
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.repository.ExerciseRepository

class GetExercise (
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke (exercise: Exercise): Exercise? {
        return repository.getExerciseByName(exerciseName = exercise.exerciseName)
    }
}