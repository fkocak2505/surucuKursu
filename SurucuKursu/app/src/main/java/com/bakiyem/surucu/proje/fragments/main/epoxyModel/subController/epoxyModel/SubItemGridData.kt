package com.bakiyem.surucu.proje.fragments.main.epoxyModel.subController.epoxyModel

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.fragments.main.dataModel.StaticData
import com.bakiyem.surucu.proje.utils.ext.regular
import com.bakiyem.surucu.proje.utils.ext.semibold
import com.google.android.material.card.MaterialCardView

@EpoxyModelClass(layout = R.layout.holder_sub_grid_item)
abstract class SubItemGridData : EpoxyModelWithHolder<SubItemGridData.GridHolder>() {

    @EpoxyAttribute
    lateinit var subStaticData: StaticData

    @EpoxyAttribute
    lateinit var listener: (String) -> Unit

    override fun bind(holder: GridHolder) {
        super.bind(holder)
        with(subStaticData) {
            holder.title.text = title
            holder.tv_itemDesc.text = desc
            holder.profileImage.setImageResource(image)

            holder.title.semibold()
            holder.tv_itemDesc.regular()
            holder.tvStart.semibold()
        }

        holder.cl.setOnClickListener {
            listener.invoke(subStaticData.title)
        }
    }

    class GridHolder : EpoxyHolder() {

        lateinit var profileImage: ImageView
        lateinit var title: TextView
        lateinit var cl: CardView
        lateinit var tv_itemDesc: TextView
        lateinit var tvStart: TextView

        override fun bindView(itemView: View) {
            profileImage = itemView.findViewById(R.id.grid_item_icon)
            title = itemView.findViewById(R.id.grid_item_title)
            tv_itemDesc = itemView.findViewById(R.id.tv_itemDesc)
            cl = itemView.findViewById(R.id.cv_gridItem)
            tvStart = itemView.findViewById(R.id.tv_start)



        }
    }

}