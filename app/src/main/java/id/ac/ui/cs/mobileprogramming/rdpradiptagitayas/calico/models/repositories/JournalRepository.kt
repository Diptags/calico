//package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.repositories
//
//import android.content.Context
//import android.os.AsyncTask
//import androidx.lifecycle.LiveData
//import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.Journal
//import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.persistences.CalicoDatabase
//
//@Suppress("DEPRECATION")
//class JournalRepository {
//
//    companion object {
//
//        var database: CalicoDatabase? = null
//        var journalModel: LiveData<Journal>? = null
//
//        private fun initializeDB(context: Context): CalicoDatabase {
//            return CalicoDatabase.getDatabaseClient(context)
//        }
//
//        fun addJournal(context: Context, journal: Journal) {
//            database = initializeDB(context)
//            AddJournalAsync().execute(journal)
//        }
//
//        fun getJournalById(context: Context, journalId: Long): LiveData<Journal> {
//            database = initializeDB(context)
//            return database!!.journalDao().getJournalById(journalId)
//        }
//
//        fun getAllJournalByUserId(
//            context: Context,
//            userCreatorId: String
//        ): LiveData<List<Journal>> {
//            database = initializeDB(context)
//            return database!!.journalDao().getAllJournalByUserId(userCreatorId)
//        }
//
//        fun updateJournal(context: Context, journal: Journal) {
//            database = initializeDB(context)
//            UpdateJournalAsync().execute(journal)
//        }
//
//        fun deleteJournal(context: Context, journal: Journal) {
//            database = initializeDB(context)
//            DeleteJournalAsync().execute(journal)
//        }
//
//        private class AddJournalAsync : AsyncTask<Journal, Void, Void>() {
//            override fun doInBackground(vararg journals: Journal): Void? {
////                journalDAO.addJournal(journals[0])
//                return null
//            }
//        }
//
//        private class UpdateJournalAsync : AsyncTask<Journal, Void, Void>() {
//            override fun doInBackground(vararg journals: Journal): Void? {
////                journalDAO.updateJournal(journals[0])
//                return null
//            }
//        }
//
//        private class DeleteJournalAsync : AsyncTask<Journal, Void, Void>() {
//            override fun doInBackground(vararg journals: Journal): Void? {
////                journalDAO.deleteJournal(journals[0])
//                return null
//            }
//        }
//    }
//}