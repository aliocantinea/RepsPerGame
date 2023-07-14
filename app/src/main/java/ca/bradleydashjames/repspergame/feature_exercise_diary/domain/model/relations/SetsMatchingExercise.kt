package ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.Exercise
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.ExerciseSet

/*
Just like with SetsMatchingGame I find this relationship confusing, conventional naming strategy is ExerciseWithSets (Parent With Children)
To make this easier to read I decided to do to opposite way as it returns a list of the child entities
*/
data class SetsMatchingExercise(
    @Embedded val exercise: Exercise,
    @Relation(
        parentColumn = "exerciseName",
        entityColumn = "exerciseName"
    )
    val exerciseSets: List<ExerciseSet>
)

/*
Sources:
PL Coding: https://www.youtube.com/watch?v=K8yKH5Ak84E
Associative Entities / Cross-reference table https://developer.android.com/training/data-storage/room/relationships#one-to-many
*/