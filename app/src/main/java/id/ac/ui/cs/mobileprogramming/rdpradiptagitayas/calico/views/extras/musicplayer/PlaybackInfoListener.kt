package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.extras.musicplayer

import androidx.annotation.IntDef
import java.lang.annotation.RetentionPolicy

abstract class PlaybackInfoListener {

    open fun onDurationChanged(duration: Int) {}
    open fun onPositionChanged(position: Int) {}
    open fun onStateChanged(@State state: Int) {}
    open fun onPlaybackCompleted() {}

    annotation class State {
        companion object {
            var INVALID = -1
            var PLAYING = 0
            var PAUSED = 1
            var RESET = 2
            var COMPLETED = 3
        }
    }

    companion object {
        fun convertStateToString(@State state: Int): String {
            return when (state) {
                State.COMPLETED -> "COMPLETED"
                State.INVALID -> "INVALID"
                State.PAUSED -> "PAUSED"
                State.PLAYING -> "PLAYING"
                State.RESET -> "RESET"
                else -> "N/A"
            }
        }
    }
}