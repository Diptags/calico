package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils

import android.content.SharedPreferences

// This is a class that contains function for shared preferences
class Preferences {

    companion object {

        // Check if user first time visit the application
        fun checkFirstTime(sharedPreferences: SharedPreferences?): Boolean {
            return sharedPreferences!!.getBoolean("firstTimeVisit", true)
        }

        // Check if user first time visit the application
        fun checkLoggedIn(sharedPreferences: SharedPreferences?): Boolean {
            return sharedPreferences!!.getBoolean("loggedIn", false)
        }

        // Save state of user first time visit
        fun saveFirstTime(sharedPreferences: SharedPreferences?) {
            val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
            editor.putBoolean("firstTimeVisit", false)
            editor.apply()
        }

        // Save credentials in shared preferences
        fun saveCredentials(
            sharedPreferences: SharedPreferences?,
            username: String?,
            password: String?
        ) {
            val editor: SharedPreferences.Editor = sharedPreferences!!.edit()
            editor.putBoolean("loggedIn", true)
            editor.putString("username", username)
            editor.putString("password", password)
            editor.apply()
        }

        // Get currently signed in user data
        fun getSignedInUser(sharedPreferences: SharedPreferences?): String? {
            return sharedPreferences!!.getString("username", null)
        }
    }
}
