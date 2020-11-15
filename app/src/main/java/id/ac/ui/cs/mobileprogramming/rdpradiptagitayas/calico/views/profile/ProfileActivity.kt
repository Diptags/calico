package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.Helpers
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.home.HomeActivity


class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Helpers.enableFullScreen(this)
        setContentView(R.layout.profile_activity)

        if (savedInstanceState == null) {
            changeFragmentToProfileInfoFragment()
        }
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

    private fun changeFragmentToProfileInfoFragment() {
        val nextFragment = ProfileInfoFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.profile_container, nextFragment)
            .commit()
    }
}
