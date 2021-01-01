package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.journal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.viewmodels.JournalViewModel
import kotlinx.android.synthetic.main.journal_list_fragment.*


class JournalListFragment : Fragment() {

    private lateinit var journalViewModel: JournalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.journal_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        journalViewModel = ViewModelProvider(requireActivity()).get(JournalViewModel::class.java)
        getJournals()

        addJournalButton.setOnClickListener {
            changeFragmentToJournalCreateFragment()
        }
    }

    private fun getJournals() {
        journalViewModel.getAllJournal(requireContext())?.observe(requireActivity()) {

            if (it.isNotEmpty()) {
                val journalAdapter = JournalAdapter(this)
                journalAdapter.journalList = it

                val journalRecyclerView: RecyclerView? = view?.findViewById(R.id.journal_recycler)
                journalRecyclerView?.adapter = journalAdapter
                journalRecyclerView?.layoutManager = LinearLayoutManager(context)

                if (journal_list_empty_placeholder != null) {
                    journal_list_empty_placeholder.visibility = View.GONE
                }
            }
        }
    }

    private fun changeFragmentToJournalCreateFragment() {
        val nextFragment = JournalCreateFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.journal_container, nextFragment)
            .addToBackStack(null)
            .commit()
    }
}