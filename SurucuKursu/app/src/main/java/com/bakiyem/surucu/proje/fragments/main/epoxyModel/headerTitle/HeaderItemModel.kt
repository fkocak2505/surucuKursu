package com.bakiyem.surucu.proje.fragments.main.epoxyModel.headerTitle

import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.proje.R

@EpoxyModelClass(layout = R.layout.holder_header_item)
abstract class HeaderItemModel: EpoxyModelWithHolder<HeaderItemModel.Holder>()  {

    @EpoxyAttribute
    lateinit var title: String

    override fun bind(holder: Holder) {
        super.bind(holder)
        holder.title.text = title
    }

    class Holder : EpoxyHolder() {

        lateinit var title: AppCompatTextView

        override fun bindView(itemView: View) {
            title = itemView.findViewById(R.id.header_title)
        }
    }

}