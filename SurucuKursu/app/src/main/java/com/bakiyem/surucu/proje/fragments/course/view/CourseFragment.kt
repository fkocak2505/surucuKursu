package com.bakiyem.surucu.proje.fragments.course.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.activity.araclarimiz.view.AraclarimizActivity
import com.bakiyem.surucu.proje.activity.galeri.GaleriActivity
import com.bakiyem.surucu.proje.activity.hakkimizda.HakkimizdaActivity
import com.bakiyem.surucu.proje.activity.kadromuz.KadromuzActivity
import com.bakiyem.surucu.proje.fragments.course.controller.CourseController
import com.bakiyem.surucu.proje.fragments.course.dataModel.CourseItem
import com.bakiyem.surucu.proje.fragments.main.controller.CListener
import com.bakiyem.surucu.proje.fragments.main.data.DummyData
import com.bakiyem.surucu.proje.utils.ext.semibold
import kotlinx.android.synthetic.main.fragment_course.view.*

class CourseFragment : Fragment(), CListener<Any> {

    lateinit var viewP: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewP = inflater.inflate(R.layout.fragment_course, container, false)

        initERVCourse()

        viewP.tv_hugeTitle.semibold()

        return viewP
    }

    private fun initERVCourse() {
        val courseController = CourseController(this)
        courseController.courseItem = DummyData.course
        viewP.erv_course.setController(courseController)
    }


    override fun onSelected(
        data: Any,
        videoView: VideoView?,
        placeHolder: ImageView?,
        playIcon: ImageView?
    ) {
        when (data) {
            is CourseItem -> {
                when (data.id) {
                    "1" -> {
                        startActivity(Intent(requireContext(), HakkimizdaActivity::class.java))
                    }
                    "2" -> {
                        startActivity(Intent(requireContext(), AraclarimizActivity::class.java))
                    }
                    "3" -> {
                        startActivity(Intent(requireContext(), GaleriActivity::class.java))
                    }
                    "4" -> {
                        startActivity(Intent(requireContext(), KadromuzActivity::class.java))
                    }
                }
            }
        }
    }
}