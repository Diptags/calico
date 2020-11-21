package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils

import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Environment
import android.provider.Settings
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.receivers.ReminderReceiver
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.home.HomeActivity
import java.io.File
import java.util.*

@Suppress("DEPRECATION")
class Helpers {

    companion object {

        fun enableFullScreen(activity: Activity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                activity.window.insetsController?.hide(WindowInsets.Type.statusBars())
            } else {
                activity.window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
            }
        }

        fun createImageFile(context: Context, fileName: String): File {
            val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            return File(storageDir, fileName)
        }

        fun registerNetworkBroadcast(context: Context, receiver: BroadcastReceiver) {
            context.registerReceiver(
                receiver,
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            )
        }

        fun unregisterNetworkBroadcast(context: Context, receiver: BroadcastReceiver) {
            context.unregisterReceiver(receiver)
        }

        fun showConnectionErrorDialog(context: Context) {
            val builder = AlertDialog.Builder(context)
            builder.setMessage(R.string.no_connection)
                .setCancelable(false)
                .setPositiveButton(
                    R.string.connect_btn
                ) { _, _ ->
                    context.startActivity(Intent(Settings.ACTION_WIFI_SETTINGS))
                }
                .setNegativeButton(
                    R.string.cancel_btn
                ) { _, _ ->
                    context.startActivity(Intent(context, HomeActivity::class.java))
                }
                .show()
        }

        fun createNotificationChannel(
            context: Context,
            importance: Int,
            showBadge: Boolean,
            channelId: String,
            name: String,
            description: String
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(channelId, name, importance)
                channel.description = description
                channel.setShowBadge(showBadge)
                val notificationManager = context.getSystemService(NotificationManager::class.java)
                notificationManager.createNotificationChannel(channel)
            }
        }

        fun scheduleDailyReminder(context: Context) {
            val manager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val pendingIntent: PendingIntent
            val calendar = Calendar.getInstance()

            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.HOUR, 0)
            calendar.set(Calendar.AM_PM, Calendar.AM)

            val myIntent = Intent(context, ReminderReceiver::class.java)
            pendingIntent = PendingIntent.getBroadcast(context, 0, myIntent, 0)

            manager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        }

        fun prepareNotificationIntent(context: Context): PendingIntent {
            val notificationIntent = Intent(context, HomeActivity::class.java)
            notificationIntent.flags =
                Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            return PendingIntent.getActivity(context, 0, notificationIntent, 0)
        }

        fun showToastMessage(context: Context, resourceId: Int) {
            Toast.makeText(
                context,
                context.resources.getString(resourceId),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}