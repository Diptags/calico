package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.receivers

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.Helpers.Companion.showConnectionErrorDialog

@Suppress("DEPRECATION")
class NetworkChangeReceiver : BroadcastReceiver() {

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent?) {
        try {
            if (!isOnline(context)) {
                showConnectionErrorDialog(context)
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }

    private fun isOnline(context: Context): Boolean {
        return try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo

            // Airplane mode connection checking
            netInfo != null && netInfo.isConnected
        } catch (e: NullPointerException) {
            e.printStackTrace()
            false
        }
    }

}