package com.bakiyem.surucu.proje.fragments.main.epoxyModel.single

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.fragments.main.data.DummyData
import com.bakiyem.surucu.proje.fragments.main.dataModel.StaticData

@EpoxyModelClass(layout = R.layout.holder_single_item)
abstract class SingleItemModel : EpoxyModelWithHolder<SingleItemModel.SingleHolder>() {


    override fun bind(holder: SingleHolder) {
        super.bind(holder)

        with(DummyData.singleData) {
            holder.ivSingleItemImage.setImageResource(image)
            holder.tvSingleItemTitle.text = title
            holder.tvSingleItemDesc.text = desc
        }
    }

    class SingleHolder : EpoxyHolder() {

        lateinit var ivSingleItemImage: ImageView
        lateinit var tvSingleItemTitle: TextView
        lateinit var tvSingleItemDesc: TextView


        override fun bindView(itemView: View) {
            ivSingleItemImage = itemView.findViewById(R.id.iv_singleItemImage)
            tvSingleItemTitle = itemView.findViewById(R.id.tv_singleItemTitle)
            tvSingleItemDesc = itemView.findViewById(R.id.tv_singleItemDesc)
        }
    }

}