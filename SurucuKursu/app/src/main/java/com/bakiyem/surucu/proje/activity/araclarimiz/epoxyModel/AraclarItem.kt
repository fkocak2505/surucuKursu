package com.bakiyem.surucu.proje.activity.araclarimiz.epoxyModel

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.model.araclar.Response4Araclar
import com.bakiyem.surucu.proje.utils.ext.loadImage
import com.bakiyem.surucu.proje.utils.ext.regular
import com.bakiyem.surucu.proje.utils.ext.semibold

@EpoxyModelClass(layout = R.layout.holder_araclar_item)
abstract class AraclarItem : EpoxyModelWithHolder<AraclarItem.Holder>() {

    @EpoxyAttribute
    lateinit var aracItem: Response4Araclar

    override fun bind(holder: Holder) {
        super.bind(holder)

        with(aracItem) {
            holder.ivAracImage.loadImage(resim)
            holder.tvAracSinif.text = sinif
            holder.tvAracMarkaModel.text = model
        }

        holder.tvAracSinif.semibold()
        holder.tvAracMarkaModel.regular()
    }

    class Holder : EpoxyHolder() {

        lateinit var tvAracSinif: TextView
        lateinit var tvAracMarkaModel: TextView
        lateinit var ivAracImage: ImageView

        override fun bindView(itemView: View) {
            tvAracSinif = itemView.findViewById(R.id.tv_aracSinif)
            tvAracMarkaModel = itemView.findViewById(R.id.tv_aracMarkaModel)
            ivAracImage = itemView.findViewById(R.id.iv_aracImage)

        }
    }

}