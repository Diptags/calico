package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.intro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.Helpers

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Helpers.enableFullScreen(this)
        setContentView(R.layout.intro_activity)

        if (savedInstanceState == null) {
            changeFragmentToIntroFragment()
        }
    }

    private fun changeFragmentToIntroFragment() {
        val nextFragment = IntroFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.intro_container, nextFragment)
            .commit()
    }
}
