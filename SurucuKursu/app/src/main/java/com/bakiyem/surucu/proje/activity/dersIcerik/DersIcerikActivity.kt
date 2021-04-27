package com.bakiyem.surucu.proje.activity.dersIcerik

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Handler
import android.text.Html
import android.text.Spannable
import android.widget.SeekBar
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.GlideImageGetter
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.model.dersIcerik.Response4DersIcerik
import com.bakiyem.surucu.proje.model.dersListesi.Response4DersListesi
import com.bakiyem.surucu.proje.model.derslerim.Response4Derslerim
import com.bakiyem.surucu.proje.model.kurs.Response4Kurs
import com.bakiyem.surucu.proje.utils.ext.loadImage
import com.bakiyem.surucu.proje.utils.ext.regular
import com.bakiyem.surucu.proje.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_ders_icerik.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import java.util.*


class DersIcerikActivity : BaseActivity(), MediaPlayer.OnPreparedListener {

    lateinit var dersIcerikVM: DersIcerikVM

    var mp: MediaPlayer? = null

    private var prepare = false

    lateinit var handler: Handler

    override fun getLayoutID(): Int = R.layout.activity_ders_icerik

    override fun initVM() {
        dersIcerikVM = ViewModelProviders.of(this).get(DersIcerikVM::class.java)
    }

    override fun initChangeFont() {
        dersKategoriItem?.let {
            tv_hugeTitle.text = it.dersAdi
            tv_dersAdiDetail.text = it.dersAdi
        } ?: run {
            tv_hugeTitle.text = "Detaylar"
            tv_dersAdiDetail.text = "Detaylar"
        }

        tv_hugeTitle.semibold()
        tv_dersAdiDetail.semibold()
        tv_dersiDinleInfo.semibold()
        tv_songTime.regular()
        tv_dersIcerik.regular()

        iv_rootImage.loadImage(Hawk.get<Response4Kurs>("kursBilgisi").logo)
    }

    override fun initReq() {
        dersKategoriItem?.let {
            dersListItem?.let {

                dersIcerikVM.getDersIcerik(it.keyi!!)

            } ?: run {
                toast("Error params dersListItem id")
                onBackPressed()
            }
        } ?: run {
            toast("Error params kategoriItem id")
            onBackPressed()
        }
    }

    override fun initVMListener() {
        prepareWithBaseVM(dersIcerikVM)
        dersIcerikVM.dersIcerikLD.observe(this, {
            it?.let {
                prepareDersIcerikData(it)
            } ?: run {
                toast("Error ders icerik")
                onBackPressed()
            }
        })
    }

    override fun onCreateMethod() {
        goback()

        handler = Handler()
        mp = MediaPlayer()

        handleSesDosyasiPlayPause()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun prepareDersIcerikData(listOfDersIcerik: MutableList<Response4DersIcerik>) {
        if (listOfDersIcerik.isNotEmpty()) {
            /*val htmlData = Html.fromHtml(listOfDersIcerik[0].detay!!).toString()
            tv_dersIcerik renderHtml htmlData*/

            /*tv_dersIcerik.setHtml(
                listOfDersIcerik[0].detay!!,
                HtmlHttpImageGetter(tv_dersIcerik, "n", true)
            );*/

            //val imageGetter = PicassoImageGetter(tv_dersIcerik, applicationContext)
            val imageGetter = GlideImageGetter(tv_dersIcerik, true)
            val html: Spannable = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(
                    listOfDersIcerik[0].detay!!,
                    Html.FROM_HTML_MODE_LEGACY,
                    imageGetter,
                    null
                ) as Spannable
            } else {
                Html.fromHtml(listOfDersIcerik[0].detay!!, imageGetter, null) as Spannable
            }
            tv_dersIcerik.text = html


            prepareSesDosyasi(listOfDersIcerik[0].sesDosyasi!!)

        }
    }

    private fun handleSesDosyasiPlayPause() {
        iv_play.setOnClickListener {
            if (mp?.isPlaying!!) {
                iv_play.setImageResource(R.drawable.ic_play)

                mp?.pause()

            } else {
                if (!prepare)
                    return@setOnClickListener

                iv_play.setImageResource(R.drawable.ic_pause)

                mp?.start()

                val timer = Timer()
                timer.scheduleAtFixedRate(object : TimerTask() {
                    @SuppressLint("SetTextI18n")
                    override fun run() {
                        mp?.let {
                            sb_song.progress = it.currentPosition

                            val totalDuration: Long = it.duration.toLong()
                            val currentDuration: Long = it.currentPosition.toLong()

                            runOnUiThread {
                                tv_songTime.text = "${milliSecondsToTimer(currentDuration)}/${
                                    milliSecondsToTimer(
                                        totalDuration
                                    )
                                }"
                            }
                        }
                    }
                }, 0, 1000)
            }
        }
    }

    private fun prepareSesDosyasi(sesDosyasi: String) {
        mp?.setDataSource(sesDosyasi)
        mp?.prepare()
        sb_song.max = mp?.duration!!

        prepare = false
        mp?.setOnPreparedListener(this)

        sb_song.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (prepare && fromUser) {
                    mp?.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        mp?.setOnCompletionListener {
            Toast.makeText(applicationContext, "aaaa", Toast.LENGTH_SHORT).show()
            killMediaPlayer()
        }
    }

    private fun milliSecondsToTimer(milliseconds: Long): String? {
        var finalTimerString = ""
        var secondsString = ""

        val hours = (milliseconds / (1000 * 60 * 60)).toInt()
        val minutes = (milliseconds % (1000 * 60 * 60)).toInt() / (1000 * 60)
        val seconds = (milliseconds % (1000 * 60 * 60) % (1000 * 60) / 1000).toInt()

        if (hours > 0) {
            finalTimerString = "$hours:"
        }

        secondsString = if (seconds < 10) {
            "0$seconds"
        } else {
            "" + seconds
        }
        finalTimerString = "$finalTimerString$minutes:$secondsString"

        return finalTimerString
    }

    private fun killMediaPlayer() {
        mp?.let {
            try {
                it.reset()
                it.release()
                mp = null
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onPrepared(mp: MediaPlayer?) {
        prepare = true
    }


    private fun goback() {
        iv_back.setOnClickListener {
            onBackPressed()
            //mp?.stop()
        }
    }

    override fun onStop() {
        super.onStop()
        mp?.let {
            if (it.isPlaying)
                it.stop()
        }
    }

    companion object {

        var dersKategoriItem: Response4Derslerim? = null
        var dersListItem: Response4DersListesi? = null

        fun start(
            context: Context?,
            dersKategoriItem: Response4Derslerim? = null,
            dersListItem: Response4DersListesi? = null
        ) {
            val starter = Intent(context, DersIcerikActivity::class.java)
            this.dersKategoriItem = dersKategoriItem
            this.dersListItem = dersListItem

            context!!.startActivity(starter)
        }

    }
}