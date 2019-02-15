package com.zeugmasolutions.localehelper

import android.app.Activity
import android.app.Application
import android.content.Context

class LocaleAwareAppCompatActivity : Activity() {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }
}

class LocaleAwareApp : Application() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(base))
    }
}