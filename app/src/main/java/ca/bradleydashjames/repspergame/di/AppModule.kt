package ca.bradleydashjames.repspergame.di

import android.app.Application
import androidx.room.Room
import ca.bradleydashjames.repspergame.feature_exercise_diary.data.data_source.ExerciseDatabase
import ca.bradleydashjames.repspergame.feature_exercise_diary.data.repository.ExerciseRepositoryImplementation
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.repository.ExerciseRepository
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.use_case.AddSet
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.use_case.DeleteSet
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.use_case.ExerciseDiaryUseCases
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.use_case.GetSets
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideExerciseDatabase(app: Application): ExerciseDatabase {
        return Room.databaseBuilder(
            app,
            ExerciseDatabase::class.java,
            ExerciseDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideExerciseRepository(db: ExerciseDatabase): ExerciseRepository {
        return ExerciseRepositoryImplementation(db.exerciseDao)
    }
    /*
    If you wanted to test this is where the change would be made, you would replace the repository interface
    with a new module, that would return a fake implementation. This makes it super easy since nothing else
    would have to be changed in anything else including the view-model
    Source: https://youtu.be/8YPXv7xKh2w?t=2775
    */

    @Provides
    @Singleton
    fun provideExerciseDiaryUseCases(repository: ExerciseRepository): ExerciseDiaryUseCases {
        return ExerciseDiaryUseCases(
            //This is what injects the use cases into the view-model
            getSets = GetSets(repository),
            addSet = AddSet(repository),
            deleteSet = DeleteSet(repository)
        )
    }
}