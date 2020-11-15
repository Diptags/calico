package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.intro

import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.Preferences
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.SPLASH_SCREEN_TIMER
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.auth.AuthActivity
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.onboarding.OnboardingActivity

class IntroActivity : AppCompatActivity() {

    // Variables
    private var backgroundImage: ImageView? = null
    private var createdBy: TextView? = null

    // Shared Preferences
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        setContentView(R.layout.intro_activity)

        // Hooks (Designs)
        backgroundImage = findViewById(R.id.background_image)
        createdBy = findViewById(R.id.created_by_line)

        sharedPreferences = this.getSharedPreferences("calico", MODE_PRIVATE)

        // Wait for few seconds before redirect to onboarding activity
        Handler(Looper.getMainLooper()).postDelayed({
            if (Preferences.checkFirstTime(sharedPreferences)) {
                Preferences.saveFirstTime(sharedPreferences)
                startActivity(Intent(this, OnboardingActivity::class.java))
            } else {
                startActivity(Intent(this, AuthActivity::class.java))
            }
            finish()
        }, SPLASH_SCREEN_TIMER.toLong())
    }
}