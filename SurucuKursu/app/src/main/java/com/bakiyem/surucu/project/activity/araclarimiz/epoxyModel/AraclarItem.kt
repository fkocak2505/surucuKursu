package com.bakiyem.surucu.project.activity.araclarimiz.epoxyModel

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.model.araclar.Response4Araclar
import com.bakiyem.surucu.project.utils.ext.loadImage
import com.bakiyem.surucu.project.utils.ext.regular
import com.bakiyem.surucu.project.utils.ext.semibold

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