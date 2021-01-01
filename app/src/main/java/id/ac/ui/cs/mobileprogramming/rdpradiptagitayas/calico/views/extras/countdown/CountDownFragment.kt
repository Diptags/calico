package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.extras.countdown

import android.app.PendingIntent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.Fragment
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.EXTRAS_NOTIFICATION_CHANNEL_ID
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.helpers.CountDownHelpers
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.GENERAL_NOTIFICATION_CHANNEL_ID
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.helpers.GeneralHelpers
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.NOTIFICATION_CODE
import kotlinx.android.synthetic.main.count_down_fragment.*

class CountDownFragment : Fragment(), CountDownHelpers.OnCountDownListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.count_down_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        timerButton1.setOnClickListener {
            setTimer(30)
            sendSuccessToastMessage()
        }
        timerButton2.setOnClickListener {
            setTimer(60)
            sendSuccessToastMessage()
        }
        timerButton3.setOnClickListener {
            setTimer(360)
            sendSuccessToastMessage()
        }
        timerButton4.setOnClickListener {
            setTimer(720)
            sendSuccessToastMessage()
        }
    }

    private fun setTimer(minute: Long) {
        val countDownTimer =
            CountDownHelpers(
                minute,
                0,
                this
            )
        countDownTimer.start()
    }

    private fun sendSuccessToastMessage() {
        Toast.makeText(
            requireActivity(),
            requireContext().getString(R.string.timer_success),
            Toast.LENGTH_LONG
        ).show();
    }

    private fun sendCountDownNotification() {
        val pendingIntent: PendingIntent = GeneralHelpers.prepareNotificationIntent(requireContext())
        val notificationLargeIcon =
            BitmapFactory.decodeResource(requireContext().resources, R.drawable.logo_color)

        val notificationBuilder = NotificationCompat.Builder(
            requireContext(),
            EXTRAS_NOTIFICATION_CHANNEL_ID
        ).apply {
            setSmallIcon(R.drawable.logo_color)
            setLargeIcon(notificationLargeIcon)
            setContentIntent(pendingIntent)
            setContentTitle(requireContext().resources.getString(R.string.timer_notification_title))
            setContentText(requireContext().resources.getString(R.string.timer_notification_desc))
            priority = NotificationCompat.PRIORITY_DEFAULT
            setAutoCancel(false)
        }
        val notificationManager = NotificationManagerCompat.from(requireContext())
        notificationManager.notify(NOTIFICATION_CODE, notificationBuilder.build())
    }

    override fun onCountDownActive(time: String) {
        // Tidak melakukan apapun saat ini
    }

    override fun onCountDownFinished() {
        sendCountDownNotification()
    }
}