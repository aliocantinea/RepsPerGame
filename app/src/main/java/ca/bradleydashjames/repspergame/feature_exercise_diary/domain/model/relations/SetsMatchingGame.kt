package ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.relations

import androidx.room.Embedded
import androidx.room.Relation
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.ExerciseSet
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.Game

/*
I find this relationship confusing because the conventional naming strategy is GameWithSets (Parent With Children)
To make this easier to read I decided to do to opposite way as it returns a list of the child entities
*/
data class SetsMatchingGame(
    @Embedded val game: Game,
    @Relation(
        parentColumn = "gameName",
        entityColumn = "gameName"
    )
    val exerciseSets: List<ExerciseSet>
)

/*
Sources:
PL Coding: https://www.youtube.com/watch?v=K8yKH5Ak84E
Associative Entities / Cross-reference table https://developer.android.com/training/data-storage/room/relationships#one-to-many
*/