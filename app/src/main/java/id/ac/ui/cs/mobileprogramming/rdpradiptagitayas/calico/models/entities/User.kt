package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "user")
data class User(

    @PrimaryKey
    @ColumnInfo(name = "userId")
    val userId: String = UUID.randomUUID().toString(),

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "username")
    val username: String,

    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "phoneNo")
    val phoneNo: String,

    @ColumnInfo(name = "password")
    val password: String

)