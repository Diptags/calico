package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dish")
data class Dish(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "dishId")
    val dishId: Long = 0,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String,

    @ColumnInfo(name = "desc")
    val desc: String

)