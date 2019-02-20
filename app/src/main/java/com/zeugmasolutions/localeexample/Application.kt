package com.zeugmasolutions.localeexample

import com.zeugmasolutions.localehelper.LocaleAwareApplication
import java.util.*

class Application : LocaleAwareApplication() {
    override fun onCreate() {
        super.onCreate()


        val locales = Locale.getAvailableLocales()
        val a = locales
    }
}