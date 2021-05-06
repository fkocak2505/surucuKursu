package com.bakiyem.surucu.project.activity.dersKategorileri.epoxy.model

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.model.derslerim.Response4Derslerim
import com.bakiyem.surucu.project.utils.ext.loadImage
import com.bakiyem.surucu.project.utils.ext.semibold


@EpoxyModelClass(layout = R.layout.holder_derslerim_item)
abstract class DersItemModel: EpoxyModelWithHolder<DersItemModel.Holder>() {

    @EpoxyAttribute
    lateinit var context: Context

    @EpoxyAttribute
    lateinit var dersItem: Response4Derslerim

    @EpoxyAttribute
    lateinit var listener: (Response4Derslerim) -> Unit

    override fun bind(holder: Holder) {
        super.bind(holder)

        with(dersItem) {
            holder.tvDersAdi.text = dersAdi
            holder.ivImage.loadImage(imageUrl)
            holder.ivIcon.loadImage(iconUrl)

        }

        holder.cvDersItem.setOnClickListener {
            listener.invoke(dersItem)
        }

        holder.tvDersAdi.semibold()

    }

    class Holder : EpoxyHolder() {

        lateinit var tvDersAdi: TextView
        lateinit var ivImage: ImageView
        lateinit var ivIcon: ImageView
        lateinit var cvDersItem: CardView

        override fun bindView(itemView: View) {
            tvDersAdi = itemView.findViewById(R.id.tv_dersAdi)
            ivImage = itemView.findViewById(R.id.iv_image)
            ivIcon = itemView.findViewById(R.id.iv_icon)
            cvDersItem = itemView.findViewById(R.id.cv_dersItem)

        }
    }

}