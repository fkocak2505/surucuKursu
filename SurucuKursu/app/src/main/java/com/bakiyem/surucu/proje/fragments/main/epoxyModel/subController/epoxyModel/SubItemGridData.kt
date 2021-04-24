package com.bakiyem.surucu.proje.fragments.main.epoxyModel.subController.epoxyModel

import android.graphics.Color
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.drawable.DrawableCompat
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.fragments.main.dataModel.StaticData
import com.bakiyem.surucu.proje.model.kurs.Response4Kurs
import com.bakiyem.surucu.proje.utils.ext.regular
import com.bakiyem.surucu.proje.utils.ext.semibold
import com.google.android.material.card.MaterialCardView
import com.orhanobut.hawk.Hawk

@EpoxyModelClass(layout = R.layout.holder_sub_grid_item)
abstract class SubItemGridData : EpoxyModelWithHolder<SubItemGridData.GridHolder>() {

    @EpoxyAttribute
    lateinit var subStaticData: StaticData

    @EpoxyAttribute
    lateinit var listener: (String) -> Unit

    override fun bind(holder: GridHolder) {
        super.bind(holder)
        with(subStaticData) {
            holder.title.text = title
            holder.tv_itemDesc.text = desc
            holder.profileImage.setImageResource(image)

            holder.title.semibold()
            holder.tv_itemDesc.regular()
            holder.tvStart.semibold()
        }

        holder.cl.setOnClickListener {
            listener.invoke(subStaticData.title)
        }

        val unwrappedDrawable = AppCompatResources.getDrawable(holder.title.context, R.drawable.bg_start_btn)
        val wrappedDrawable = DrawableCompat.wrap(unwrappedDrawable!!)
        DrawableCompat.setTint(wrappedDrawable, Color.parseColor("#${Hawk.get<Response4Kurs>("kursBilgisi").renk}"))
        holder.btnStart.setBackgroundResource(R.drawable.bg_start_btn)
    }

    class GridHolder : EpoxyHolder() {

        lateinit var profileImage: ImageView
        lateinit var title: TextView
        lateinit var cl: CardView
        lateinit var tv_itemDesc: TextView
        lateinit var tvStart: TextView
        lateinit var btnStart: ConstraintLayout

        override fun bindView(itemView: View) {
            profileImage = itemView.findViewById(R.id.grid_item_icon)
            title = itemView.findViewById(R.id.grid_item_title)
            tv_itemDesc = itemView.findViewById(R.id.tv_itemDesc)
            cl = itemView.findViewById(R.id.cv_gridItem)
            tvStart = itemView.findViewById(R.id.tv_start)
            btnStart = itemView.findViewById(R.id.btn_start)



        }
    }

}