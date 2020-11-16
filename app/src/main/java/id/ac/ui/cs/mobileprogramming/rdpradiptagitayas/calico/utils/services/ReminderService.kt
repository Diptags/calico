package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.helpers.GeneralHelper


class ReminderService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onCreate() {
        GeneralHelper.scheduleDailyReminder(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_NOT_STICKY
    }
}