package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views

import android.content.Context
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.persistences.ApplicationDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginRepository {

    companion object {

        var applicationDatabase: ApplicationDatabase? = null
        var loginTableModel: LiveData<LoginTableModel>? = null

        private fun initializeDB(context: Context): ApplicationDatabase {
            return ApplicationDatabase.getDatabaseClient(context)
        }

        fun insertData(context: Context, username: String, password: String) {

            applicationDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                val loginDetails = LoginTableModel(username, password)
//                applicationDatabase!!.loginDao().InsertData(loginDetails)
            }
        }

        fun getLoginDetails(context: Context, username: String): LiveData<LoginTableModel>? {

            applicationDatabase = initializeDB(context)

//            loginTableModel = applicationDatabase!!.loginDao().getLoginDetails(username)

            return loginTableModel
        }

    }
}