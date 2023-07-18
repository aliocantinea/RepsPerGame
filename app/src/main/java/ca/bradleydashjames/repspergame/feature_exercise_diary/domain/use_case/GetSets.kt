package ca.bradleydashjames.repspergame.feature_exercise_diary.domain.use_case

import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.ExerciseSet
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.repository.ExerciseRepository
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.utility.ExerciseOrder
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.utility.OrderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/*
A use case is a component of the UI that has a single function. This makes it easy to reuse the component if it is needed
across multiple screens and reduces the amount of repeat code. It also allows for easier expansion when building out an app with more
features.
There should only be one function that is public and viewable from the outside, utility private functions can be included if needed
*/

class GetSets(
    //Using the interface Exercise Repository here is important so that it can be replaceable for testing
    private val repository: ExerciseRepository
) {
    operator fun invoke(
        /*
        I had thought this ordering should be done through SQL queries originally but changed it to follow
        Philipp Lackner's example here https://youtu.be/8YPXv7xKh2w?t=2102
        I can see that this would cause less queries than before, and though it is a local database if it
        was an API call that could become taxing for the system and the API calls
        */
        exerciseOrder: ExerciseOrder = ExerciseOrder.Date(OrderType.Descending)
    ): Flow<List<ExerciseSet>> {
        //This step is making me realize that I might need to rethink the fun call and data class fields
        return repository.getSets().map { exerciseSet ->
            when(exerciseOrder.orderType) {
                is OrderType.Ascending -> {
                    when(exerciseOrder) {
                        is ExerciseOrder.Date -> {
                            exerciseSet.sortedBy { it.timestamp }
                        }
                        is ExerciseOrder.Game -> {
                            exerciseSet.sortedBy { it.gameName }
                        }
                        is ExerciseOrder.Exercise -> {
                            exerciseSet.sortedBy { it.exerciseName }
                        }
                        /*
                        Since color is in a different data class a relationship data class needs to be called
                        this returns a different flow list, so needs to be it's own invocation ** but I am guessing tbh
                        is ExerciseOrder.Color -> {
                        exerciseSet.sortedBy { it
                        -> {

                        }
                        */
                    }
                }
                is OrderType.Descending -> {
                    when(exerciseOrder) {
                        is ExerciseOrder.Date -> {
                            exerciseSet.sortedByDescending { it.timestamp }
                        }
                        is ExerciseOrder.Game -> {
                            exerciseSet.sortedByDescending { it.gameName }
                        }
                        is ExerciseOrder.Exercise -> {
                            exerciseSet.sortedByDescending { it.exerciseName }
                        }
                        /*
                        is ExerciseOrder.Color -> {
                        exerciseSet.sortedBy { it
                        -> {

                        }
                        */
                    }

                }
            }
        }
    }
}