package com.bakiyem.surucu.proje.activity.galeri

import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.base.activity.BaseActivity
import com.bakiyem.surucu.proje.model.galeri.Response4Galeri
import kotlinx.android.synthetic.main.activity_galeri.*

class GaleriActivity : BaseActivity() {

    lateinit var galeriVM: GaleriVM

    override fun getLayoutID(): Int = R.layout.activity_galeri

    override fun initVM() {
        galeriVM = ViewModelProviders.of(this).get(GaleriVM::class.java)
    }

    override fun initChangeFont() {

    }

    override fun initReq() {
        galeriVM.getGaleri()
    }

    override fun initVMListener() {
        galeriVM.galeriLD.observe(this, {
            it?.let {
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
            Toast.makeText(applicationContext, it.resim, Toast.LENGTH_SHORT).show()
        }
        recycler_view?.adapter = adapter
        layoutManager.spanSizeLookup = adapter.spanSizeLookup

        adapter.setItems(prepareGaleriDataWithColumns(listOfGaleriData))
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
        }else {
            listOfGaleriData.forEachIndexed { index, response4Galeri ->
                if(index % 2 == 0){
                    finalData.add(
                        DataModel(
                            response4Galeri.resim!!,
                            3
                        )
                    )
                }else {
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