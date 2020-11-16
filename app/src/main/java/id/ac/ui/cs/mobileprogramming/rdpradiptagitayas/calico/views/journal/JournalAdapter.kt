package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.journal

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.models.entities.Journal
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.home.HomeActivity


class JournalAdapter(var journalListFragment: JournalListFragment) :
    RecyclerView.Adapter<JournalAdapter.JournalViewHolder>() {

    var journalList: List<Journal>? = null

    class JournalViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cardImage: ImageView = itemView.findViewById(R.id.journal_card_image)
        var cardTitle: TextView = itemView.findViewById(R.id.journal_card_title)
        var cardSummary: TextView = itemView.findViewById(R.id.journal_card_summary)
        var parentLayout: CardView = itemView.findViewById(R.id.journalCard)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): JournalViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.journal_list_design, parent, false)
        return JournalViewHolder(view)
    }

    override fun onBindViewHolder(holder: JournalViewHolder, position: Int) {
        val journal: Journal? = journalList?.get(position)
        if (journal != null) {
            holder.cardTitle.text = journal.title
            holder.cardSummary.text = journal.summary

            if (journal.type == "food") holder.cardImage.setImageResource(R.drawable.food)
            else holder.cardImage.setImageResource(R.drawable.beverage)

            holder.parentLayout.setOnClickListener {
                val intent = Intent(journalListFragment.context, HomeActivity::class.java)
                intent.putExtra("id", journal.journalId)
                journalListFragment.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return if (journalList == null) 0
        else journalList!!.size
    }
}
