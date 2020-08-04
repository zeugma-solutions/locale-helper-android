package com.zeugmasolutions.localehelper

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.view.View
import java.util.*

interface LocaleHelperActivityDelegate {
    fun setLocale(activity: Activity, newLocale: Locale)
    fun attachBaseContext(newBase: Context): Context
    fun applyOverrideConfiguration(
        baseContext: Context,
        overrideConfiguration: Configuration?
    ): Configuration?

    fun onPaused()
    fun onResumed(activity: Activity)
    fun onCreate(activity: Activity)
    fun getResources(resources: Resources): Resources
}

class LocaleHelperActivityDelegateImpl : LocaleHelperActivityDelegate {
    override fun onCreate(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            activity.window.decorView.layoutDirection =
                if (LocaleHelper.isRTL(Locale.getDefault())) View.LAYOUT_DIRECTION_RTL else View.LAYOUT_DIRECTION_LTR
        }
    }

    private var locale: Locale = Locale.getDefault()

    override fun setLocale(activity: Activity, newLocale: Locale) {
        LocaleHelper.setLocale(activity, newLocale)
        locale = newLocale
        activity.recreate()
    }

    override fun attachBaseContext(newBase: Context): Context = LocaleHelper.onAttach(newBase)

    override fun applyOverrideConfiguration(
        baseContext: Context, overrideConfiguration: Configuration?
    ): Configuration? {
        overrideConfiguration?.setTo(baseContext.resources.configuration)
        overrideConfiguration?.setCurrentLocale(Locale.getDefault())
        return overrideConfiguration
    }

    override fun getResources(resources: Resources): Resources {
        return if (resources.configuration.currentLocale == Locale.getDefault()) {
            resources
        } else {
            resources.configuration.setCurrentLocale(Locale.getDefault())
            resources
        }
    }

    override fun onPaused() {
        locale = Locale.getDefault()
    }

    override fun onResumed(activity: Activity) {
        if (locale == Locale.getDefault()) return

        activity.recreate()
    }
}

class LocaleHelperApplicationDelegate {
    fun attachBaseContext(base: Context): Context = LocaleHelper.onAttach(base)

    fun onConfigurationChanged(context: Context) {
        LocaleHelper.onAttach(context)
    }
}