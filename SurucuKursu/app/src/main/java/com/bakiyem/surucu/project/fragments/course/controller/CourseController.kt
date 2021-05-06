package com.bakiyem.surucu.project.fragments.course.controller

import com.airbnb.epoxy.AsyncEpoxyController
import com.bakiyem.surucu.project.fragments.course.dataModel.CourseItem
import com.bakiyem.surucu.project.fragments.course.epoxyModel.courseItem
import com.bakiyem.surucu.project.fragments.main.controller.CListener

class CourseController(private val listener: CListener<Any>) : AsyncEpoxyController() {

    var courseItem: List<CourseItem> = emptyList()
        set(value) {
            field = value
            requestModelBuild()
        }


    override fun buildModels() {

        courseItem.forEachIndexed { index, courseItem ->
            courseItem {
                id("course $index")
                courseItem(courseItem)
                cCourseListener {
                    listener.onSelected(it)
                }
            }
        }
    }
}