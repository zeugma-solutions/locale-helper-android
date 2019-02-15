package com.zeugmasolutions.localehelper

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import java.util.*

object LocaleHelper {

    private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
    private const val SELECTED_COUNTRY = "Locale.Helper.Selected.Country"

    fun onAttach(context: Context): Context {
        val locale = load(context)
        return setLocale(context, locale)
    }

//    fun onConfigurationChanged(context: Context): Configuration {
//        val locale = load(context)
//
//        configuration.locale = locale
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//            configuration.setLayoutDirection(locale)
//        }
//
//        return configuration
//    }

    fun getLocale(context: Context): Locale {
        return load(context)
    }

    fun setLocale(context: Context, locale: Locale): Context {
        persist(context, locale)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, locale)
        } else updateResourcesLegacy(context, locale)

    }

    fun setLocaleAndRestart(activity: Activity, locale: Locale) {
        setLocale(activity.baseContext, locale)
        activity.recreate()
    }

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(LocaleHelper::class.java.name, Context.MODE_PRIVATE)
    }

    private fun persist(context: Context, locale: Locale?) {
        if (locale == null) return
        getPreferences(context)
            .edit()
            .putString(SELECTED_LANGUAGE, locale.language)
            .putString(SELECTED_COUNTRY, locale.country)
            .apply()
    }

    private fun load(context: Context): Locale {
        val preferences = getPreferences(context)
        val language = preferences.getString(SELECTED_LANGUAGE, Locale.getDefault().language)
        val country = preferences.getString(SELECTED_COUNTRY, Locale.getDefault().country)
        return Locale(language, country)
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, locale: Locale): Context {
        Locale.setDefault(locale)

        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        configuration.setLayoutDirection(locale)

        return context.createConfigurationContext(configuration)
    }

    private fun updateResourcesLegacy(context: Context, locale: Locale): Context {
        Locale.setDefault(locale)

        val resources = context.resources

        val configuration = resources.configuration
        configuration.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale)
        }

        resources.updateConfiguration(configuration, resources.displayMetrics)

        return context
    }
}