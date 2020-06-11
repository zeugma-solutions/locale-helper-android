package com.zeugmasolutions.localeexample

import android.content.Context
import androidx.multidex.MultiDex
import com.zeugmasolutions.localehelper.LocaleAwareApplication
import com.zeugmasolutions.localehelper.LocaleHelper

class Application : LocaleAwareApplication() {
    override fun attachBaseContext(base: Context) {
        LocaleHelper.configurationBlock = {
            it.fontScale = 1.0f
        }

        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}