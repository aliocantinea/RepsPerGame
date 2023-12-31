package ca.bradleydashjames.repspergame.feature_exercise_diary.domain.use_case

import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.ExerciseSet
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.repository.ExerciseRepository

class DeleteSet(
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke(exerciseSet: ExerciseSet) {
        repository.deleteSet(exerciseSet)
    }
}