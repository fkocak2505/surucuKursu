package com.bakiyem.surucu.proje.activity.randevuEkle

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.model.kurs.Response4Kurs
import com.bakiyem.surucu.proje.model.randevuEkle.Response4Egitmen
import com.bakiyem.surucu.proje.model.randevuSaat.Response4Saat
import com.bakiyem.surucu.proje.utils.ext.loadImage
import com.bakiyem.surucu.proje.utils.ext.regular
import com.bakiyem.surucu.proje.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.activity_randevu_ekle.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import org.greenrobot.eventbus.EventBus
import java.text.SimpleDateFormat
import java.util.*


class RandevuEkleActivity: BaseActivity() {

    private val myCalendar: Calendar = Calendar.getInstance()

    lateinit var randevuEkleVM: RandevuEkleVM

    var tarihData: String? = ""
    var egitmenId: String? = ""
    var saatId: String? = ""

    override fun getLayoutID(): Int = R.layout.activity_randevu_ekle

    override fun initVM() {
        randevuEkleVM = ViewModelProviders.of(this).get(RandevuEkleVM::class.java)
    }

    override fun initChangeFont() {
        tv_randevuTarihiInfo.semibold()
        tv_randevuEgitmenInfo.semibold()
        tv_createRandevu.semibold()
        et_randevuTarih.regular()
        tv_hugeTitle.semibold()

        iv_rootImage.loadImage(Hawk.get<Response4Kurs>("kursBilgisi").logo)
    }

    override fun initReq() {

    }

    override fun initVMListener() {
        randevuEkleVM.egitmenListLD.observe(this, {
            it?.let {
                tv_randevuEgitmenInfo.visibility = View.VISIBLE
                cl_randevuEgitmen.visibility = View.VISIBLE

                prepareEgitmenListData(it)

            }?: run{
                toast("Error when fetched egitmen list")
            }
        })

        randevuEkleVM.saatListLD.observe(this, {
            it?.let {

                cv_saat.visibility = View.VISIBLE
                btn_createRandevu.visibility = View.VISIBLE

                prepareSaatData(it)
            }?: run{

            }
        })

        randevuEkleVM.addRandevuLD.observe(this, {
            it?.let {
                toast(it.detay!!)
            }?: run{
                toast("Error Add Randevu")
            }
            EventBus.getDefault().post("aaa")
            onBackPressed()
        })
    }

    override fun onCreateMethod() {

        val date =
            OnDateSetListener { view, year, monthOfYear, dayOfMonth -> // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateLabel()
            }

        et_randevuTarih.setOnClickListener {
            val dp = DatePickerDialog(
                this@RandevuEkleActivity, date, myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            )
            dp.datePicker.minDate = System.currentTimeMillis()
            dp.show()
        }

        handleAddRandevuClickListener()

        goBack()

    }

    private fun goBack(){
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun handleAddRandevuClickListener(){
        btn_createRandevu.setOnClickListener {
            randevuEkleVM.addRandevu(tarihData!!, egitmenId!!, saatId!!)
        }
    }

    private fun updateLabel() {
        val myFormat = "dd.MM.yyyy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        et_randevuTarih.text = sdf.format(myCalendar.time)

        tarihData = sdf.format(myCalendar.time)

        randevuEkleVM.getEgitmenList(tarihData!!)

    }

    private fun prepareEgitmenListData(egitmenList: MutableList<Response4Egitmen>){
        s_egitmen.adapter = CSpinnerAdapter(this, egitmenList.map { it.adSoyad })
        //ArrayAdapter(context, R.layout.item_country_spinner, supportHeadlines.map { it.name })
        s_egitmen.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                egitmenId = egitmenList[position].keyi
                getEgitmenSaat()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        }
    }

    private fun getEgitmenSaat(){
        randevuEkleVM.getEgitmenSaat(tarihData!!, egitmenId!!)
    }

    private fun prepareSaatData(saatList: MutableList<Response4Saat>){
        rv_saat.setHasFixedSize(true)
        val layoutManager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 3)
        rv_saat.layoutManager = layoutManager
        val adapter = DataAdapter2(applicationContext, saatList)
        rv_saat.addItemDecoration(MiddleDividerItemDecoration(applicationContext, MiddleDividerItemDecoration.ALL))

        rv_saat.adapter = adapter

        adapter.onShopSelected = { resposne4Saat ->
            saatId = resposne4Saat.id
        }
    }

    /*override fun onResume() {
        super.onResume()
        //startActivity(intent)
    }*/
}