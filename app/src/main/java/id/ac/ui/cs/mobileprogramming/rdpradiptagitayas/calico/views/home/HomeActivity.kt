package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.*
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.helpers.GeneralHelpers
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.receivers.NetworkChangeReceiver


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GeneralHelpers.enableFullScreen(this)
        GeneralHelpers.scheduleDailyReminder(this)

        GeneralHelpers.registerNetworkBroadcast(
            this,
            NetworkChangeReceiver()
        )

        setContentView(R.layout.home_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.home_container, HomeFragment())
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            GeneralHelpers.unregisterNetworkBroadcast(
                this,
                NetworkChangeReceiver()
            )
        } catch (e: IllegalArgumentException) {
            Log.d("calico_network_broadcast", e.toString())
        }
    }
}