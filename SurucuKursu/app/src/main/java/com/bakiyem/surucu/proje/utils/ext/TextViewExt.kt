package com.bakiyem.surucu.proje.utils.ext

import android.os.Build
import android.text.Html
import android.widget.TextView

infix fun TextView.renderHtml(string: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        this.text = Html.fromHtml(string, Html.FROM_HTML_MODE_COMPACT)
    } else {
        this.text = Html.fromHtml(string)
    }
}