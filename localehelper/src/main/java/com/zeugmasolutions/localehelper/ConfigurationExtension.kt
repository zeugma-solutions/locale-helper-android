package com.zeugmasolutions.localehelper

import android.content.res.Configuration
import android.os.Build
import java.util.*

@Suppress("DEPRECATION")
val Configuration.currentLocale: Locale
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        locales.get(0)
    } else {
        locale
    }

@Suppress("DEPRECATION")
fun Configuration.setCurrentLocale(locale: Locale) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        this.setLocale(locale)
    } else {
        this.locale = locale
    }
}