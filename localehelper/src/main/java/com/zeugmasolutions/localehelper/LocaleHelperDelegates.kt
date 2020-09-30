package com.zeugmasolutions.localehelper

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.LocaleHelperAppCompatDelegate
import java.util.*

interface LocaleHelperActivityDelegate {
    fun setLocale(activity: Activity, newLocale: Locale?)
    fun attachBaseContext(newBase: Context): Context
    fun onPaused(activity: Activity)
    fun onResumed(activity: Activity)
    fun onCreate(activity: Activity)
    fun getApplicationContext(applicationContext: Context): Context
    fun getAppCompatDelegate(delegate: AppCompatDelegate): AppCompatDelegate
}

class LocaleHelperActivityDelegateImpl : LocaleHelperActivityDelegate {
    private var locale: Locale? = null
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
        locale = LocaleHelper.getLocale(activity)
    }

    override fun setLocale(activity: Activity, newLocale: Locale?) {
        Log.d(LOG_TAG, "Setting new locale `${newLocale}` and recreating activity `${activity.javaClass.name}`")
        LocaleHelper.setLocale(activity, newLocale)
        locale = LocaleHelper.getLocale(activity)
        activity.recreate()
    }

    override fun attachBaseContext(newBase: Context): Context {
        return LocaleHelper.onAttach(newBase)
    }

    override fun getApplicationContext(applicationContext: Context): Context {
        return LocaleHelper.onAttach(applicationContext)
    }

    override fun onPaused(activity: Activity) {
        val localeFromHelper = LocaleHelper.getLocale(activity)
        Log.d(LOG_TAG, "Remembering locale `$localeFromHelper` in `${activity.javaClass.name}`")
        locale = localeFromHelper
    }

    override fun onResumed(activity: Activity) {
        val localeFromHelper = LocaleHelper.getLocale(activity)
        Log.d(LOG_TAG, "onResume (Comparing `$locale` vs `$localeFromHelper`)")
        if (locale == localeFromHelper) return
        Log.d(LOG_TAG, "Calling `${activity.javaClass.name}`.recreate()")
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
