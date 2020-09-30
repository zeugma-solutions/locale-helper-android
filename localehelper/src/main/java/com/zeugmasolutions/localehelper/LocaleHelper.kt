package com.zeugmasolutions.localehelper

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import android.os.Build
import androidx.core.os.ConfigurationCompat
import java.util.*

object LocaleHelper {

    private var initialized = false

    var configurationStorage: ILocaleConfigurationStorage = LocaleConfigurationStorageImpl()
        set(value) {
            field = value
            initialized = false
        }

    /**
     * Attach the selected or default [Locale] to the [context]
     */
    fun onAttach(context: Context): Context {
        if (!initialized) {
            val localesFromConfiguration = load(context)
            Locale.setDefault(localesFromConfiguration ?: getSystemLocale())
            initialized = true
        }
        return updateContextResources(context, Locale.getDefault())
    }

    fun getSystemLocale(): Locale {
        val locales = ConfigurationCompat.getLocales(Resources.getSystem().configuration)
        return if (locales.isEmpty) {
            Locale.US
        } else {
            locales.get(0)
        }
    }

    /**
     * Gets the currently saved [Locale] from [SharedPreferences] or returns [Locale.getDefault]
     */
    fun getLocale(context: Context): Locale? = load(context)

    /**
     * Sets [locale] for [context] and persist the selection in [SharedPreferences]
     */
    fun setLocale(context: Context, locale: Locale?): Context {
        persist(context, locale)
        return if (locale != null) {
            Locale.setDefault(locale)
            updateContextResources(context, locale)
        } else {
            val systemLocale = getSystemLocale()
            Locale.setDefault(systemLocale)
            updateContextResources(context, systemLocale)
        }
    }

    /**
     * Returns if the given [locale] is a Right-To-Left language
     */
    fun isRTL(locale: Locale): Boolean = Locales.RTL.contains(locale.language)

    private fun persist(context: Context, locale: Locale?) {
        configurationStorage.storeLocale(context, locale)
    }

    private fun load(context: Context): Locale? {
        return configurationStorage.loadLocale(context)
    }

    private fun updateContextResources(context: Context, locale: Locale): Context {
        if (context.currentLocale == locale && context is Application) {
            return context
        }

        val resources = context.resources
        val configuration = resources.configuration
        configuration.setCurrentLocale(locale)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLayoutDirection(locale)
        }

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context.createConfigurationContext(configuration)
        } else {
            @Suppress("DEPRECATION")
            resources.updateConfiguration(configuration, resources.displayMetrics)
            context
        }
    }
}