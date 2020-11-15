package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.Dish
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.Journal
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.User
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.daos.DishDAO
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.daos.JournalDAO
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.daos.UserDAO
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.DATABASE_NAME


@Database(
    entities = [Dish::class, Journal::class, User::class],
    version = 1,
    exportSchema = false
)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun dishDao(): DishDAO
    abstract fun journalDao(): JournalDAO
    abstract fun userDao(): UserDAO

    companion object {

        @Volatile
        private var INSTANCE: ApplicationDatabase? = null

        fun getDatabaseClient(context: Context): ApplicationDatabase {
            if (INSTANCE != null) return INSTANCE!!
            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, ApplicationDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
                return INSTANCE!!
            }
        }
    }
}