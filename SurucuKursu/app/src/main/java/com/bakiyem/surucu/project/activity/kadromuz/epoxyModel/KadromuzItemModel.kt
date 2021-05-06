package com.bakiyem.surucu.project.activity.kadromuz.epoxyModel

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.model.kadromuz.Response4Kadromuz
import com.bakiyem.surucu.project.utils.ext.loadImage
import com.bakiyem.surucu.project.utils.ext.regular
import com.bakiyem.surucu.project.utils.ext.semibold

@EpoxyModelClass(layout = R.layout.holder_kadromuz_item)
abstract class KadromuzItemModel: EpoxyModelWithHolder<KadromuzItemModel.Holder>() {

    @EpoxyAttribute
    lateinit var kadromuzItem: Response4Kadromuz

    override fun bind(holder: Holder) {
        super.bind(holder)

        with(kadromuzItem) {
            holder.ivImageKadromuz.loadImage(resim)
            holder.tvTitleKadromuz.text = gorevi
            holder.tvDescKadromuz.text = adSoyad
        }

        holder.tvTitleKadromuz.semibold()
        holder.tvDescKadromuz.regular()
    }

    class Holder : EpoxyHolder() {

        lateinit var ivImageKadromuz: ImageView
        lateinit var tvTitleKadromuz: TextView
        lateinit var tvDescKadromuz: TextView

        override fun bindView(itemView: View) {

            ivImageKadromuz = itemView.findViewById(R.id.iv_imageKadromuz)
            tvTitleKadromuz = itemView.findViewById(R.id.tv_titleKadromuz)
            tvDescKadromuz = itemView.findViewById(R.id.tv_descKadromuz)

        }
    }

}