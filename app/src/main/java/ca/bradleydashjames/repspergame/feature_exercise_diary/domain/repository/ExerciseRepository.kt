package ca.bradleydashjames.repspergame.feature_exercise_diary.domain.repository

import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.Exercise
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.ExerciseSet
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.Game
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.relations.SetsMatchingExercise
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.relations.SetsMatchingGame
import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    /*
    Source: https://youtu.be/8YPXv7xKh2w?t=1514
    What is a repository?
    This layer interfaces with the data-source and decides what and where the data received is forward to
    The logic of which source, such as an API call or cached data, should be forwarded to the use-cases
    Use-cases don't care where it gets it from just that it gets the data
    Checking for errors in the data given via an API call would also done here
    An interface is also used here so that fake versions of it can be created for testing so that an API call isn't actioned
    */

    suspend fun insertSet(exerciseSet: ExerciseSet)

    suspend fun deleteSet(exerciseSet: ExerciseSet)

    suspend fun upsertExercise(exercise: Exercise)

    suspend fun deleteExercise(exercise: Exercise)

    suspend fun upsertGame(game: Game)

    suspend fun deleteGame(game: Game)

    fun getSets(): Flow<List<ExerciseSet>>

    suspend fun getSetById(setId: Long): ExerciseSet?

    fun getGames(): Flow<List<Game>>

    fun getExercises(): Flow<List<Exercise>>

    fun getSetsMatchingExercise(exerciseName: String): Flow<List<SetsMatchingExercise>>

    fun getSetsMatchingGame(gameName: String): Flow<List<SetsMatchingGame>>
}