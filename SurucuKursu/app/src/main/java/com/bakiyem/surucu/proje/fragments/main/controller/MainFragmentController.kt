package com.bakiyem.surucu.proje.fragments.main.controller

import com.airbnb.epoxy.AsyncEpoxyController
import com.airbnb.epoxy.Carousel
import com.airbnb.epoxy.carousel
import com.bakiyem.surucu.proje.fragments.main.dataModel.StaticData
import com.bakiyem.surucu.proje.fragments.main.epoxyModel.carouselItem.CarouselItemModel_
import com.bakiyem.surucu.proje.fragments.main.epoxyModel.gridItem
import com.bakiyem.surucu.proje.fragments.main.epoxyModel.headerTitle.headerItem
import com.bakiyem.surucu.proje.fragments.main.epoxyModel.single.singleItem
import com.bakiyem.surucu.proje.fragments.main.epoxyModel.slider.sliderItem
import com.bakiyem.surucu.proje.model.announcements.Response4Announcements
import org.imaginativeworld.whynotimagecarousel.CarouselItem

class MainFragmentController(private val listener: CListener<Any>) : AsyncEpoxyController() {

    var sliderItem: List<CarouselItem> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    var duyuruItem: List<Response4Announcements> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        sliderItem {
            id("1")
            sliderItem(sliderItem)
        }

        gridItem {
            id("2")
            listener {
                listener.onSelected(it)
            }
        }

        singleItem {
            id("3")
        }

        headerItem {
            id("4")
            title("Duyurular")
        }

        val announcementsModel = duyuruItem.map {
            CarouselItemModel_()
                .id(it.keyi)
                .announcements(it)
                .listener {
                    listener.onSelected(it)
                }
        }
        carousel {
            id("recent1")
            padding(Carousel.Padding.dp(0, 4, 0, 16, 8))
            models(announcementsModel)
        }
    }
}