package com.zeugmasolutions.localehelper

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import java.util.Locale

object LocaleHelper {
    private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
    private const val SELECTED_COUNTRY = "Locale.Helper.Selected.Country"
    private var initialized = false

    /**
     * Returns the system [Locale]
     */
    @SuppressLint("ConstantLocale")
    val systemLocale: Locale = Locale.getDefault()

    /**
     * Attach the selected or default [Locale] to the [context]
     */
    fun onAttach(context: Context): Context {
        if (!initialized) {
            Locale.setDefault(load(context))
            initialized = true
        }
        return updateContextResources(context, Locale.getDefault())
    }

    /**
     * Gets the currently saved [Locale] from [SharedPreferences] or returns [Locale.getDefault]
     */
    fun getLocale(context: Context): Locale = load(context)

    /**
     * Clears any saved locale selections and returns app to system default
     */
    fun clearLocaleSelection(context: Context): Context {
        getPreferences(context).edit()
            .remove(SELECTED_LANGUAGE)
            .remove(SELECTED_COUNTRY)
            .apply()
        Locale.setDefault(systemLocale)
        return updateContextResources(context, systemLocale)
    }

    /**
     * Returns if a user-specified locale has been set
     */
    fun hasLocaleSelection(context: Context): Boolean {
        val prefs = getPreferences(context)
        return prefs.contains(SELECTED_COUNTRY) && prefs.contains(SELECTED_COUNTRY)
    }

    /**
     * Sets [locale] for [context] and persist the selection in [SharedPreferences]
     */
    fun setLocale(context: Context, locale: Locale): Context {
        persist(context, locale)
        Locale.setDefault(locale)
        return updateContextResources(context, locale)
    }

    /**
     * Returns if the given [locale] is a Right-To-Left language
     */
    fun isRTL(locale: Locale): Boolean = Locales.RTL.contains(locale.language)

    private fun getPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(LocaleHelper::class.java.name, Context.MODE_PRIVATE)

    private fun persist(context: Context, locale: Locale?) {
        if (locale == null) return
        getPreferences(context).edit()
            .putString(SELECTED_LANGUAGE, locale.language)
            .putString(SELECTED_COUNTRY, locale.country)
            .apply()
    }

    private fun load(context: Context): Locale {
        val preferences = getPreferences(context)
        val default = Locale.getDefault()
        val language = preferences.getString(SELECTED_LANGUAGE, default.language) ?: return default
        val country = preferences.getString(SELECTED_COUNTRY, default.country) ?: return default
        return Locale(language, country)
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
