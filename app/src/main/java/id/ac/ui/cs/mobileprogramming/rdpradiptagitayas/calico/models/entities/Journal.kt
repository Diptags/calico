package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "journal")
data class Journal(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "journalId")
    val journalId: Long = 0,

    @ColumnInfo(name = "userCreatorId")
    val userCreatorId: Long,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "content")
    val content: String?

)