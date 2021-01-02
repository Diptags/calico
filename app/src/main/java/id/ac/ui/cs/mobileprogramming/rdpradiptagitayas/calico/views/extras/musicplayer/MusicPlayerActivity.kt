package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.extras.musicplayer

import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.R
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.EXTRAS_NOTIFICATION_CHANNEL_ID
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.NOTIFICATION_CODE
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.utils.helpers.GeneralHelpers
import id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.extras.ExtrasActivity

class MusicPlayerActivity : AppCompatActivity() {
    private var mSeekbarAudio: SeekBar? = null
    private var mPlayerAdapter: PlayerAdapter? = null
    private var mUserIsSeeking = false

    private var playButton: Button? = null
    private var pauseButton: Button? = null
    private var stopButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.music_player_activity)
        initializeUI()
        initializeSeekbar()
        initializePlaybackController()
    }

    override fun onStart() {
        super.onStart()
        mPlayerAdapter!!.loadMedia(MEDIA_RES_ID)
    }

    override fun onStop() {
        super.onStop()
        sendMusicNotification()
    }

    override fun onBackPressed() {
        super.onBackPressed()

        // Reset state playback
        mPlayerAdapter!!.reset()
        resetButtonConditions()

        // Menampilkan pesan musik berhenti
        Toast.makeText(
            this,
            getString(R.string.music_stopped),
            Toast.LENGTH_LONG
        ).show();

        // Intent ke activity sebelumnya
        startActivity(Intent(this, ExtrasActivity::class.java))
        finish()
    }

    private fun initializeUI() {
        playButton = findViewById(R.id.button_play)
        pauseButton = findViewById(R.id.button_pause)
        stopButton = findViewById(R.id.button_reset)
        mSeekbarAudio = findViewById(R.id.seekbar_audio)

        pauseButton?.setOnClickListener {
            mPlayerAdapter!!.pause()
            playButton?.isEnabled = true
            pauseButton?.isEnabled = false
        }
        playButton?.setOnClickListener {
            mPlayerAdapter!!.play()
            playButton?.isEnabled = false
            pauseButton?.isEnabled = true
            pauseButton?.visibility = View.VISIBLE
            stopButton?.visibility = View.VISIBLE
        }
        stopButton?.setOnClickListener {
            mPlayerAdapter!!.reset()
            resetButtonConditions()
        }
    }

    private fun resetButtonConditions() {
        playButton?.isEnabled = true
        pauseButton?.isEnabled = true
        stopButton?.isEnabled = true
        pauseButton?.visibility = View.INVISIBLE
        stopButton?.visibility = View.INVISIBLE
    }

    private fun sendMusicNotification() {
        val pendingIntent: PendingIntent = GeneralHelpers.prepareNotificationIntent(this)
        val notificationLargeIcon =
            BitmapFactory.decodeResource(resources, R.drawable.logo_color)

        val notificationBuilder = NotificationCompat.Builder(
            this,
            EXTRAS_NOTIFICATION_CHANNEL_ID
        ).apply {
            setSmallIcon(R.drawable.logo_color)
            setLargeIcon(notificationLargeIcon)
            setContentIntent(pendingIntent)
            setContentTitle(getString(R.string.music_notification_title))
            setContentText(getString(R.string.music_notification_desc))
            priority = NotificationCompat.PRIORITY_DEFAULT
            setAutoCancel(false)
        }
        val notificationManager = NotificationManagerCompat.from(this)
        notificationManager.notify(NOTIFICATION_CODE, notificationBuilder.build())
    }

    private fun initializePlaybackController() {
        val mMediaPlayerHolder = MusicPlayerHolder(this)
        mMediaPlayerHolder.setPlaybackInfoListener(PlaybackListener())
        mPlayerAdapter = mMediaPlayerHolder
    }

    private fun initializeSeekbar() {
        mSeekbarAudio!!.setOnSeekBarChangeListener(
            object : OnSeekBarChangeListener {
                var userSelectedPosition = 0
                override fun onStartTrackingTouch(seekBar: SeekBar) {
                    mUserIsSeeking = true
                }

                override fun onProgressChanged(
                    seekBar: SeekBar,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if (fromUser) {
                        userSelectedPosition = progress
                    }
                }

                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    mUserIsSeeking = false
                    mPlayerAdapter!!.seekTo(userSelectedPosition)
                }
            })
    }

    inner class PlaybackListener : PlaybackInfoListener() {
        override fun onDurationChanged(duration: Int) {
            mSeekbarAudio!!.max = duration
        }

        override fun onPositionChanged(position: Int) {
            if (!mUserIsSeeking) {
                mSeekbarAudio!!.setProgress(position, true)
            }
        }

        override fun onStateChanged(@State state: Int) {}
        override fun onPlaybackCompleted() {}
    }

    companion object {
        const val MEDIA_RES_ID: Int = R.raw.jazz_in_paris
    }
}