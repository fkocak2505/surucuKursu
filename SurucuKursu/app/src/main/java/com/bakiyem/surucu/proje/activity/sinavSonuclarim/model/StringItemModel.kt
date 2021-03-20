package com.bakiyem.surucu.proje.activity.sinavSonuclarim.model

import android.view.View
import android.widget.TextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.utils.ext.semibold

@EpoxyModelClass(layout = R.layout.holder_string_item)
abstract class StringItemModel : EpoxyModelWithHolder<StringItemModel.Holder>() {

    @EpoxyAttribute
    lateinit var title: String

    override fun bind(holder: Holder) {
        super.bind(holder)

        with(title) {
            holder.tvStringItem.text = this
        }

        holder.tvStringItem.semibold()
    }

    class Holder : EpoxyHolder() {

        lateinit var tvStringItem: TextView

        override fun bindView(itemView: View) {
            tvStringItem = itemView.findViewById(R.id.tv_stringItem)


        }
    }


}