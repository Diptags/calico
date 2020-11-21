package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.journal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import kotlinx.android.synthetic.main.journal_detail_activity.*

class JournalDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.journal_detail_activity)

        journalTitle.text = intent.getStringExtra("title")
        journalSummary.text = intent.getStringExtra("summary")
        journalDescription.text = intent.getStringExtra("description")
    }
}