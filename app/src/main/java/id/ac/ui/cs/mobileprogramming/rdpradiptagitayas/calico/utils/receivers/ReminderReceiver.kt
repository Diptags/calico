package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.receivers

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.GENERAL_NOTIFICATION_CHANNEL_ID
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.NOTIFICATION_CODE
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.home.HomeActivity


class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {

        val notificationLargeIcon =
            BitmapFactory.decodeResource(context.resources, R.drawable.logo_color)

        val notificationIntent = Intent(context, HomeActivity::class.java)
        notificationIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

        val pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0)

        val notificationBuilder = NotificationCompat.Builder(
            context,
            GENERAL_NOTIFICATION_CHANNEL_ID
        ).apply {
            setSmallIcon(R.drawable.logo_color)
            setLargeIcon(notificationLargeIcon)
            setContentIntent(pendingIntent)
            setContentTitle(context.resources.getString(R.string.reminder_notification_title))
            setContentText(context.resources.getString(R.string.reminder_notification_content))
            priority = NotificationCompat.PRIORITY_DEFAULT
            setAutoCancel(false)
        }

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(NOTIFICATION_CODE, notificationBuilder.build())
    }
}