package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.receivers

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.services.ReminderService


class BootReceiver : BroadcastReceiver() {

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent?) {
        val i = Intent(context, ReminderService::class.java)
        context.startService(i)
        Toast.makeText(context, context.getString(R.string.service_active), Toast.LENGTH_LONG)
            .show()
    }
}