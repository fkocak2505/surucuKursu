package com.bakiyem.surucu.proje.activity.video

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.annotation.NonNull
import androidx.lifecycle.Lifecycle
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.model.video.Response4Video
import com.bakiyem.surucu.proje.utils.ext.loadImage
import com.bakiyem.surucu.proje.utils.ext.semibold
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


@EpoxyModelClass(layout = R.layout.holder_videolarim)
abstract class VideolarimItemModel : EpoxyModelWithHolder<VideolarimItemModel.Holder>() {

    @EpoxyAttribute
    lateinit var context: Context

    @EpoxyAttribute
    lateinit var videoItem: Response4Video

    @EpoxyAttribute
    lateinit var listener: (Response4Video, VideoView?, ImageView, ImageView) -> Unit

    @EpoxyAttribute
    lateinit var lifecycle: Lifecycle

    @SuppressLint("SetJavaScriptEnabled")
    override fun bind(holder: Holder) {
        super.bind(holder)

        with(videoItem) {
            holder.tvVideoTitle.text = baslik
            //holder.ivPlaceholder.loadImage(resim)
        }

        val aa = videoItem.link?.split("/")
        val key = aa?.get(aa.size - 1)

        lifecycle.addObserver(holder.vvVideo)

        holder.vvVideo.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                youTubePlayer.cueVideo(key!!, 0f)
            }
        })

        /*holder.ivVideoPlay.setOnClickListener {

        }*/

        holder.tvVideoTitle.semibold()

    }

    class Holder : EpoxyHolder() {

        //lateinit var ivVideoPlay: ImageView
        lateinit var vvVideo: YouTubePlayerView
        //lateinit var ivPlaceholder: ImageView
        lateinit var tvVideoTitle: TextView

        override fun bindView(itemView: View) {
            tvVideoTitle = itemView.findViewById(R.id.tv_videoTitle)
            //ivVideoPlay = itemView.findViewById(R.id.iv_videoPlay)
            vvVideo = itemView.findViewById(R.id.youtube_player_view)
            //ivPlaceholder = itemView.findViewById(R.id.iv_placeholder)


        }
    }
}