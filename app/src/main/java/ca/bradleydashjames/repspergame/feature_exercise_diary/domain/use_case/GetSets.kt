package ca.bradleydashjames.repspergame.feature_exercise_diary.domain.use_case

import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.ExerciseSet
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.repository.ExerciseRepository
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
    operator fun invoke(): Flow<List<ExerciseSet>> {
        //This step is making me realize that I might need to rethink the fun call and data class fields
        return repository.getSets().map { exerciseSet ->
            //TODO(add sort sealed class for datetime, game and exercise https://youtu.be/8YPXv7xKh2w?t=2086)
            exerciseSet.sortedBy { it.date }
        }
    }
}