package ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Game(
    // Originally I had Primary key auto generating as Int but why not use user entered String and gameName since it will work with Upsert
    @PrimaryKey(autoGenerate = false)
    val gameName: String,
    val icon: String,
    @Embedded
    val valueNames: Values
)

// Using Embedded to make this data class also contain the names of the value fields but cleaner https://developer.android.com/training/data-storage/room/relationships#nested-objects
data class Values(
    val valueOne: String,
    val valueTwo: String,
    val valueThree: String
)

/*
Sources:
PL Coding: https://www.youtube.com/watch?v=bOd3wO0uFr8
Define relationships between objects: https://developer.android.com/training/data-storage/room/relationships#approaches

Overall comments:
*/