package com.bakiyem.surucu.project.fragments.main.epoxyModel

import android.view.View
import android.widget.ImageView
import android.widget.VideoView
import com.airbnb.epoxy.*
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.fragments.main.controller.CListener
import com.bakiyem.surucu.project.fragments.main.data.DummyData
import com.bakiyem.surucu.project.fragments.main.epoxyModel.subController.GridController

@EpoxyModelClass(layout = R.layout.holder_grid_item)
abstract class GridItemModel : EpoxyModelWithHolder<GridItemModel.GridHolder>(), CListener<Any>{

    @EpoxyAttribute
    lateinit var listener: (String) -> Unit


    override fun bind(holder: GridHolder) {
        super.bind(holder)

        val controller = GridController(this)
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

    /*override fun onSelected(data: Any) {
        when (data) {
            is String -> {
                listener.invoke(data)
            }
        }
    }*/

    override fun onSelected(
        data: Any,
        videoView: VideoView?,
        placeHolder: ImageView?,
        playIcon: ImageView?
    ) {
        when (data) {
            is String -> {
                listener.invoke(data)
            }
        }
    }
}