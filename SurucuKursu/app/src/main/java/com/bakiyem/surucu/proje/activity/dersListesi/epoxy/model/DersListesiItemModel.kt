package com.bakiyem.surucu.proje.activity.dersListesi.epoxy.model

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.model.dersListesi.Response4DersListesi
import com.bakiyem.surucu.proje.model.derslerim.Response4Derslerim
import com.bakiyem.surucu.proje.utils.ext.regular
import com.bakiyem.surucu.proje.utils.ext.semibold

@EpoxyModelClass(layout = R.layout.holder_ders_listesi_item)
abstract class DersListesiItemModel: EpoxyModelWithHolder<DersListesiItemModel.Holder>() {

    @EpoxyAttribute
    lateinit var dersListesiItem: Response4DersListesi

    @EpoxyAttribute
    lateinit var listener: (Response4DersListesi) -> Unit

    @EpoxyAttribute
    var position: Int = -1

    override fun bind(holder: Holder) {
        super.bind(holder)

        with(dersListesiItem) {
            holder.tvDersListesiAdi.text = baslik
            holder.tvCountDersListesi.text = position.toString()
        }

        holder.cvDersListesi.setOnClickListener {
            listener.invoke(dersListesiItem)
        }

        holder.tvDersListesiAdi.regular()
        holder.tvCountDersListesi.semibold()


    }

    class Holder : EpoxyHolder() {

        lateinit var tvCountDersListesi: TextView
        lateinit var tvDersListesiAdi: TextView
        lateinit var cvDersListesi: CardView

        override fun bindView(itemView: View) {
            tvCountDersListesi = itemView.findViewById(R.id.tv_countDersListesi)
            tvDersListesiAdi = itemView.findViewById(R.id.tv_dersListesiAdi)
            cvDersListesi = itemView.findViewById(R.id.cv_dersListesi)

        }
    }

}