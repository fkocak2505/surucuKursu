package com.bakiyem.surucu.project.fragments.main.epoxyModel.single

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.fragments.main.data.DummyData
import com.bakiyem.surucu.project.utils.ext.regular
import com.bakiyem.surucu.project.utils.ext.semibold

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

            holder.tvSingleItemTitle.semibold()
            holder.tvSingleItemDesc.regular()
            holder.tvStart.semibold()
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
        lateinit var tvStart: TextView


        override fun bindView(itemView: View) {
            ivSingleItemImage = itemView.findViewById(R.id.iv_singleItemImage)
            tvSingleItemTitle = itemView.findViewById(R.id.tv_singleItemTitle)
            tvSingleItemDesc = itemView.findViewById(R.id.tv_singleItemDesc)
            cvSingleItem = itemView.findViewById(R.id.cv_singleItem)
            tvStart = itemView.findViewById(R.id.tv_start)



        }
    }

}