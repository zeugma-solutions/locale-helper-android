package com.zeugmasolutions.localehelper

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.Log
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
        Log.d("LocaleHelperDelegates", "Setting new locale $newLocale and recreating activity")
        LocaleHelper.setLocale(activity, newLocale)
        locale = LocaleHelper.getLocale(activity)
        activity.recreate()
    }

    override fun attachBaseContext(newBase: Context): Context = LocaleHelper.onAttach(newBase)

    override fun getApplicationContext(applicationContext: Context): Context =
        LocaleHelper.onAttach(applicationContext)

    override fun onPaused(activity: Activity) {
        locale = LocaleHelper.getLocale(activity)
    }

    override fun onResumed(activity: Activity) {
        Log.d("LocaleHelperDelegates", "On resume ($locale vs ${LocaleHelper.getLocale(activity)})...")
        if (locale == LocaleHelper.getLocale(activity)) return
        activity.recreate()
    }
}

class LocaleHelperApplicationDelegate {
    fun attachBaseContext(base: Context): Context = LocaleHelper.onAttach(base)

    fun onConfigurationChanged(context: Context) {
        LocaleHelper.onAttach(context)
    }
}