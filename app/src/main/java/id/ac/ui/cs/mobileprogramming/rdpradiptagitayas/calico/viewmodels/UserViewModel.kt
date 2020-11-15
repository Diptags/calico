package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.User
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.repositories.UserRepository

class UserViewModel : ViewModel() {

    private var userLiveData: LiveData<User>? = null

    fun addUser(context: Context, userData: HashMap<String, String>) {
        val user = User(
            UserRepository.generateUuid(),
            userData["name"].toString(),
            userData["username"].toString(),
            userData["email"].toString(),
            userData["phoneNo"].toString(),
            userData["password"].toString()
        )
        UserRepository.addUser(context, user)
    }

    fun checkIsUserRegistered(context: Context, username: String, password: String): Boolean {
        userLiveData = UserRepository.getUserByUsername(context, username)
        if (userLiveData?.value?.password == password) {
            return true
        }
        return false
    }

    fun getUserByUsername(context: Context, username: String): LiveData<User>? {
        return UserRepository.getUserByUsername(context, username)
    }

    fun updateUser(context: Context, user: User) {
        UserRepository.updateUser(context, user)
    }

}