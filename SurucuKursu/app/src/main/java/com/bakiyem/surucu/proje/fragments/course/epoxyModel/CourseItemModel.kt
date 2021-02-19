package com.bakiyem.surucu.proje.fragments.course.epoxyModel

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.fragments.course.dataModel.CourseItem

@EpoxyModelClass(layout = R.layout.holder_course_item)
abstract class CourseItemModel: EpoxyModelWithHolder<CourseItemModel.Holder>()  {

    @EpoxyAttribute
    lateinit var courseItem: CourseItem

    @EpoxyAttribute
    lateinit var cCourseListener: (CourseItem) -> Unit

    override fun bind(holder: CourseItemModel.Holder) {
        super.bind(holder)

        with(courseItem){
            holder.ivImage.setImageResource(image)
            holder.tvTitle.text = title

            holder.cvCourseItem.setOnClickListener {
                cCourseListener.invoke(courseItem)
            }
        }

    }

    class Holder : EpoxyHolder() {

        lateinit var ivImage: ImageView
        lateinit var tvTitle: TextView
        lateinit var cvCourseItem: CardView

        override fun bindView(itemView: View) {
            ivImage = itemView.findViewById(R.id.iv_image)
            tvTitle = itemView.findViewById(R.id.tv_title)
            cvCourseItem = itemView.findViewById(R.id.cv_courseItem)

        }
    }

}