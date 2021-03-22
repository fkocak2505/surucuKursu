package com.bakiyem.surucu.proje.activity.faydaliBilgiler.epoxy.model

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.model.faydaliBilgiler.Response4FaydaliBilgiler
import com.bakiyem.surucu.proje.utils.ext.regular
import com.bakiyem.surucu.proje.utils.ext.semibold


@EpoxyModelClass(layout = R.layout.holder_faydali_bilgiler)
abstract class FaydaliBilgilerItemModel: EpoxyModelWithHolder<FaydaliBilgilerItemModel.Holder>() {

    @EpoxyAttribute
    lateinit var faydaliBilgilerItem: Response4FaydaliBilgiler

    @EpoxyAttribute
    lateinit var listener: (Response4FaydaliBilgiler) -> Unit

    @EpoxyAttribute
    var position: Int = -1

    override fun bind(holder: Holder) {
        super.bind(holder)

        with(faydaliBilgilerItem) {
            holder.tvDersListesiAdi.text = baslik
            holder.tvCountDersListesi.text = position.toString()
        }

        holder.cvDersListesi.setOnClickListener {
            listener.invoke(faydaliBilgilerItem)
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