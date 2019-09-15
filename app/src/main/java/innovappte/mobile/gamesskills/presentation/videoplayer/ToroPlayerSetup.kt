package innovappte.mobile.gamesskills.presentation.videoplayer

import android.net.Uri
import android.view.ViewParent
import com.google.android.exoplayer2.ui.PlayerView
import im.ene.toro.ToroPlayer
import im.ene.toro.ToroUtil
import im.ene.toro.exoplayer.ExoPlayerViewHelper
import im.ene.toro.media.PlaybackInfo
import im.ene.toro.widget.Container

interface ToroPlayerSetup: ToroPlayer {

    fun getVideoUri(): Uri
    fun getVideoView(): PlayerView
    fun getAdapterPosition(): Int
    fun getViewParent(): ViewParent
    fun getVideoHelper(): ExoPlayerViewHelper

    override fun initialize(container: Container, playbackInfo: PlaybackInfo) {
        getVideoHelper().initialize(container, playbackInfo)
    }


    override fun isPlaying(): Boolean {
        return  getVideoHelper().isPlaying ?: false
    }

    override fun getPlayerView() = getVideoView()

    override fun pause() {
        getVideoHelper().pause()
    }

    override fun wantsToPlay(): Boolean {
        return ToroUtil.visibleAreaOffset(this, getViewParent()) >= 0.85
    }

    override fun play() {
        getVideoHelper().play()
    }

    override fun getCurrentPlaybackInfo(): PlaybackInfo {
        return getVideoHelper().latestPlaybackInfo ?: PlaybackInfo()
    }

    override fun release() {
//        if (videoHelper != null) {
            getVideoHelper().release()
//            videoHelper = null
//        }
    }

    override fun getPlayerOrder() = getAdapterPosition()
}