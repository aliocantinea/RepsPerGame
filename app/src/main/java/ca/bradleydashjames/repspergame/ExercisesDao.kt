package ca.bradleydashjames.repspergame

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ExercisesDao {
    /*
    Using suspend functions here because they will run in a coroutine and will wait to update things until they are finished
    This can be used for functions that don't return data and just do something
    */
    @Insert
    suspend fun insertSet(set: Set)

    @Delete
    suspend fun deleteSet(set: Set)

    @Upsert
    suspend fun upsertExercise(exercise: Exercise)

    @Delete
    suspend fun deleteExercise(exercise: Exercise)

    @Upsert
    suspend fun upsertGame(game: Game)

    @Delete
    suspend fun deleteGame(game: Game)

    // Queries
    @Query("SELECT * FROM `set` ORDER BY date, time ASC")
    // Using a Flow here will notify when changes occur within the database it is querying
    fun getHistoryOrderedByDateTime(): Flow<List<Set>>

    @Transaction
    @Query("SELECT * FROM `set` ORDER BY date, time ASC")
    fun getSetsWithExercises(): Flow<List<SetsWithExercises>>
    fun getSetsWithGames(): Flow<List<SetsWithGames>>

    @Transaction
    @Query("SELECT * FROM exercise")
    fun getExercisesWithSetss(): Flow<List<ExercisesWithSets>>

    @Transaction
    @Query("SELECT * FROM game")
    fun getGamesWithSets(): Flow<List<GamesWithSets>>
}

/*
Sources:
PL Coding: https://www.youtube.com/watch?v=bOd3wO0uFr8
Define relationships between objects: https://developer.android.com/training/data-storage/room/relationships#approaches

Overall comments:
Not sure if this should be broken up into more than one Dao related to each database
*/