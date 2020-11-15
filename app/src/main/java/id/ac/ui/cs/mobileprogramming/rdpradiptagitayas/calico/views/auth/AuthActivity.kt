package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.Helpers

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.auth_activity)

        Helpers.enableFullScreen(this)

        if (savedInstanceState == null) {
            openAuthSignInFragment()
        }
    }

    private fun openAuthSignInFragment() {
        val nextFragment = AuthSignInFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.auth_container, nextFragment)
            .commit()
    }
}
