package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.GENERAL_NOTIFICATION_CHANNEL_DESC
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.GENERAL_NOTIFICATION_CHANNEL_ID
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.GENERAL_NOTIFICATION_CHANNEL_NAME
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.Helpers
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.receivers.NetworkChangeReceiver


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Helpers.enableFullScreen(this)
        Helpers.scheduleDailyReminder(this)

        initReminderNotificationChannel()
        setContentView(R.layout.home_activity)

        if (savedInstanceState == null) {
            changeFragmentToHomeFragment()
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

    private fun changeFragmentToHomeFragment() {
        val nextFragment = HomeFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.home_container, nextFragment)
            .commit()
    }

    override fun onStart() {
        super.onStart()
        Helpers.registerNetworkBroadcast(
            this,
            NetworkChangeReceiver()
        )
    }

    override fun onStop() {
        super.onStop()
        try {
            Helpers.unregisterNetworkBroadcast(
                this,
                NetworkChangeReceiver()
            )
        } catch (e: IllegalArgumentException) {
            Log.d("calico_network_broadcast", "IllegalArgumentException")
        }
    }

}