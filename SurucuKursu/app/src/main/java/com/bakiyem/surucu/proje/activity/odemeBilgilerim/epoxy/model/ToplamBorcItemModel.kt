package com.bakiyem.surucu.proje.activity.odemeBilgilerim.epoxy.model

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.model.odemeBilgilerim.Response4BorcOzet
import com.bakiyem.surucu.proje.utils.ext.regular
import com.bakiyem.surucu.proje.utils.ext.semibold

@EpoxyModelClass(layout = R.layout.holder_toplam_borc)
abstract class ToplamBorcItemModel: EpoxyModelWithHolder<ToplamBorcItemModel.BorcHolder>() {

    @EpoxyAttribute
    lateinit var borcOzet: Response4BorcOzet


    override fun bind(holder: BorcHolder) {
        super.bind(holder)

        with(borcOzet){
            holder.tvToplamBorc.text = toplamBorc
            holder.tvTahsilEdilen.text = tahsilEdilen
            holder.tvKalan.text = kalanBorcu
        }

        holder.tvToplamBorc.regular()
        holder.tvToplamBorcInfo.semibold()
        holder.tvTahsilEdilen.regular()
        holder.tvTahsilEdilenInfo.semibold()
        holder.tvKalan.semibold()
        holder.tvKalanInfo.semibold()
    }

    class BorcHolder : EpoxyHolder() {

        lateinit var tvToplamBorcInfo: TextView
        lateinit var tvToplamBorc: TextView
        lateinit var tvTahsilEdilenInfo: TextView
        lateinit var tvTahsilEdilen: TextView
        lateinit var tvKalanInfo: TextView
        lateinit var tvKalan: TextView

        override fun bindView(itemView: View) {
            tvToplamBorcInfo = itemView.findViewById(R.id.tv_toplamBorcInfo)
            tvToplamBorc = itemView.findViewById(R.id.tv_toplamBorc)
            tvTahsilEdilenInfo = itemView.findViewById(R.id.tv_tahsilEdilenInfo)
            tvTahsilEdilen = itemView.findViewById(R.id.tv_tahsilEdilen)
            tvKalanInfo = itemView.findViewById(R.id.tv_kalanInfo)
            tvKalan = itemView.findViewById(R.id.tv_kalan)







        }
    }

}