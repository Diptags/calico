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
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.helpers.GeneralHelpers
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.NOTIFICATION_CODE


class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {

        val pendingIntent: PendingIntent = GeneralHelpers.prepareNotificationIntent(context)
        val notificationLargeIcon =
            BitmapFactory.decodeResource(context.resources, R.drawable.logo_color)

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