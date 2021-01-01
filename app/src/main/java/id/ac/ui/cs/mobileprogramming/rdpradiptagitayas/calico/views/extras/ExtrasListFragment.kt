package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.extras

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.extras.countdown.CountDownActivity
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.extras.musicplayer.MusicPlayerActivity
import kotlinx.android.synthetic.main.extras_list_fragment.*


class ExtrasListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.extras_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showRandomQuestions()
        timerButton.setOnClickListener {
            startActivity(Intent(context, CountDownActivity::class.java))
        }
        musicPlayerButton.setOnClickListener {
            startActivity(Intent(context, MusicPlayerActivity::class.java))
        }
    }

    private fun showRandomQuestions() {
        val index: Int = getRandomNumberFromNative()
        val questions: Array<Int> = arrayOf(
            R.string.question_1,
            R.string.question_2,
            R.string.question_3,
            R.string.question_4,
            R.string.question_5,
            R.string.question_6,
            R.string.question_7,
            R.string.question_8,
            R.string.question_9,
            R.string.question_10,
        )
        randomQuestion.setText(questions[index])
    }

    // Implementasi fungsi sumNumbers berada pada berkas C++
    private external fun getRandomNumberFromNative(): Int

    // Melakukan load berkas C++ dalam bentuk native library
    companion object {
        init {
            System.loadLibrary("calico-lib")
        }
    }
}
