package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

// Journal and Dish relationship (Many to many)
@Entity(primaryKeys = ["journalId", "dishId"])
data class JournalDishCrossRef(
    @ColumnInfo(index = true) val journalId: Long,
    @ColumnInfo(index = true) val dishId: Long
)