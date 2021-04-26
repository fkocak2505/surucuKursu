package com.bakiyem.surucu.proje.activity.randevular.epoxy.model

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.model.randevularim.Response4Randevularim
import com.bakiyem.surucu.proje.utils.ext.regular
import com.bakiyem.surucu.proje.utils.ext.semibold
import java.text.SimpleDateFormat
import java.util.*

@EpoxyModelClass(layout = R.layout.holder_randevularim)
abstract class RandevularimItemModel : EpoxyModelWithHolder<RandevularimItemModel.RandevuHolder>() {

    @EpoxyAttribute
    lateinit var randevu: Response4Randevularim

    @EpoxyAttribute
    lateinit var listener: (Response4Randevularim) -> Unit


    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun bind(holder: RandevuHolder) {
        super.bind(holder)

        with(randevu) {
            var baslamaSaati = ""
            var bitisSaati = ""
            var finalSaat = ""
            saatBaslama?.let {
                baslamaSaati = it
                finalSaat = baslamaSaati
            }

            saatBitis?.let {
                bitisSaati = it
                finalSaat = "$finalSaat : $bitisSaati"
            }

            holder.tvRandevuDate.text = "$tarih /"
            holder.tvRandevuTime.text = finalSaat
            holder.tvRandevuTitle.text = "$egitmen ile direksiyon dersiniz bulunmaktadır"

            if (durum == "Aktif"){
                holder.ivRandevuIsActive.visibility = View.VISIBLE
                holder.clIptalRandevu.visibility= View.GONE
            } else{
                val sdf = SimpleDateFormat("dd/MM/yyyy")
                if(System.currentTimeMillis() < sdf.parse(tarih).time ){
                    holder.ivRandevuIsActive.visibility = View.GONE
                    holder.clIptalRandevu.visibility= View.VISIBLE
                }else {
                    holder.ivRandevuIsActive.visibility = View.VISIBLE
                    holder.clIptalRandevu.visibility= View.GONE
                }

                /*holder.ivRandevuIsActive.visibility = View.GONE
                holder.clIptalRandevu.visibility= View.VISIBLE*/
            }
        }

        holder.clIptalRandevu.setOnClickListener {
            listener.invoke(randevu)
        }

        holder.tvRandevuDate.semibold()
        holder.tvRandevuTime.regular()
        holder.tvRandevuTitle.regular()
        holder.tvIptalEt.regular()
    }

    class RandevuHolder : EpoxyHolder() {

        lateinit var tvRandevuDate: TextView
        lateinit var tvRandevuTime: TextView
        lateinit var tvRandevuTitle: TextView
        lateinit var tvIptalEt: TextView
        lateinit var ivRandevuIsActive: ImageView
        lateinit var clIptalRandevu: ConstraintLayout



        override fun bindView(itemView: View) {
            tvRandevuDate = itemView.findViewById(R.id.tv_randevuDate)
            tvRandevuTime = itemView.findViewById(R.id.tv_randevuTime)
            tvRandevuTitle = itemView.findViewById(R.id.tv_randevuTitle)
            ivRandevuIsActive = itemView.findViewById(R.id.iv_randevuIsActive)
            clIptalRandevu = itemView.findViewById(R.id.cl_iptalRandevu)
            tvIptalEt = itemView.findViewById(R.id.tv_iptalEt)




        }
    }

}