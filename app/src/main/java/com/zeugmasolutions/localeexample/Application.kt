package com.zeugmasolutions.localeexample

import android.content.Context
import androidx.multidex.MultiDex
import com.zeugmasolutions.localehelper.LocaleAwareApplication

class Application : LocaleAwareApplication() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}