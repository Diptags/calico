package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.dish.DishActivity
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.journal.JournalActivity
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.profile.ProfileActivity
import kotlinx.android.synthetic.main.home_fragment.*


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dishButton.setOnClickListener {
            startActivity(Intent(activity, DishActivity::class.java))
        }

        journalButton.setOnClickListener {
            startActivity(Intent(activity, JournalActivity::class.java))
        }

        profileButton.setOnClickListener {
            startActivity(Intent(activity, ProfileActivity::class.java))
        }
    }
}