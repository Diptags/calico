package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.extras

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.helpers.GeneralHelpers
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.home.HomeActivity

class ExtrasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GeneralHelpers.enableFullScreen(this)
        setContentView(R.layout.extras_activity)

        if (savedInstanceState == null) {
            changeFragmentToExtrasListFragment()
        }
    }

    private fun changeFragmentToExtrasListFragment() {
        val nextFragment = ExtrasListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.extras_container, nextFragment)
            .commit()
    }

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        if (count == 0) {
            super.onBackPressed()
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        } else {
            supportFragmentManager.popBackStack()
        }
    }
}