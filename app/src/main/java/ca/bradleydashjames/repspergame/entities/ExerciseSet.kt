package ca.bradleydashjames.repspergame.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

// Entity declares to room that this is a database table
@Entity
data class ExerciseSet(
    @PrimaryKey(autoGenerate = true) val setId: Long = 0, // could also make it val id: Int? = null, which would make it nullable
    val reps: Int,
    // LocalDateTime https://www.youtube.com/watch?v=gzHy6wKAJh8 added coreLibraryDesugaring to reduce SDK LocalDateTime needs
    val date: LocalDate = LocalDate.now(),
    val time: LocalTime = LocalTime.now(),
    val gameName: String,
    val exerciseName: String
)

/*
Sources:
PL Coding: https://www.youtube.com/watch?v=bOd3wO0uFr8
Define relationships between objects: https://developer.android.com/training/data-storage/room/relationships#approaches

Overall comments:
*/