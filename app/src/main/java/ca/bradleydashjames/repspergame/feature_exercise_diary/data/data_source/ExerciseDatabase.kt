package ca.bradleydashjames.repspergame.feature_exercise_diary.data.data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.Exercise
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.ExerciseSet
import ca.bradleydashjames.repspergame.feature_exercise_diary.domain.model.Game

// Source: https://youtu.be/iTdzBM1zErA
@Database(
    entities = [
        ExerciseSet::class,
        Game::class,
        Exercise::class
    ],
    /*
    This version is used for room to know there is an update to the database
    each time you make a change to the schema of a database you need to increase this version as well
    */
    version = 1
)
abstract class ExerciseDatabase : RoomDatabase() {

    abstract val exerciseDao: ExercisesDao
    /*
    This is very confusing but from what I understand Room needs to compile these data classes into a database
    to do that you create this object which is threadsafe, to make sure that there is only one instance of the database
    this is called a singleton. If you don't make this threadsafe what can happen is two threads could try to create the object
    */
    companion object {
        const val DATABASE_NAME = "exercise_db"

        /*
        // This will help prevent race conditions
        @Volatile
        private var INSTANCE: ExerciseDatabase? = null
        fun getInstance(context: Context): ExerciseDatabase {
        synchronized will lock "this" to a single thread
        synchronized(this) {
        returns the instance, but if it is null it will create the database
        return INSTANCE ?: Room.databaseBuilder(
        context.applicationContext,
        ExerciseDatabase::class.java,
        "exercise_db"
        ).build().also {
        updates the singleton with the created database
        INSTANCE = it
        }
        }
        }
        */
    }
}

/*
It seems like a lot of this is handled natively by room and is not longer needed.
I can't be sure yet but after a bit more research I will comment it out if it is not needed

Since changing to use Dagger-hilt this is handled in the AppModule now so only a name is declared here
source: https://youtu.be/8YPXv7xKh2w?t=2698
*/