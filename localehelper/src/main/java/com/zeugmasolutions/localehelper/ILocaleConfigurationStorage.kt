import android.content.Context
import android.content.SharedPreferences
import com.zeugmasolutions.localehelper.LocaleHelper
import java.util.*

/**
 * Allows to store locale configuration in app specific preferences system.
 */
interface ILocaleConfigurationStorage {
    fun loadLocale(context: Context): Locale
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

    override fun loadLocale(context: Context): Locale {
        val preferences = getPreferences(context)
        val default = Locale.getDefault()
        val language = preferences.getString(SELECTED_LANGUAGE, default.language) ?: return default
        val country = preferences.getString(SELECTED_COUNTRY, default.country) ?: return default
        return Locale(language, country)
    }

    override fun storeLocale(context: Context, locale: Locale?) {
        if (locale == null) return
        getPreferences(context).edit()
            .putString(SELECTED_LANGUAGE, locale.language)
            .putString(SELECTED_COUNTRY, locale.country)
            .apply()
    }
}