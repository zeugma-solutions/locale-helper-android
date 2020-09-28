package com.zeugmasolutions.localehelper

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import java.util.*

val Context.currentLocale: Locale
    get() = resources.configuration.currentLocale

val Configuration.currentLocale: Locale
    get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        locales.get(0)
    } else {
        @Suppress("DEPRECATION")
        locale
    }

fun Configuration.setCurrentLocale(locale: Locale) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
        this.setLocale(locale)
    } else {
        @Suppress("DEPRECATION")
        this.locale = locale
    }
}