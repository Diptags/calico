package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.Journal

@Dao
interface JournalDAO {

    @Insert
    fun addJournal(journal: Journal)

    @Query("SELECT * from journal")
    fun getAllJournal(): LiveData<List<Journal>>

    @Query("SELECT * FROM journal WHERE journalId = :journalId")
    fun getJournalById(journalId: Long): LiveData<Journal>

    @Update
    fun updateJournal(journal: Journal)

    @Delete
    fun deleteJournal(journal: Journal)

}