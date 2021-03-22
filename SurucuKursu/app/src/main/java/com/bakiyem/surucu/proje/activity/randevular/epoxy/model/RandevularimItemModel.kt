package com.bakiyem.surucu.proje.activity.randevular.epoxy.model

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.model.randevularim.Response4Randevularim
import com.bakiyem.surucu.proje.utils.ext.regular
import com.bakiyem.surucu.proje.utils.ext.semibold

@EpoxyModelClass(layout = R.layout.holder_randevularim)
abstract class RandevularimItemModel : EpoxyModelWithHolder<RandevularimItemModel.RandevuHolder>() {

    @EpoxyAttribute
    lateinit var randevu: Response4Randevularim


    @SuppressLint("SetTextI18n")
    override fun bind(holder: RandevuHolder) {
        super.bind(holder)

        with(randevu) {
            holder.tvRandevuDate.text = "$tarih /"
            holder.tvRandevuTime.text = "$saatBaslama : $saatBitis "
            holder.tvRandevuTitle.text = "$egitmen ile direksiyon dersiniz bulunmaktadır"

            if (durum == "Pasif")
                holder.ivRandevuIsActive.visibility = View.VISIBLE
            else
                holder.ivRandevuIsActive.visibility = View.GONE
        }

        holder.tvRandevuDate.semibold()
        holder.tvRandevuTime.regular()
        holder.tvRandevuTitle.regular()
    }

    class RandevuHolder : EpoxyHolder() {

        lateinit var tvRandevuDate: TextView
        lateinit var tvRandevuTime: TextView
        lateinit var tvRandevuTitle: TextView
        lateinit var ivRandevuIsActive: ImageView

        override fun bindView(itemView: View) {
            tvRandevuDate = itemView.findViewById(R.id.tv_randevuDate)
            tvRandevuTime = itemView.findViewById(R.id.tv_randevuTime)
            tvRandevuTitle = itemView.findViewById(R.id.tv_randevuTitle)
            ivRandevuIsActive = itemView.findViewById(R.id.iv_randevuIsActive)


        }
    }

}