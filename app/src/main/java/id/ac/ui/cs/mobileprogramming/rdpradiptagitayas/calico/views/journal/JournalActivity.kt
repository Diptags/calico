package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.journal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.helpers.GeneralHelpers
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.home.HomeActivity


class JournalActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GeneralHelpers.enableFullScreen(this)
        setContentView(R.layout.journal_activity)

        if (savedInstanceState == null) {
            changeFragmentToJournalListFragment()
        }
    }

    private fun changeFragmentToJournalListFragment() {
        val nextFragment = JournalListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.journal_container, nextFragment)
            .commit()
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}
