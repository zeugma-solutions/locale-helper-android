package com.zeugmasolutions.localehelper

import java.util.*

class App : LocaleAwareApp() {
    override fun onCreate() {
        super.onCreate()


        val locales = Locale.getAvailableLocales()
        val a = locales
    }
}