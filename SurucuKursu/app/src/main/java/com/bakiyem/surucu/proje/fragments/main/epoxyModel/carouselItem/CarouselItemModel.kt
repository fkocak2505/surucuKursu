package com.bakiyem.surucu.proje.fragments.main.epoxyModel.carouselItem

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.model.announcements.Response4Announcements

@EpoxyModelClass(layout = R.layout.holder_carousel_item)
abstract class CarouselItemModel: EpoxyModelWithHolder<CarouselItemModel.Holder>()  {

    @EpoxyAttribute
    lateinit var announcements: Response4Announcements

    @EpoxyAttribute
    lateinit var listener: (Response4Announcements) -> Unit

    override fun bind(holder: Holder) {
        super.bind(holder)
        with(announcements) {
            holder.tvDate.text = tarih
            holder.tvAnnouncementsTitle.text = baslik

            holder.cvDuyuru.setOnClickListener {
                listener.invoke(this)
            }
        }
    }

    class Holder : EpoxyHolder() {

        lateinit var tvDate: TextView
        lateinit var tvAnnouncementsTitle: TextView
        lateinit var cvDuyuru: CardView

        override fun bindView(itemView: View) {
            tvDate = itemView.findViewById(R.id.tv_date)
            tvAnnouncementsTitle = itemView.findViewById(R.id.tv_announcementsTitle)
            cvDuyuru = itemView.findViewById(R.id.cv_duyuru)



        }
    }

}