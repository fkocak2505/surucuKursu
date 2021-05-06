package com.bakiyem.surucu.project.utils.ext

import android.graphics.Typeface
import android.os.Build
import android.text.Html
import android.widget.TextView
import com.bakiyem.surucu.project.utils.FontUtils

infix fun TextView.renderHtml(string: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        this.text = Html.fromHtml(string, Html.FROM_HTML_MODE_COMPACT)
    } else {
        this.text = Html.fromHtml(string)
    }
}

fun TextView.semibold() {
    try {
        this.typeface = FontUtils.getFont(Typeface.BOLD)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun TextView.regular() {
    try {
        this.typeface = FontUtils.getFont(Typeface.BOLD)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}