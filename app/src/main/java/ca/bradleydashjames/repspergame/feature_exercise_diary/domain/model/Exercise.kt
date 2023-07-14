package ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exercise(
    // Changed Primary Key to exerciseName: String from exerciseId: Int, since a string will work with Upsert in the Dao
    @PrimaryKey(autoGenerate = false)
    val exerciseName: String,
    val icon: String // using string as I *think a filepath to an icon will be used
)

/*
Sources:
PL Coding: https://www.youtube.com/watch?v=bOd3wO0uFr8
Define relationships between objects: https://developer.android.com/training/data-storage/room/relationships#approaches

Overall comments:
*/