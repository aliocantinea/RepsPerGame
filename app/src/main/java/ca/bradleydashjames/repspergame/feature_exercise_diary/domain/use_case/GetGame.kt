package ca.bradleydashjames.repspergame.feature_exercise_diary.domain.use_case

import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.Game
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.repository.ExerciseRepository

class GetGame (
    private val repository: ExerciseRepository
) {
    suspend operator fun invoke (game: Game): Game? {
        return repository.getGameByName(gameName = game.gameName)
    }
}