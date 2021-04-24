package com.bakiyem.surucu.proje.activity.galeri

import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.model.galeri.Response4Galeri
import com.bakiyem.surucu.proje.model.kurs.Response4Kurs
import com.bakiyem.surucu.proje.utils.ext.loadImage
import com.bakiyem.surucu.proje.utils.ext.semibold
import com.orhanobut.hawk.Hawk
import com.stfalcon.frescoimageviewer.ImageViewer
import kotlinx.android.synthetic.main.activity_galeri.*
import kotlinx.android.synthetic.main.toolbar_layout.*


class GaleriActivity : BaseActivity() {

    lateinit var galeriVM: GaleriVM

    lateinit var listOfGaleri: MutableList<Response4Galeri>

    override fun getLayoutID(): Int = R.layout.activity_galeri

    override fun initVM() {
        galeriVM = ViewModelProviders.of(this).get(GaleriVM::class.java)
    }

    override fun initChangeFont() {
        tv_hugeTitle.semibold()

        iv_rootImage.loadImage(Hawk.get<Response4Kurs>("kursBilgisi").logo)
    }

    override fun initReq() {
        galeriVM.getGaleri()
    }

    override fun initVMListener() {
        prepareWithBaseVM(galeriVM)
        galeriVM.galeriLD.observe(this, {
            it?.let {
                listOfGaleri = mutableListOf()
                listOfGaleri = it
                prepareGaleriData(it)
            } ?: run {
                Toast.makeText(applicationContext, "awfawf", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateMethod() {
        goBack()
    }

    private fun prepareGaleriData(listOfGaleriData: MutableList<Response4Galeri>) {
        val layoutManager = GridLayoutManager(context, 10)
        recycler_view?.layoutManager = layoutManager
        val adapter = GalleryAdapter(context) {
            clickImage(it)
        }
        recycler_view?.adapter = adapter
        layoutManager.spanSizeLookup = adapter.spanSizeLookup

        adapter.setItems(prepareGaleriDataWithColumns(listOfGaleriData))
    }

    private fun clickImage(currentDataModel: DataModel) {
        val strOfGaleriList: MutableList<String> = mutableListOf()

        var index = 0
        listOfGaleri.forEachIndexed { i, response4Galeri ->
            if(currentDataModel.resim == response4Galeri.resim)
                index = i
        }

        for(i in index until listOfGaleri.size){
            strOfGaleriList.add(listOfGaleri[i].resim!!)
        }

        for(i in 0 until index){
            strOfGaleriList.add(listOfGaleri[i].resim!!)
        }


        ImageViewer.Builder(this, strOfGaleriList)
            .setStartPosition(0)
            .setBackgroundColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.transparentGreyColor
                )
            )
            .show()
    }

    private fun prepareGaleriDataWithColumns(listOfGaleriData: MutableList<Response4Galeri>): MutableList<DataModel> {
        val finalData: MutableList<DataModel> = mutableListOf()
        val size = listOfGaleriData.size
        val isEven = size % 2 == 0

        if (!isEven) {
            for (i in 0 until size - 1) {
                if (i % 2 == 0) {
                    finalData.add(
                        DataModel(
                            listOfGaleriData[i].resim!!,
                            3
                        )
                    )
                } else {
                    finalData.add(
                        DataModel(
                            listOfGaleriData[i].resim!!,
                            7
                        )
                    )
                }

            }
        } else {
            listOfGaleriData.forEachIndexed { index, response4Galeri ->
                if (index % 2 == 0) {
                    finalData.add(
                        DataModel(
                            response4Galeri.resim!!,
                            3
                        )
                    )
                } else {
                    finalData.add(
                        DataModel(
                            response4Galeri.resim!!,
                            7
                        )
                    )
                }

            }
        }

        return finalData


    }

    private fun goBack() {
        iv_back.setOnClickListener {
            onBackPressed()
        }
    }
}