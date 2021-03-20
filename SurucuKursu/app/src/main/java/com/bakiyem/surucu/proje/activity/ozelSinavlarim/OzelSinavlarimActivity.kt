package com.bakiyem.surucu.proje.activity.ozelSinavlarim

import android.annotation.SuppressLint
import android.content.Intent
import android.widget.SeekBar
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.activity.denemeSinavi.DenemeSinaviActivity
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_ozel_sinavlar.*

class OzelSinavlarimActivity : BaseActivity() {

    lateinit var ozelSinavVM: OzelSinavVM

    private var ilkYardimCount = 0
    private var trafikCount = 0
    private var motorCount = 0
    private var adapCount = 0

    override fun getLayoutID(): Int = R.layout.activity_ozel_sinavlar

    override fun initVM() {
        ozelSinavVM = ViewModelProviders.of(this).get(OzelSinavVM::class.java)
    }

    override fun initChangeFont() {

    }

    override fun initReq() {

    }

    override fun initVMListener() {
        ozelSinavVM.ozelSinavLD.observe(this, {
            it?.let {
                DenemeSinaviActivity.start(this, "3", "", false,  "Özel Sınav",  it)
            } ?: run {
                toast("Error ozel sinav genarate service")
            }
        })
    }

    override fun onCreateMethod() {
        goBack()

        handleSeekBarChangeListener()

        setSeekBarValue()

        handleViewClickListener()
    }

    private fun goBack() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun handleViewClickListener() {
        btn_startExam.setOnClickListener {
            ozelSinavVM.getOzelSinav(
                ilkYardimCount.toString(),
                trafikCount.toString(),
                motorCount.toString(),
                adapCount.toString()
            )
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setSeekBarValue() {
        tv_totalQuestionCount.text =
            "Toplam Soru Sayısı: ${ilkYardimCount + trafikCount + motorCount + adapCount}"
    }

    private fun handleSeekBarChangeListener() {
        sb_ilkYardim.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                ilkYardimCount = progress

                val value = progress * (seekBar!!.width - 2 * seekBar.thumbOffset) / seekBar.max
                tv_ilkYardimQuestionCount.text = "$progress"
                tv_ilkYardimQuestionCount.x = seekBar.x + value + seekBar.thumbOffset / 2

                setSeekBarValue()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        sb_trafik.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                trafikCount = progress

                val value = progress * (seekBar!!.width - 2 * seekBar.thumbOffset) / seekBar.max
                tv_trafikQuestionCount.text = "$progress"
                tv_trafikQuestionCount.x = seekBar.x + value + seekBar.thumbOffset / 2

                setSeekBarValue()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        sb_motor.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                motorCount = progress

                val value = progress * (seekBar!!.width - 2 * seekBar.thumbOffset) / seekBar.max
                tv_motorQuestionCount.text = "$progress"
                tv_motorQuestionCount.x = seekBar.x + value + seekBar.thumbOffset / 2

                setSeekBarValue()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        sb_adap.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                adapCount = progress

                val value = progress * (seekBar!!.width - 2 * seekBar.thumbOffset) / seekBar.max
                tv_adapQuestionCount.text = "$progress"
                tv_adapQuestionCount.x = seekBar.x + value + seekBar.thumbOffset / 2

                setSeekBarValue()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }
}