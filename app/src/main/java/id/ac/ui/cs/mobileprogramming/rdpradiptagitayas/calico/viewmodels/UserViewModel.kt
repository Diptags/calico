package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.viewmodels

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.User
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.repositories.UserRepository

class UserViewModel : ViewModel() {

    private var userLiveData: LiveData<User>? = null

    fun insertSignUpDetails(context: Context, userData: HashMap<String, String>) {
        UserRepository.addUser(context, userData)
    }

    fun insertSignInDetails(context: Context, username: String, password: String): Boolean {
        userLiveData = UserRepository.getUserByUsername(context, username)
        if (userLiveData?.value?.password == password) {
            return true
        }
        return false
    }

    fun getSignedInUser(context: Context, username: String): LiveData<User>? {
        return UserRepository.getUserByUsername(context, username)
    }

    fun updateUserInformation(context: Context, user: User) {
        UserRepository.updateUser(context, user)
    }

}