package id.ac.ui.cs.mobileprogramming.rdpradiptagitayas.calico.views.extras.musicplayer

interface PlayerAdapter {
    val isPlaying: Boolean
    fun loadMedia(resourceId: Int)
    fun release()
    fun play()
    fun reset()
    fun pause()
    fun initializeProgressCallback()
    fun seekTo(position: Int)
}
