package ca.bradleydashjames.repspergame.feature_exercise_diary.domain.use_case

import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.Exercise

/*
When there are more features added in the future it would be a lot to inject each fo the use cases into a different view-model
to make this more compact this file is used to keep track of all use cases in the feature so that it can be injected all at once
*/

data class ExerciseDiaryUseCases(
    val getSets: GetSets,
    val addSet: AddSet,
    val deleteSet: DeleteSet,
    val getGame: GetGame,
    val getExercise: GetExercise
)
