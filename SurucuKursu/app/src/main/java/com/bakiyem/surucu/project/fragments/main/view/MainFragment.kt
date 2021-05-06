package com.bakiyem.surucu.project.fragments.main.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.activity.dersKategorileri.DerslerimActivity
import com.bakiyem.surucu.project.activity.duyuruDetay.DuyuruDetayActivity
import com.bakiyem.surucu.project.activity.login.LoginActivity
import com.bakiyem.surucu.project.activity.randevular.RandevularimActivity
import com.bakiyem.surucu.project.activity.sinavlarim.SinavlarimActivity
import com.bakiyem.surucu.project.fragments.main.controller.CListener
import com.bakiyem.surucu.project.fragments.main.controller.MainFragmentController
import com.bakiyem.surucu.project.fragments.main.viewModel.MainFragmentVM
import com.bakiyem.surucu.project.model.announcements.Response4Announcements
import com.bakiyem.surucu.project.model.announcements.Response4Slider
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
        var announcements = mutableListOf<Response4Announcements>()
        mainFragmentVM.announcementsLD.observe(this, {
            it?.let {
                announcements = it
                mainFragmentVM.getSlider()
            } ?: run {
                Toast.makeText(requireContext(), "Error Announcement", Toast.LENGTH_SHORT).show()
            }
        })

        mainFragmentVM.sliderLD.observe(this, {
            it?.let {
                initEpoxyController(announcements, it)
            }?: run{
                Toast.makeText(requireContext(), "Error Slider", Toast.LENGTH_SHORT).show()
            }
        })

    }

    private fun initEpoxyController(listOfAnnouncement: List<Response4Announcements>, listOfSlider: MutableList<Response4Slider>) {

        val sliderList = prepareSliderData(listOfSlider)

        val controller = MainFragmentController(this)
        controller.sliderItem = sliderList
        controller.duyuruItem = listOfAnnouncement
        viewP.erv_main.setController(controller)
        //controller.requestModelBuild()
    }

    private fun prepareSliderData(listOfSlider: MutableList<Response4Slider>): MutableList<CarouselItem> {
        val cListOfSlider = mutableListOf<CarouselItem>()

        listOfSlider.forEach {
            cListOfSlider.add(
                CarouselItem(
                    imageUrl = it.resim
                )
            )
        }

        return cListOfSlider
    }

    override fun onSelected(
        data: Any,
        videoView: VideoView?,
        placeHolder: ImageView?,
        playIcon: ImageView?
    ) {
        when (data) {
            is Response4Announcements -> {
                DuyuruDetayActivity.start(requireContext(), data.keyi)
            }
            is String -> {
                if (!Hawk.contains("loginResponse"))
                    startActivity(Intent(requireContext(), LoginActivity::class.java))
                else {
                    when (data) {
                        "Derslerim" -> {
                            startActivity(Intent(requireContext(), DerslerimActivity::class.java))
                        }
                        "Sınavlar" -> {
                            startActivity(Intent(requireContext(), SinavlarimActivity::class.java))
                        }
                        "Randevu" -> {
                            //startActivity(Intent(requireContext(), RandevuEkleActivity::class.java))
                            startActivity(Intent(requireContext(), RandevularimActivity::class.java))
                        }
                    }
                }
            }
        }
    }
}