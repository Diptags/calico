package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.GENERAL_NOTIFICATION_CHANNEL_DESC
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.GENERAL_NOTIFICATION_CHANNEL_ID
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.GENERAL_NOTIFICATION_CHANNEL_NAME
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.helpers.GeneralHelper


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GeneralHelper.enableFullScreen(this)
        GeneralHelper.scheduleDailyReminder(this)

        initReminderNotificationChannel()
        setContentView(R.layout.home_activity)

        if (savedInstanceState == null) {
            changeFragmentToHomeFragment()
        }
    }

    private fun initReminderNotificationChannel() {
        GeneralHelper.createNotificationChannel(
            this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            GENERAL_NOTIFICATION_CHANNEL_ID, GENERAL_NOTIFICATION_CHANNEL_NAME,
            GENERAL_NOTIFICATION_CHANNEL_DESC
        )
    }

    private fun changeFragmentToHomeFragment() {
        val nextFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.home_container, nextFragment)
            .commit()
    }
}