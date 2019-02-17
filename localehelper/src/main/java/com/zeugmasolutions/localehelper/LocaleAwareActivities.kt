package com.zeugmasolutions.localehelper

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.*

open class LocaleAwareActivity : Activity() {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }

    open fun updateLocale(locale: Locale) {
        LocaleHelper.setLocale(this, locale)
        recreate()
    }
}

open class LocaleAwareCompatActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }

    open fun updateLocale(locale: Locale) {
        LocaleHelper.setLocale(this, locale)
        recreate()
    }
}

open class LocaleAwareApp : Application() {
    override fun attachBaseContext(base: Context) {
        Log.i(
            "gunhan",
            "App.attachBaseContext: current:${base.resources.configuration.locale.language} new:${LocaleHelper.onAttach(
                base
            ).resources.configuration.locale.language}"
        )

        super.attachBaseContext(LocaleHelper.onAttach(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        Log.i("gunhan", "App.onConfigurationChanged: ${newConfig.locale.language}")

        // super.onConfigurationChanged(LocaleHelper.onConfig(this, newConfig))
        super.onConfigurationChanged(newConfig)
        LocaleHelper.onAttach(this)

    }
}