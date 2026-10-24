package com.example.data.audio

import android.content.Context
import android.media.MediaPlayer

/**
 * Minimal audio wrapper for the Dhawq journey stations.
 *
 * Deliberately built on the framework [MediaPlayer] — no Media3 dependency
 * until the project is buildable again: zero risk to the dependency graph,
 * and the interface is small enough to swap the implementation later.
 *
 * Files are expected at `res/raw/dhawq_station_01.mp3` … `dhawq_station_10.mp3`.
 * Until the voice is recorded the screen shows a quiet "coming soon" chip.
 */
class StationPlayer {

    /** Listener notified on the main thread of any state change. */
    fun interface OnStateChanged {
        fun onStateChanged(playbackState: PlaybackState)
    }

    enum class PlaybackState { IDLE, PLAYING, PAUSED }

    var onStateChanged: OnStateChanged? = null
        set(value) {
            field = value
            value?.onStateChanged(currentState)
        }

    private var player: MediaPlayer? = null
    private var currentResName: String? = null
    private var currentState: PlaybackState = PlaybackState.IDLE
        set(value) {
            if (field != value) {
                field = value
                onStateChanged?.onStateChanged(value)
            }
        }

    /**
     * Starts (or resumes) the given raw resource. Returns false when the
     * resource does not exist — the caller then shows "coming soon".
     * Requesting a *different* station while one is loaded resets the player
     * and prepares the new track (instead of resuming the old one).
     */
    fun play(context: Context, rawResName: String): Boolean {
        val resId = context.resources.getIdentifier(rawResName, "raw", context.packageName)
        if (resId == 0) return false

        if (currentResName != null && currentResName != rawResName) {
            releasePlayer()
        }

        if (player == null) {
            currentResName = rawResName
            player = MediaPlayer().apply {
                setOnCompletionListener {
                    seekTo(0)
                    currentState = PlaybackState.IDLE
                }
                setDataSource(context.applicationContext, android.net.Uri.parse("android.resource://${context.packageName}/$resId"))
                setOnPreparedListener { it.start(); currentState = PlaybackState.PLAYING }
                setOnErrorListener { _, _, _ ->
                    releasePlayer()
                    true
                }
                prepareAsync()
            }
        } else {
            player?.start()
            currentState = PlaybackState.PLAYING
        }
        return true
    }

    fun pause() {
        val p = player ?: return
        if (p.isPlaying) p.pause()
        currentState = PlaybackState.PAUSED
    }

    fun stop() {
        player?.let {
            if (it.isPlaying) it.stop()
            it.reset()
        }
        currentState = PlaybackState.IDLE
    }

    fun releasePlayer() {
        player?.release()
        player = null
        currentState = PlaybackState.IDLE
    }
}
