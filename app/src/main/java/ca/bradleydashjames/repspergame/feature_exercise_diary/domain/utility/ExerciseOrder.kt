package ca.bradleydashjames.repspergame.feature_exercise_diary.domain.utility

sealed class ExerciseOrder(
    val orderType: OrderType
) {
    class Date(orderType: OrderType): ExerciseOrder(orderType)
    class Game(orderType: OrderType): ExerciseOrder(orderType)
    class Exercise(orderType: OrderType): ExerciseOrder(orderType)
    // Not sure how to implement this since it is a related data class that contains the color
    //class Color(orderType: OrderType): ExerciseOrder(orderType)

}

// Source https://youtu.be/8YPXv7xKh2w?t=2102