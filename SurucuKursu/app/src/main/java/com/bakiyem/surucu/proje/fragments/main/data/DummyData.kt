package com.bakiyem.surucu.proje.fragments.main.data

import com.bakiyem.surucu.proje.R
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
}