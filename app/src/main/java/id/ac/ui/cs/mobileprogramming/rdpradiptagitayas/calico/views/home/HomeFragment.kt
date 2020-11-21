package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.dish.DishActivity
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.journal.JournalActivity
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.profile.ProfileActivity
import kotlinx.android.synthetic.main.home_fragment.*
import java.util.*


@Suppress("DEPRECATION")
class HomeFragment : Fragment() {

    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

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

        mHandler = Handler()

        changeQuote()
    }

    private fun getRandomQuote(): Int {
        val quotes: Array<Int> = arrayOf(
            R.string.quote_1,
            R.string.quote_2,
            R.string.quote_3,
            R.string.quote_4,
            R.string.quote_5,
            R.string.quote_6,
            R.string.quote_7,
            R.string.quote_8,
            R.string.quote_9,
            R.string.quote_10,
        )
        val random = Random()
        val index: Int = random.nextInt(10)
        return quotes[index]
    }

    private fun changeQuote() {
        mRunnable = Runnable {
            quoteText.setText(getRandomQuote())
            mHandler.postDelayed(
                mRunnable,
                5000
            )
        }
        mHandler.postDelayed(
            mRunnable,
            5000
        )
    }
}