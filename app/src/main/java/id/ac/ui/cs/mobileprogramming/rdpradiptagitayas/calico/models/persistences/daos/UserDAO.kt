package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.persistences.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.User

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User)

    @Query("SELECT * FROM user WHERE username =:username")
    fun getUserByUsername(username: String): LiveData<User>

    @Update
    fun updateUser(user: User)
}