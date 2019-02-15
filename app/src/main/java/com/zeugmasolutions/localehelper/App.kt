package com.zeugmasolutions.localehelper

import android.app.Application
import android.content.Context
import android.content.res.Configuration
import android.util.Log

class App : Application() {

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