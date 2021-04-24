package com.bakiyem.surucu.proje.activity.odemeBilgilerim.epoxy.model

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.VideoView
import com.airbnb.epoxy.*
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.activity.odemeBilgilerim.epoxy.model.controller.GridController4OdemeBilgileri
import com.bakiyem.surucu.proje.fragments.main.controller.CListener
import com.bakiyem.surucu.proje.fragments.main.data.DummyData
import com.bakiyem.surucu.proje.fragments.main.epoxyModel.subController.GridController
import com.bakiyem.surucu.proje.model.odemeBilgilerim.Response4OdemeBilgileri

@EpoxyModelClass(layout = R.layout.holder_grid_item_odeme_bilgileri)
abstract class GridItem4OdemeBilgilerimModel: EpoxyModelWithHolder<GridItem4OdemeBilgilerimModel.GridHolder>(),
    CListener<Response4OdemeBilgileri> {

    @EpoxyAttribute
    lateinit var context: Context

    @EpoxyAttribute
    lateinit var odemeBilgilerList: MutableList<Response4OdemeBilgileri>

    @EpoxyAttribute
    lateinit var listener: (Response4OdemeBilgileri) -> Unit

    override fun bind(holder: GridHolder) {
        super.bind(holder)

        val controller = GridController4OdemeBilgileri(context, this)
        holder.ervGridOdemeListesi.setController(controller)
        holder.ervGridOdemeListesi.addItemDecoration(EpoxyItemSpacingDecorator(10))
        controller.gridData = odemeBilgilerList

    }

    class GridHolder : EpoxyHolder() {

        lateinit var ervGridOdemeListesi: EpoxyRecyclerView

        override fun bindView(itemView: View) {
            ervGridOdemeListesi = itemView.findViewById(R.id.erv_grid_odeme_listesi)
        }
    }
/*
    override fun onSelected(data: Response4OdemeBilgileri) {
        listener.invoke(data)
    }*/

    override fun onSelected(
        data: Response4OdemeBilgileri,
        videoView: VideoView?,
        placeHolder: ImageView?,
        playIcon: ImageView?
    ) {
        listener.invoke(data)
    }
}