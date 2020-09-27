package com.zeugmasolutions.localeexample

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
import androidx.multidex.MultiDex
import com.zeugmasolutions.localehelper.LocaleAwareApplication
import com.zeugmasolutions.localehelper.LocaleHelper

class Application : LocaleAwareApplication() {

    companion object {
        var FONT_SCALE = 1.0f
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
    }

    override fun attachBaseContext(base: Context) {
        LocaleHelper.configurationBlock = {
            it.fontScale = FONT_SCALE
        }

        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}