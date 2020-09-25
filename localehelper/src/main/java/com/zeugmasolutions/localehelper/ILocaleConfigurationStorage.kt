package com.zeugmasolutions.localehelper

import android.content.Context
import android.content.SharedPreferences
import java.util.*

/**
 * Allows to store locale configuration in app specific preferences system.
 */
interface ILocaleConfigurationStorage {
    fun loadLocale(context: Context): Locale?
    fun storeLocale(context: Context, locale: Locale?)
}

/**
 * Default implementation using {@link SharedPreferences}.
 */
class LocaleConfigurationStorageImpl : ILocaleConfigurationStorage {

    companion object {
        private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
        private const val SELECTED_COUNTRY = "Locale.Helper.Selected.Country"
    }

    private fun getPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(LocaleHelper::class.java.name, Context.MODE_PRIVATE)

    override fun loadLocale(context: Context): Locale? {
        val preferences = getPreferences(context)
        return if (preferences.contains(SELECTED_COUNTRY) && preferences.contains(SELECTED_LANGUAGE)) {
            val language = preferences.getString(SELECTED_LANGUAGE, null)
            val country = preferences.getString(SELECTED_COUNTRY, null)
            Locale(requireNotNull(language) {
                "Inconsistent configuration"
            }, requireNotNull(country) {
                "Inconsistent configuration"
            })
        } else {
            null
        }
    }

    override fun storeLocale(context: Context, locale: Locale?) {
        val edit = getPreferences(context).edit()
        if (locale == null) {
            edit
                .remove(SELECTED_LANGUAGE)
                .remove(SELECTED_COUNTRY)
        } else {
            edit
                .putString(SELECTED_LANGUAGE, locale.language)
                .putString(SELECTED_COUNTRY, locale.country)

        }
        edit.apply()
    }
}