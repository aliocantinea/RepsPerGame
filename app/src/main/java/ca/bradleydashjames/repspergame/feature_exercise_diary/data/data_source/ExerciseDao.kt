package ca.bradleydashjames.repspergame.feature_exercise_diary.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.Exercise
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.ExerciseSet
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.Game
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.relations.SetsMatchingExercise
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.relations.SetsMatchingGame
import kotlinx.coroutines.flow.Flow

@Dao
interface ExercisesDao {
    /*
    Using suspend functions here because they will run in a coroutine and will wait to update things until they are finished
    This can be used for functions that don't return data and just do something such as update
    */
    // Change ExerciseSet to Upsert from Insert since a editable history would be good in case a mistake was logged
    @Upsert // Upsert handles insert(onConflict) by default
    suspend fun insertSet(exerciseSet: ExerciseSet)

    @Delete
    suspend fun deleteSet(exerciseSet: ExerciseSet)

    @Upsert
    suspend fun upsertExercise(exercise: Exercise)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)

    @Upsert
    suspend fun upsertGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    /*
    Suspend should not be used for the following because they return a flow
    source: https://youtu.be/8YPXv7xKh2w?t=1365
    */

    // Queries
    @Query("SELECT * FROM exerciseset")
    // Using a Flow here will notify when changes occur within the database it is querying
    fun getSets(): Flow<List<ExerciseSet>>

    @Query("SELECT * FROM exerciseset WHERE setId = :setId")
    suspend fun getSetById(setId: Long): ExerciseSet?

    @Query("SELECT * FROM game ORDER BY gameName ASC")
    fun getGames(): Flow<List<Game>>

    @Query("SELECT * FROM exercise ORDER BY exerciseName ASC")
    fun getExercises(): Flow<List<Exercise>>

    // One to Many Queries
    /*
    @Transaction is added so that it is threadsafe and finishes the query completely before changes,
    if one of the two queries was delayed and an update happened to one this could cause problems
    transaction makes sure they are both carried out one after another before anything else is performed
    */
    @Transaction
    @Query("SELECT * FROM exercise WHERE exerciseName = :exerciseName")
    fun getSetsMatchingExercise(exerciseName: String): Flow<List<SetsMatchingExercise>>
    // I think this really highlights the readability of flipping the relationship entity name to "Child Matching Parent"

    @Transaction
    @Query("SELECT * FROM game WHERE gameName = :gameName")
    fun getSetsMatchingGame(gameName: String): Flow<List<SetsMatchingGame>>
}

/*
Sources:
PL Coding: https://youtube.com/playlist?list=PLQkwcJG4YTCS3AD2C-yWtJUGTYMh5h3Zz
Define relationships between objects: https://developer.android.com/training/data-storage/room/relationships#approaches

Overall comments:
Not sure if this should be broken up into more than one Dao related to each database
*/