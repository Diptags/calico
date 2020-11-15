package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.home

import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.GENERAL_NOTIFICATION_CHANNEL_DESC
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.GENERAL_NOTIFICATION_CHANNEL_ID
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.GENERAL_NOTIFICATION_CHANNEL_NAME
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.Helpers


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Helpers.enableFullScreen(this)
        Helpers.scheduleDailyReminder(this)

        initReminderNotificationChannel()
        setContentView(R.layout.home_activity)

        if (savedInstanceState == null) {
            openHomeFragment()
        }
    }

    private fun initReminderNotificationChannel() {
        Helpers.createNotificationChannel(
            this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            GENERAL_NOTIFICATION_CHANNEL_ID, GENERAL_NOTIFICATION_CHANNEL_NAME,
            GENERAL_NOTIFICATION_CHANNEL_DESC
        )
    }

    private fun openHomeFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.home_container, HomeFragment())
            .commit()
    }
}