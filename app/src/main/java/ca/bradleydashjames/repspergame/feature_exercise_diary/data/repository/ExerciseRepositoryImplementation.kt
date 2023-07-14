package ca.bradleydashjames.repspergame.feature_exercise_diary.data.repository

import ca.bradleydashjames.repspergame.feature_exercise_diary.data.data_source.ExercisesDao
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.Exercise
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.ExerciseSet
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.Game
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.relations.SetsMatchingExercise
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.relations.SetsMatchingGame
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.repository.ExerciseRepository
import kotlinx.coroutines.flow.Flow

class ExerciseRepositoryImplementation(
    private val dao: ExercisesDao
) : ExerciseRepository {
    override suspend fun upsertSet(exerciseSet: ExerciseSet) {
        dao.upsertSet(exerciseSet)
    }

    override suspend fun deleteSet(exerciseSet: ExerciseSet) {
        dao.deleteSet(exerciseSet)
    }

    override suspend fun upsertExercise(exercise: Exercise) {
        dao.upsertExercise(exercise)
    }

    override suspend fun deleteExercise(exercise: Exercise) {
        dao.deleteExercise(exercise)
    }

    override suspend fun upsertGame(game: Game) {
        dao.upsertGame(game)
    }

    override suspend fun deleteGame(game: Game) {
        dao.deleteGame(game)
    }

    override fun getSets(): Flow<List<ExerciseSet>> {
        return dao.getSets()
    }

    override suspend fun getSetById(setId: Long): ExerciseSet? {
        return dao.getSetById(setId)
    }

    override fun getGames(): Flow<List<Game>> {
        return dao.getGames()
    }

    override fun getExercises(): Flow<List<Exercise>> {
        return dao.getExercises()
    }

    override fun getSetsMatchingExercise(exerciseName: String): Flow<List<SetsMatchingExercise>> {
        return dao.getSetsMatchingExercise(exerciseName)
    }

    override fun getSetsMatchingGame(gameName: String): Flow<List<SetsMatchingGame>> {
        return dao.getSetsMatchingGame(gameName)
    }
}