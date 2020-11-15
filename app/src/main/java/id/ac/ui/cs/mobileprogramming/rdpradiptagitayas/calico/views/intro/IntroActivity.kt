package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.intro

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.Helpers

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Helpers.enableFullScreen(this)

        if (savedInstanceState == null) {
            openIntroFragment()
        }
    }

    private fun openIntroFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.intro_container, IntroFragment())
            .commit()
    }
}
