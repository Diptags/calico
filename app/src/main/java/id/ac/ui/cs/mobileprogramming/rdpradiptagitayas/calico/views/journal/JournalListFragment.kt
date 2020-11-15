package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.journal

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R


class JournalListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.journal_list_fragment, container, false)
    }
}