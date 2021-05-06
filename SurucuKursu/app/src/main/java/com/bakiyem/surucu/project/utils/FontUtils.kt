package com.bakiyem.surucu.project.utils

import android.graphics.Typeface
import android.util.SparseArray
import androidx.annotation.IntRange
import com.bakiyem.surucu.project.R
import com.bakiyem.surucu.project.SurucuKursuApplication

object FontUtils {

    private val fontMap = SparseArray<Typeface>()

    @JvmStatic
    fun getFont(
        @IntRange(
            from = Typeface.NORMAL.toLong(),
            to = Typeface.BOLD.toLong()
        ) style: Int = Typeface.NORMAL
    ): Typeface {
        if (fontMap.size() == 0) {
            val appFontNameWithPath =
                "fonts/" + SurucuKursuApplication.getInstance()?.getString(R.string.app_new_font_regular)
            val appBolFontNameWithPath =
                "fonts/" + SurucuKursuApplication.getInstance()?.getString(R.string.app_new_font_semibold)
            val assetManager = SurucuKursuApplication.getInstance()?.assets
            fontMap.append(
                Typeface.NORMAL,
                Typeface.createFromAsset(assetManager, appFontNameWithPath)
            )
            fontMap.append(
                Typeface.BOLD,
                Typeface.createFromAsset(assetManager, appBolFontNameWithPath)
            )
        }
        return fontMap[style]
    }

}