package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.repositories

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.ApplicationDatabase
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.User
import java.util.*

@Suppress("DEPRECATION")
class UserRepository {

    companion object {

        var applicationDatabase: ApplicationDatabase? = null

        private fun initializeDB(context: Context): ApplicationDatabase {
            return ApplicationDatabase.getDatabaseClient(context)
        }

        fun generateUuid(): String = UUID.randomUUID().toString()

        fun addUser(context: Context, user: User) {
            applicationDatabase = initializeDB(context)
            AddUserAsync().execute(user)
        }

        fun getUserByUsername(context: Context, username: String): LiveData<User>? {
            applicationDatabase = initializeDB(context)
            return applicationDatabase!!.userDao().getUserByUsername(username)
        }

        fun updateUser(context: Context, user: User) {
            applicationDatabase = initializeDB(context)
            UpdateUserAsync().execute(user)
        }

        private class AddUserAsync : AsyncTask<User, Void, Void>() {
            override fun doInBackground(vararg users: User): Void? {
                applicationDatabase!!.userDao().addUser(users[0])
                return null
            }
        }

        private class UpdateUserAsync : AsyncTask<User, Void, Void>() {
            override fun doInBackground(vararg users: User): Void? {
                applicationDatabase!!.userDao().updateUser(users[0])
                return null
            }
        }
    }
}
