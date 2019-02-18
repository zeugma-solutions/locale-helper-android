package com.zeugmasolutions.localeexample

import com.zeugmasolutions.localehelper.LocaleAwareApp
import java.util.*

class App : LocaleAwareApp() {
    override fun onCreate() {
        super.onCreate()


        val locales = Locale.getAvailableLocales()
        val a = locales
    }
}