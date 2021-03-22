package com.bakiyem.surucu.proje.fragments.main.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.activity.dersKategorileri.DerslerimActivity
import com.bakiyem.surucu.proje.activity.duyuruDetay.DuyuruDetayActivity
import com.bakiyem.surucu.proje.activity.sinavlarim.SinavlarimActivity
import com.bakiyem.surucu.proje.fragments.contact.IletisimVM
import com.bakiyem.surucu.proje.fragments.main.controller.CListener
import com.bakiyem.surucu.proje.fragments.main.controller.MainFragmentController
import com.bakiyem.surucu.proje.fragments.main.viewModel.MainFragmentVM
import com.bakiyem.surucu.proje.model.announcements.Response4Announcements
import com.orhanobut.hawk.Hawk
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.imaginativeworld.whynotimagecarousel.CarouselItem

class MainFragment : Fragment(), CListener<Any> {

    lateinit var mainFragmentVM: MainFragmentVM



    lateinit var viewP: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewP = inflater.inflate(R.layout.fragment_main, container, false)

        initVM()

        return viewP
    }

    private fun initVM() {
        mainFragmentVM = ViewModelProviders.of(this).get(MainFragmentVM::class.java)

        prepareVMListener()

        mainFragmentVM.getAnnouncements()
    }

    private fun prepareVMListener() {

        mainFragmentVM.announcementsLD.observe(this, {
            it?.let {
                initEpoxyController(it)
            } ?: run {
                Toast.makeText(requireContext(), "Error Announcement", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun initEpoxyController(listOfAnnouncement: List<Response4Announcements>) {

        val sliderList = prepareSliderData()

        val controller = MainFragmentController(this)
        controller.sliderItem = sliderList
        controller.duyuruItem = listOfAnnouncement
        viewP.erv_main.setController(controller)
        //controller.requestModelBuild()
    }

    private fun prepareSliderData(): MutableList<CarouselItem> {
        val listOfSlider = mutableListOf<CarouselItem>()
        for (i in 1..5) {
            if (i % 2 == 0) {
                listOfSlider.add(
                    CarouselItem(
                        imageUrl = "https://images.unsplash.com/photo-1569398034126-476b0d96e2d1?w=1080"
                    )
                )
            } else {
                listOfSlider.add(
                    CarouselItem(
                        imageUrl = "https://images.unsplash.com/photo-1507667522877-ad03f0c7b0e0?w=1080"
                    )
                )
            }
        }
        return listOfSlider
    }

    override fun onSelected(data: Any) {
        when (data) {
            is Response4Announcements -> {
                DuyuruDetayActivity.start(requireContext(), data.keyi)
            }
            is String -> {
                if (!Hawk.contains("loginResponse"))
                    Toast.makeText(requireContext(), "Login olun", Toast.LENGTH_SHORT).show()
                else {
                    when (data) {
                        "Derslerim" -> {
                            startActivity(Intent(requireContext(), DerslerimActivity::class.java))
                        }
                        "Sınavlar" -> {
                            startActivity(Intent(requireContext(), SinavlarimActivity::class.java))
                        }
                        "Randevu" -> {
                            Toast.makeText(requireContext(), data, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}