package com.bakiyem.surucu.project.fragments.main.epoxyModel.slider

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.project.R
import org.imaginativeworld.whynotimagecarousel.CarouselItem
import org.imaginativeworld.whynotimagecarousel.ImageCarousel

@EpoxyModelClass(layout = R.layout.holder_slider_item)
abstract class SliderItemModel : EpoxyModelWithHolder<SliderItemModel.SliderHolder>(){

    @EpoxyAttribute
    lateinit var sliderItem: List<CarouselItem>

    override fun bind(holder: SliderHolder) {
        super.bind(holder)

        holder.icSlider.addData(sliderItem)

    }

    class SliderHolder : EpoxyHolder() {

        lateinit var icSlider: ImageCarousel

        override fun bindView(itemView: View) {
            icSlider = itemView.findViewById(R.id.ic_slider)
        }
    }

}