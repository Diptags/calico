package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.Journal
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.repositories.JournalRepository


class JournalViewModel : ViewModel() {

    var journalMutableLiveData: MutableLiveData<Journal> =
        MutableLiveData<Journal>()

    fun addJournal(context: Context, journal: Journal) {
        JournalRepository.addJournal(context, journal)
        journalMutableLiveData.value = journal
    }

    fun getAllJournal(context: Context): LiveData<List<Journal>>? {
        return JournalRepository.getAllJournal(context)
    }

    fun getJournalById(context: Context, journalId: Long): LiveData<Journal>? {
        return JournalRepository.getJournalById(context, journalId)
    }

    fun updateJournal(context: Context, journal: Journal) {
        JournalRepository.updateJournal(context, journal)
        journalMutableLiveData.value = journal
    }

    fun deleteJournal(context: Context, journal: Journal) {
        JournalRepository.deleteJournal(context, journal)
    }
}
