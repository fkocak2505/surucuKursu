package com.bakiyem.surucu.proje.fragments.main.epoxyModel.single

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.fragments.main.data.DummyData
import com.bakiyem.surucu.proje.fragments.main.dataModel.StaticData

@EpoxyModelClass(layout = R.layout.holder_single_item)
abstract class SingleItemModel : EpoxyModelWithHolder<SingleItemModel.SingleHolder>() {

    @EpoxyAttribute
    lateinit var listener: (String) -> Unit

    override fun bind(holder: SingleHolder) {
        super.bind(holder)

        with(DummyData.singleData) {
            holder.ivSingleItemImage.setImageResource(image)
            holder.tvSingleItemTitle.text = title
            holder.tvSingleItemDesc.text = desc
        }

        holder.cvSingleItem.setOnClickListener {
            listener.invoke(DummyData.singleData.title)
        }
    }

    class SingleHolder : EpoxyHolder() {

        lateinit var ivSingleItemImage: ImageView
        lateinit var tvSingleItemTitle: TextView
        lateinit var tvSingleItemDesc: TextView
        lateinit var cvSingleItem: CardView


        override fun bindView(itemView: View) {
            ivSingleItemImage = itemView.findViewById(R.id.iv_singleItemImage)
            tvSingleItemTitle = itemView.findViewById(R.id.tv_singleItemTitle)
            tvSingleItemDesc = itemView.findViewById(R.id.tv_singleItemDesc)
            cvSingleItem = itemView.findViewById(R.id.cv_singleItem)


        }
    }

}