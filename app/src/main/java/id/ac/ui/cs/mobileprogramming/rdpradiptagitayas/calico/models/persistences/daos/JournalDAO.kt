package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.persistences.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.Journal

@Dao
interface JournalDAO {

    @Insert
    fun addJournal(journal: Journal)

    @Query("SELECT * FROM Journal WHERE journalId = :journalId")
    fun getJournalById(journalId: Long): LiveData<Journal>

    @Query("SELECT * FROM Journal WHERE userCreatorId = :userCreatorId")
    fun getAllJournalByUserId(userCreatorId: String): LiveData<List<Journal>>

    @Update
    fun updateJournal(journal: Journal)

    @Delete
    fun deleteJournal(journal: Journal)

}