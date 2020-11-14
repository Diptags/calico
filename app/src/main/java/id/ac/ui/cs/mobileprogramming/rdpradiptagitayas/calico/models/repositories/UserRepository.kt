package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.repositories

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.User
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.persistences.ApplicationDatabase
import java.util.*

@Suppress("DEPRECATION")
class UserRepository {

    companion object {

        var applicationDatabase: ApplicationDatabase? = null
        var userModel: LiveData<User>? = null

        private fun initializeDB(context: Context): ApplicationDatabase {
            return ApplicationDatabase.getDatabaseClient(context)
        }

        private fun generateUuid(): String = UUID.randomUUID().toString()

        fun addUser(context: Context, userData: HashMap<String, String>) {
            applicationDatabase = initializeDB(context)
            val user = User(
                generateUuid(),
                userData["name"].toString(),
                userData["username"].toString(),
                userData["email"].toString(),
                userData["phoneNo"].toString(),
                userData["password"].toString()
            )
            AddUserAsync().execute(user)
        }

        fun getUserByUsername(context: Context, username: String): LiveData<User>? {
            applicationDatabase = initializeDB(context)
            userModel = applicationDatabase!!.userDao().getUserByUsername(username)
            return userModel
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
