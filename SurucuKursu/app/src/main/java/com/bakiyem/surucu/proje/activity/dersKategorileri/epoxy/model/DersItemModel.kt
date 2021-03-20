package com.bakiyem.surucu.proje.activity.dersKategorileri.epoxy.model

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.model.derslerim.Response4Derslerim
import com.bakiyem.surucu.proje.utils.ext.semibold

@EpoxyModelClass(layout = R.layout.holder_derslerim_item)
abstract class DersItemModel: EpoxyModelWithHolder<DersItemModel.Holder>() {

    @EpoxyAttribute
    lateinit var dersItem: Response4Derslerim

    @EpoxyAttribute
    lateinit var listener: (Response4Derslerim) -> Unit

    override fun bind(holder: Holder) {
        super.bind(holder)

        with(dersItem) {
            holder.tvDersAdi.text = dersAdi
        }

        holder.cvDersItem.setOnClickListener {
            listener.invoke(dersItem)
        }

        holder.tvDersAdi.semibold()

    }

    class Holder : EpoxyHolder() {

        lateinit var tvDersAdi: TextView
        lateinit var cvDersItem: CardView

        override fun bindView(itemView: View) {
            tvDersAdi = itemView.findViewById(R.id.tv_dersAdi)
            cvDersItem = itemView.findViewById(R.id.cv_dersItem)

        }
    }

}