package com.bakiyem.surucu.proje.fragments.main.epoxyModel

import android.view.View
import com.airbnb.epoxy.*
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.fragments.main.controller.CListener
import com.bakiyem.surucu.proje.fragments.main.data.DummyData
import com.bakiyem.surucu.proje.fragments.main.epoxyModel.subController.GridController

@EpoxyModelClass(layout = R.layout.holder_grid_item)
abstract class GridItemModel : EpoxyModelWithHolder<GridItemModel.GridHolder>(){

    @EpoxyAttribute
    lateinit var listener: (String) -> Unit


    override fun bind(holder: GridHolder) {
        super.bind(holder)

        val controller = GridController()
        holder.ervGrid.setController(controller)
        holder.ervGrid.addItemDecoration(EpoxyItemSpacingDecorator(10))
        controller.gridData = DummyData.gridData

    }

    class GridHolder : EpoxyHolder() {

        lateinit var ervGrid: EpoxyRecyclerView

        override fun bindView(itemView: View) {
            ervGrid = itemView.findViewById(R.id.erv_grid)
        }
    }


}