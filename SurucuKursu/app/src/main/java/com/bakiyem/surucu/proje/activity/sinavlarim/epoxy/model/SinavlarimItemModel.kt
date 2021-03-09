package com.bakiyem.surucu.proje.activity.sinavlarim.epoxy.model

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.proje.R

@EpoxyModelClass(layout = R.layout.holder_sinavlarim_item)
abstract class SinavlarimItemModel: EpoxyModelWithHolder<SinavlarimItemModel.Holder>()  {

    @EpoxyAttribute
    lateinit var sinavItem: String

    @EpoxyAttribute
    lateinit var listener: (String) -> Unit

    override fun bind(holder: Holder) {
        super.bind(holder)

        with(sinavItem) {
            holder.tvSinavlarim.text = this
        }

        holder.cvSinavItem.setOnClickListener {
            listener.invoke(sinavItem)
        }

    }

    class Holder : EpoxyHolder() {

        lateinit var tvSinavlarim: TextView
        lateinit var cvSinavItem: CardView

        override fun bindView(itemView: View) {
            tvSinavlarim = itemView.findViewById(R.id.tv_sinavlarim)
            cvSinavItem = itemView.findViewById(R.id.cv_sinavItem)

        }
    }

}