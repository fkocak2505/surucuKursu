package com.bakiyem.surucu.proje.fragments.main.data

import com.bakiyem.surucu.proje.R
import com.bakiyem.surucu.proje.fragments.course.dataModel.CourseItem
import com.bakiyem.surucu.proje.fragments.main.dataModel.StaticData

object DummyData {

    val gridData = listOf(
        StaticData(
            1,
            R.drawable.ic_lessons,
            "Derslerim",
            "Dersleri hem dinleyelim, hem de okuyabilirsiniz."
        ),
        StaticData(
            2,
            R.drawable.ic_exam,
            "Sınavlar",
            "Deneme sınavlarıyla seviyenizi ölçebilirsiniz."
        )
    )

    val singleData = StaticData(
        3,
        R.drawable.ic_date,
        "Randevu",
        "Takvimden uygun tarihi seçerek randevu oluşturabilirsiniz."
    )

    val course = listOf(
        CourseItem(
            "1",
            R.drawable.ic_hakkimizda,
            "Hakkımızda"
        ),
        CourseItem(
            "2",
            R.drawable.ic_araclar,
            "Araçlarımız"
        ),
        CourseItem(
            "3",
            R.drawable.ic_galeri,
            "Galeri"
        ),
        CourseItem(
            "4",
            R.drawable.ic_kadromuz,
            "Kadromuz"
        )
    )
}