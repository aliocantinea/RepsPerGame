package ca.bradleydashjames.repspergame

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalTime

// Entity declares to room that this is a database table
@Entity
data class Set(
    @PrimaryKey(autoGenerate = true) val setId: Long = 0, // could also make it val id: Int? = null, which would make it nullable
    val reps: Int,
    // LocalDateTime https://www.youtube.com/watch?v=gzHy6wKAJh8 added coreLibraryDesugaring to reduce SDK LocalDateTime needs
    val date: LocalDate = LocalDate.now(),
    val time: LocalTime = LocalTime.now()
)

@Entity
data class Game(
    @PrimaryKey(autoGenerate = true) val gameId: Int = 0,
    val gameName: String,
    @Embedded
    val valueNames: Values
)

// Using Embedded to make this data class also contain the names of the value fields but cleaner https://developer.android.com/training/data-storage/room/relationships#nested-objects
data class Values(
    val valueOne: String,
    val valueTwo: String,
    val valueThree: String
)

@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true) val exerciseId: Int = 0,
    val exerciseName: String,
    val icon: String // using string as I *think a filepath to an icon will be used
)

// Associative Entities / Cross-reference table https://developer.android.com/training/data-storage/room/relationships#many-to-many
@Entity(primaryKeys = ["setId", "exerciseId"])
data class SetExerciseCrossRef(
    val setId: Long,
    val exerciseId: Int
)

data class SetsWithExercises(
    @Embedded val set: Set,
    @Relation(
        parentColumn = "setId",
        entityColumn = "exerciseId",
        associateBy = Junction(SetExerciseCrossRef::class)
    )
    val exercises: List<Exercise>
)

data class ExercisesWithSets(
    @Embedded val exercise: Exercise,
    @Relation(
        parentColumn = "exerciseId",
        entityColumn = "setId",
        associateBy = Junction(SetExerciseCrossRef::class)
    )
    val set: List<Set>
)

@Entity(primaryKeys = ["setId", "gameId"])
data class SetGameCrossRef(
    val setId: Long,
    val gameId: Int
)

data class SetsWithGames(
    @Embedded val set: Set,
    @Relation(
        parentColumn = "setId",
        entityColumn = "gameId",
        associateBy = Junction(SetGameCrossRef::class)
    )
    val games: List<Game>
)

data class GamesWithSets(
    @Embedded val game: Game,
    @Relation(
        parentColumn = "gameId",
        entityColumn = "setId",
        associateBy = Junction(SetGameCrossRef::class)
    )
    val set: List<Set>
)

/*
Sources:
PL Coding: https://www.youtube.com/watch?v=bOd3wO0uFr8
Define relationships between objects: https://developer.android.com/training/data-storage/room/relationships#approaches

Overall comments:
not really sure if I should have broken this into multiple databases for possible feature changes
*/

