package com.zeugmasolutions.localehelper

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.LocaleHelperAppCompatDelegate
import java.util.Locale

interface LocaleHelperActivityDelegate {
    fun setLocale(activity: Activity, newLocale: Locale)
    fun onPaused()
    fun onResumed(activity: Activity)
    fun onCreate(activity: Activity)
    fun getApplicationContext(applicationContext: Context): Context
    fun getAppCompatDelegate(delegate: AppCompatDelegate): AppCompatDelegate
}

class LocaleHelperActivityDelegateImpl : LocaleHelperActivityDelegate {
    private var locale: Locale = Locale.getDefault()
    private var appCompatDelegate: AppCompatDelegate? = null

    override fun getAppCompatDelegate(delegate: AppCompatDelegate) =
        appCompatDelegate ?: LocaleHelperAppCompatDelegate(delegate).apply {
            appCompatDelegate = this
        }

    override fun onCreate(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            activity.window.decorView.layoutDirection =
                if (LocaleHelper.isRTL(Locale.getDefault())) View.LAYOUT_DIRECTION_RTL
                else View.LAYOUT_DIRECTION_LTR
        }
    }

    override fun setLocale(activity: Activity, newLocale: Locale) {
        LocaleHelper.setLocale(activity, newLocale)
        locale = newLocale
        activity.recreate()
    }

    override fun getApplicationContext(applicationContext: Context): Context = applicationContext

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

    fun getApplicationContext(context: Context): Context = LocaleHelper.onAttach(context)
}
