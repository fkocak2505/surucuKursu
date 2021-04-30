package com.bakiyem.surucu.proje.activity.sinavSonuclarim.model

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.activity.dersListesi.epoxy.model.DersListesiItemModel
import com.bakiyem.surucu.proje.model.kurs.Response4Kurs
import com.bakiyem.surucu.proje.model.sinifSinavi.Response4SinifSinavi
import com.bakiyem.surucu.proje.utils.ext.semibold
import com.orhanobut.hawk.Hawk

@EpoxyModelClass(layout = R.layout.holder_sinif_list_item)
abstract class SinavSonuclarimGirilenSinavListItemModel : EpoxyModelWithHolder<SinavSonuclarimGirilenSinavListItemModel.Holder>() {

    @EpoxyAttribute
    lateinit var context: Context

    @EpoxyAttribute
    lateinit var sinifSinavi: Response4SinifSinavi


    override fun bind(holder: Holder) {
        super.bind(holder)

        with(sinifSinavi) {
            holder.tvQuestionNumber.text = getQuestionsNumber(this).toString()
            holder.tvCorrectNumber.text = getCorrectNumber(this).toString()
            holder.tvWrongNumber.text = getWrongNumber(this).toString()
            holder.tvEmptyNumber.text = getEmptyNumber(this).toString()

            holder.tvScoreNumber.text = puan
            holder.tvBaslik.text = baslik

            if(sonuc == "Başarılı"){
                holder.btnAgainResolve.background = ContextCompat.getDrawable(context, R.drawable.bg_start_btn_green)
            } else{
                holder.btnAgainResolve.background = ContextCompat.getDrawable(context, R.drawable.bg_start_btn_red)
            }

            holder.tvAgainResolve.text = sonuc

            /*val unwrappedDrawable = AppCompatResources.getDrawable(holder.btnAgainResolve.context, R.drawable.bg_start_btn)
            val wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable!!)
            DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#${Hawk.get<Response4Kurs>("kursBilgisi").renk}"))
            holder.btnAgainResolve.setBackgroundResource(R.drawable.bg_start_btn)*/

        }

        holder.tvScoreNumber.semibold()
        holder.tvScoreNumberInfo.semibold()
        holder.tvBaslik.semibold()
        holder.tvAgainResolve.semibold()

        holder.tvQuestionNumberInfo.semibold()
        holder.tv_correctNumberInfo.semibold()
        holder.tv_wrongNumberInfo.semibold()
        holder.tv_emptyNumberInfo.semibold()
        holder.tvQuestionNumber.semibold()
        holder.tvCorrectNumber.semibold()
        holder.tvWrongNumber.semibold()
        holder.tvEmptyNumber.semibold()


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

        lateinit var tvQuestionNumberInfo: TextView
        lateinit var tv_correctNumberInfo: TextView
        lateinit var tv_wrongNumberInfo: TextView
        lateinit var tv_emptyNumberInfo: TextView
        lateinit var tvScoreNumberInfo: TextView




        override fun bindView(itemView: View) {
            tvQuestionNumber = itemView.findViewById(R.id.tv_questionNumber)
            tvQuestionNumberInfo = itemView.findViewById(R.id.tv_questionNumberInfo)
            tvCorrectNumber = itemView.findViewById(R.id.tv_correctNumber)
            tv_correctNumberInfo = itemView.findViewById(R.id.tv_correctNumberInfo)
            tvWrongNumber = itemView.findViewById(R.id.tv_wrongNumber)
            tv_wrongNumberInfo = itemView.findViewById(R.id.tv_wrongNumberInfo)
            tvEmptyNumber = itemView.findViewById(R.id.tv_emptyNumber)
            tv_emptyNumberInfo = itemView.findViewById(R.id.tv_emptyNumberInfo)
            tvScoreNumber = itemView.findViewById(R.id.tv_scoreNumber)
            tvScoreNumberInfo = itemView.findViewById(R.id.tv_scoreNumberInfo)
            cvSinifSinav = itemView.findViewById(R.id.cv_sinifSinav)
            btnAgainResolve = itemView.findViewById(R.id.btn_againResolve)
            tvAgainResolve = itemView.findViewById(R.id.tv_againResolve)
            tvBaslik = itemView.findViewById(R.id.tv_baslik)


        }
    }

}