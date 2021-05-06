package com.bakiyem.surucu.project.activity.video

import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.base.activity.BaseActivity
import com.bakiyem.surucu.project.fragments.main.controller.CListener
import com.bakiyem.surucu.project.model.kurs.Response4Kurs
import com.bakiyem.surucu.project.model.video.Response4Video
import com.bakiyem.surucu.project.utils.ext.loadImage
import com.bakiyem.surucu.project.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_videolar.*
import kotlinx.android.synthetic.main.activity_videolar.iv_back
import kotlinx.android.synthetic.main.activity_videolar.tv_hugeTitle
import kotlinx.android.synthetic.main.toolbar_layout.*

class VideolarimActivity: BaseActivity(), CListener<Response4Video> {

    private lateinit var videolarimVM: VideolarimVM

    override fun getLayoutID(): Int = R.layout.activity_videolar

    override fun initVM() {
        videolarimVM = ViewModelProviders.of(this).get(VideolarimVM::class.java)
    }

    override fun initChangeFont() {
        tv_hugeTitle.semibold()

        iv_rootImage.loadImage(Hawk.get<Response4Kurs>("kursBilgisi").logo)
    }

    override fun initReq() {
        videolarimVM.getVideolar()
    }

    override fun initVMListener() {
        videolarimVM.videolarLD.observe(this, {
            it?.let {
                prepareVideoData(it)
            } ?: run {
                toast("Error videolar api..")
                onBackPressed()
            }
        })
    }

    override fun onCreateMethod() {
        iv_back.setOnClickListener {
            onBackPressed()
        }

    }

    private fun prepareVideoData(listOfVideo: MutableList<Response4Video>){
        val videolarimController = VideolarimController(applicationContext, lifecycle, this)
        videolarimController.videolarim = listOfVideo
        erv_videolar.setController(videolarimController)
    }


    override fun onSelected(
        data: Response4Video,
        videoView: VideoView?,
        placeHolder: ImageView?,
        playIcon: ImageView?
    ) {
        val uri: Uri = Uri.parse(data.link)
        videoView?.setMediaController(MediaController(this))
        videoView?.setVideoURI(uri)
        videoView?.requestFocus()
        videoView?.start()

        placeHolder?.visibility = View.GONE
        playIcon?.visibility = View.GONE

    }
}