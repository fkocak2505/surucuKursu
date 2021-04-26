package com.bakiyem.surucu.proje.activity.odemeBilgilerim.epoxy.model.model

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.model.odemeBilgilerim.Response4OdemeBilgileri
import com.bakiyem.surucu.proje.utils.ext.regular
import com.bakiyem.surucu.proje.utils.ext.semibold

@EpoxyModelClass(layout = R.layout.holder_sub_grid_odeme_bilgileri)
abstract class GridSubItemModel4OdemeBilgileri :
    EpoxyModelWithHolder<GridSubItemModel4OdemeBilgileri.GridHolder>() {

    @EpoxyAttribute
    lateinit var context: Context

    @EpoxyAttribute
    lateinit var response4OdemeBilgileri: Response4OdemeBilgileri

    @EpoxyAttribute
    lateinit var listener: (Response4OdemeBilgileri) -> Unit

    override fun bind(holder: GridHolder) {
        super.bind(holder)
        with(response4OdemeBilgileri) {
            holder.tvVadeTarihi.text = borcTarih
            holder.tvTutar.text = "$tutar TL"

            if (durum == "Ödendi") {
                holder.btnOdemeYap.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_start_btn_gray)
                holder.tvOdemeYap.text = "Ödeme Yapıldı"

                holder.llTahsilTarihi.visibility = View.VISIBLE
                holder.llTuru.visibility = View.VISIBLE
                holder.llMakbuzNo.visibility = View.VISIBLE

                if (odemeTarih != "")
                    holder.tvTahsilTarihi.text = odemeTarih
                else
                    holder.tvTahsilTarihi.text = "-"

                if (odemeTur != "")
                    holder.tvTuru.text = odemeTur
                else
                    holder.tvTuru.text = "-"

                if (makbuzNo != "")
                    holder.tvMakbuz.text = makbuzNo
                else
                    holder.tvMakbuz.text = "-"



            } else {
                holder.btnOdemeYap.background =
                    ContextCompat.getDrawable(context, R.drawable.bg_start_btn_green)
                holder.tvOdemeYap.text = "Ödeme Yap"

                holder.llTahsilTarihi.visibility = View.GONE
                holder.llTuru.visibility = View.GONE
                holder.llMakbuzNo.visibility = View.GONE
            }

        }

        holder.tvVadeTarihiInfo.semibold()
        holder.tvVadeTarihi.regular()
        holder.tvTahsilTarihiInfo.semibold()
        holder.tvTahsilTarihi.regular()
        holder.tvTuruInfo.semibold()
        holder.tvTuru.regular()
        holder.tvMakbuzInfo.semibold()
        holder.tvMakbuz.regular()
        holder.tvTutarInfo.semibold()
        holder.tvTutar.regular()
        holder.tvOdemeYap.semibold()


        holder.btnOdemeYap.setOnClickListener {
            listener.invoke(response4OdemeBilgileri)
        }


    }

    class GridHolder : EpoxyHolder() {

        lateinit var tvVadeTarihiInfo: TextView
        lateinit var tvVadeTarihi: TextView
        lateinit var tvTahsilTarihiInfo: TextView
        lateinit var tvTahsilTarihi: TextView
        lateinit var tvTuruInfo: TextView
        lateinit var tvTuru: TextView
        lateinit var tvMakbuzInfo: TextView
        lateinit var tvMakbuz: TextView
        lateinit var tvTutarInfo: TextView
        lateinit var tvTutar: TextView
        lateinit var btnOdemeYap: ConstraintLayout
        lateinit var tvOdemeYap: TextView

        lateinit var llVadeTarihi: LinearLayout
        lateinit var llTahsilTarihi: LinearLayout
        lateinit var llTuru: LinearLayout
        lateinit var llMakbuzNo: LinearLayout
        lateinit var llTutar: LinearLayout


        override fun bindView(itemView: View) {
            tvVadeTarihiInfo = itemView.findViewById(R.id.tv_vadeTarihiInfo)
            tvVadeTarihi = itemView.findViewById(R.id.tv_vadeTarihi)
            tvTahsilTarihiInfo = itemView.findViewById(R.id.tv_tahsilTarihiInfo)
            tvTahsilTarihi = itemView.findViewById(R.id.tv_tahsilTarihi)
            tvTuruInfo = itemView.findViewById(R.id.tv_turuInfo)
            tvTuru = itemView.findViewById(R.id.tv_turu)
            tvMakbuzInfo = itemView.findViewById(R.id.tv_makbuzInfo)
            tvMakbuz = itemView.findViewById(R.id.tv_makbuz)
            tvTutarInfo = itemView.findViewById(R.id.tv_tutarInfo)
            tvTutar = itemView.findViewById(R.id.tv_tutar)
            btnOdemeYap = itemView.findViewById(R.id.btn_odemeYap)
            tvOdemeYap = itemView.findViewById(R.id.tv_odemeYap)

            llVadeTarihi = itemView.findViewById(R.id.ll_vadeTarihi)
            llTahsilTarihi = itemView.findViewById(R.id.ll_tahsilTarihi)
            llTuru = itemView.findViewById(R.id.ll_turu)
            llMakbuzNo = itemView.findViewById(R.id.ll_makbuzNo)
            llTutar = itemView.findViewById(R.id.ll_tutar)

        }
    }

}