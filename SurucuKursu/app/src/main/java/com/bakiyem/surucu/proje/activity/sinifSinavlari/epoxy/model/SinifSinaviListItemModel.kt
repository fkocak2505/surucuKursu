package com.bakiyem.surucu.proje.activity.sinifSinavlari.epoxy.model

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.activity.dersListesi.epoxy.model.DersListesiItemModel
import com.bakiyem.surucu.proje.model.sinifSinavi.Response4SinifSinavi

@EpoxyModelClass(layout = R.layout.holder_sinif_list_item)
abstract class SinifSinaviListItemModel : EpoxyModelWithHolder<SinifSinaviListItemModel.Holder>() {

    @EpoxyAttribute
    lateinit var sinifSinavi: Response4SinifSinavi

    @EpoxyAttribute
    lateinit var listener: (Response4SinifSinavi) -> Unit


    override fun bind(holder: Holder) {
        super.bind(holder)

        with(sinifSinavi) {
            holder.tvQuestionNumber.text = getQuestionsNumber(this).toString()
            holder.tvCorrectNumber.text = getCorrectNumber(this).toString()
            holder.tvWrongNumber.text = getWrongNumber(this).toString()
            holder.tvEmptyNumber.text = getEmptyNumber(this).toString()

            holder.tvScoreNumber.text = puan
            holder.tvBaslik.text = baslik
        }

        holder.btnAgainResolve.setOnClickListener {
            listener.invoke(sinifSinavi)
        }


    }

    private fun getQuestionsNumber(sinifSinavi: Response4SinifSinavi): Int {
        with(sinifSinavi) {
            return getCorrectNumber(this) + getWrongNumber(this) + getEmptyNumber(this)
        }
    }

    private fun getCorrectNumber(sinifSinavi: Response4SinifSinavi): Int {
        with(sinifSinavi) {
            return ilkYrdmDogru?.toInt()!! + trafikDogru?.toInt()!! + motorDogru?.toInt()!! + adapDogru?.toInt()!!
        }
    }

    private fun getWrongNumber(sinifSinavi: Response4SinifSinavi): Int {
        with(sinifSinavi) {
            return ilkYrdmYanlis?.toInt()!! + trafikYanlis?.toInt()!! + motorYanlis?.toInt()!! + adapYanlis?.toInt()!!
        }
    }

    private fun getEmptyNumber(sinifSinavi: Response4SinifSinavi): Int {
        with(sinifSinavi) {
            return ilkYrdmBos?.toInt()!! + trafikBos?.toInt()!! + motorBos?.toInt()!! + adapBos?.toInt()!!
        }
    }

    class Holder : EpoxyHolder() {

        lateinit var tvQuestionNumber: TextView
        lateinit var tvCorrectNumber: TextView
        lateinit var tvWrongNumber: TextView
        lateinit var tvEmptyNumber: TextView
        lateinit var tvScoreNumber: TextView
        lateinit var cvSinifSinav: CardView
        lateinit var tvAgainResolve: TextView
        lateinit var btnAgainResolve: ConstraintLayout
        lateinit var tvBaslik: TextView


        override fun bindView(itemView: View) {
            tvQuestionNumber = itemView.findViewById(R.id.tv_questionNumber)
            tvCorrectNumber = itemView.findViewById(R.id.tv_correctNumber)
            tvWrongNumber = itemView.findViewById(R.id.tv_wrongNumber)
            tvEmptyNumber = itemView.findViewById(R.id.tv_emptyNumber)
            tvScoreNumber = itemView.findViewById(R.id.tv_scoreNumber)
            cvSinifSinav = itemView.findViewById(R.id.cv_sinifSinav)
            btnAgainResolve = itemView.findViewById(R.id.btn_againResolve)
            tvAgainResolve = itemView.findViewById(R.id.tv_againResolve)
            tvBaslik = itemView.findViewById(R.id.tv_baslik)


        }
    }

}