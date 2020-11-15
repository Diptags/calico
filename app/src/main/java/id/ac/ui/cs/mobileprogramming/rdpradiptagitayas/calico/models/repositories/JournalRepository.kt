package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.repositories

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.ApplicationDatabase
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.Journal

@Suppress("DEPRECATION")
class JournalRepository {

    companion object {

        var applicationDatabase: ApplicationDatabase? = null

        private fun initializeDB(context: Context): ApplicationDatabase {
            return ApplicationDatabase.getDatabaseClient(context)
        }

        fun addJournal(context: Context, journal: Journal) {
            applicationDatabase = initializeDB(context)
            AddJournalAsync().execute(journal)
        }

        fun getAllJournals(context: Context): LiveData<List<Journal>>? {
            applicationDatabase = initializeDB(context)
            return applicationDatabase!!.journalDao().getAllJournal()
        }

        fun getJournalById(context: Context, journalId: Long): LiveData<Journal>? {
            applicationDatabase = initializeDB(context)
            return applicationDatabase!!.journalDao().getJournalById(journalId)
        }

        fun updateJournal(context: Context, journal: Journal) {
            applicationDatabase = initializeDB(context)
            UpdateJournalAsync().execute(journal)
        }

        fun deleteJournal(context: Context, journal: Journal) {
            applicationDatabase = initializeDB(context)
            DeleteJournalAsync().execute(journal)
        }

        private class AddJournalAsync : AsyncTask<Journal, Void, Void>() {
            override fun doInBackground(vararg journals: Journal): Void? {
                applicationDatabase!!.journalDao().addJournal(journals[0])
                return null
            }
        }

        private class UpdateJournalAsync : AsyncTask<Journal, Void, Void>() {
            override fun doInBackground(vararg journals: Journal): Void? {
                applicationDatabase!!.journalDao().updateJournal(journals[0])
                return null
            }
        }

        private class DeleteJournalAsync : AsyncTask<Journal, Void, Void>() {
            override fun doInBackground(vararg journals: Journal): Void? {
                applicationDatabase!!.journalDao().deleteJournal(journals[0])
                return null
            }
        }
    }
}