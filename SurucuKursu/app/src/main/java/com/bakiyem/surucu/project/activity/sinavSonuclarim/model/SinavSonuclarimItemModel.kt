package com.bakiyem.surucu.project.activity.sinavSonuclarim.model

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.model.sinavSonuclarim.Response4SinavSonuclarim
import com.bakiyem.surucu.project.utils.ext.semibold

@EpoxyModelClass(layout = R.layout.holder_sinav_sonuc)
abstract class SinavSonuclarimItemModel: EpoxyModelWithHolder<SinavSonuclarimItemModel.Holder>()  {

    @EpoxyAttribute
    lateinit var sinavSonuclarim: Response4SinavSonuclarim

    override fun bind(holder: Holder) {
        super.bind(holder)

        with(sinavSonuclarim) {
            holder.tvSumQuizNumber.text = toplamSinav
            holder.tvSumQuiestionNumber.text = toplamSoru
            holder.tvCorrecntNumber.text = toplamDogruSoru
            holder.tvWrongNumber.text = toplamYanlisSoru
            holder.tvEmptyNumber.text = toplamBosSoru
            holder.tvTotalTimeNumber.text = toplamSure

            holder.tvSumQuizNumber.semibold()
            holder.tvSumQuiestionNumber.semibold()
            holder.tvCorrecntNumber.semibold()
            holder.tvWrongNumber.semibold()
            holder.tvEmptyNumber.semibold()
            holder.tvTotalTimeNumber.semibold()

            holder.tvSumQuizNumberInfo.semibold()
            holder.tvSumQuiestionNumberInfo.semibold()
            holder.tvCorrecntNumberInfo.semibold()
            holder.tvWrongNumberInfo.semibold()
            holder.tvEmptyNumberInfo.semibold()
            holder.tvTotalTimeNumberInfo.semibold()

        }

    }

    class Holder : EpoxyHolder() {

        lateinit var tvSumQuizNumber: TextView
        lateinit var tvSumQuiestionNumber: TextView
        lateinit var tvCorrecntNumber: TextView
        lateinit var tvWrongNumber: TextView
        lateinit var tvEmptyNumber: TextView
        lateinit var tvTotalTimeNumber: TextView

        lateinit var tvSumQuizNumberInfo: TextView
        lateinit var tvSumQuiestionNumberInfo: TextView
        lateinit var tvCorrecntNumberInfo: TextView
        lateinit var tvWrongNumberInfo: TextView
        lateinit var tvEmptyNumberInfo: TextView
        lateinit var tvTotalTimeNumberInfo: TextView


        override fun bindView(itemView: View) {
            tvSumQuizNumber = itemView.findViewById(R.id.tv_sumQuizNumber)
            tvSumQuiestionNumber = itemView.findViewById(R.id.tv_sumQuiestionNumber)
            tvCorrecntNumber = itemView.findViewById(R.id.tv_correcntNumber)
            tvWrongNumber = itemView.findViewById(R.id.tv_wrongNumber)
            tvEmptyNumber = itemView.findViewById(R.id.tv_emptyNumber)
            tvTotalTimeNumber = itemView.findViewById(R.id.tv_totalTimeNumber)

            tvSumQuizNumberInfo = itemView.findViewById(R.id.tv_sumQuizNumberInfo)
            tvSumQuiestionNumberInfo = itemView.findViewById(R.id.tv_sumQuiestionNumberInfo)
            tvCorrecntNumberInfo = itemView.findViewById(R.id.tv_correcntNumberInfo)
            tvWrongNumberInfo = itemView.findViewById(R.id.tv_wrongNumberInfo)
            tvEmptyNumberInfo = itemView.findViewById(R.id.tv_emptyNumberInfo)
            tvTotalTimeNumberInfo = itemView.findViewById(R.id.tv_totalTimeNumberInfo)


        }
    }

}