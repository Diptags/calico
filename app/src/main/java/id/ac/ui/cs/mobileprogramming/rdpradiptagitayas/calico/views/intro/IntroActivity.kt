package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.intro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.*
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.helpers.GeneralHelpers

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GeneralHelpers.enableFullScreen(this)
        setContentView(R.layout.intro_activity)

        // Untuk meningkatkan performa, inisiasi notification channel hanya saat splash / intro screen
        initNotificationChannel()

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

    private fun initNotificationChannel() {

        // Notification Channel untuk General
        GeneralHelpers.createNotificationChannel(
            this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, true,
            GENERAL_NOTIFICATION_CHANNEL_ID, GENERAL_NOTIFICATION_CHANNEL_NAME,
            GENERAL_NOTIFICATION_CHANNEL_DESC
        )

        // Notification Channel untuk Fitur Ekstra
        GeneralHelpers.createNotificationChannel(
            this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, true,
            EXTRAS_NOTIFICATION_CHANNEL_ID, EXTRAS_NOTIFICATION_CHANNEL_NAME,
            EXTRAS_NOTIFICATION_CHANNEL_DESC
        )
    }
}
