package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.extras.countdown

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.helpers.GeneralHelpers

class CountDownActivity : AppCompatActivity() {

    companion object {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GeneralHelpers.enableFullScreen(this)
        setContentView(R.layout.count_down_activity)

        if (savedInstanceState == null) {
            changeFragmentToCountDownFragment()
        }
    }

    private fun changeFragmentToCountDownFragment() {
        val nextFragment = CountDownFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.count_down_container, nextFragment)
            .commit()
    }
}