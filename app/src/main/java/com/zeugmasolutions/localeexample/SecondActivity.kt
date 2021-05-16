package com.zeugmasolutions.localeexample

import android.os.Bundle
import com.zeugmasolutions.localehelper.LocaleHelper
import com.zeugmasolutions.localehelper.Locales
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.view_language_buttons.*
import java.util.Locale

class SecondActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        setTitle(R.string.second_activity_title)

        language_picker.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.language_default -> updateLocale(null)
                R.id.language_tr -> updateLocale(Locales.Turkish)
                R.id.language_en -> updateLocale(Locales.EnglishUS)
                R.id.language_cn -> updateLocale(Locale.CHINA)
                R.id.language_ur -> updateLocale(Locales.Urdu)
            }
        }

        setLanguageSelection()

        backButton.setOnClickListener { finish() }

        mapView.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        setLanguageSelection()
    }
    
    private fun setLanguageSelection() {
        var languageSelection = R.id.language_default
        if( LocaleHelper.hasLocaleSelection(this) ) {
            languageSelection = when(LocaleHelper.getLocale(this)) {
                Locales.Turkish -> R.id.language_tr
                Locales.EnglishUS -> R.id.language_en
                Locale.CHINA -> R.id.language_cn
                Locales.Urdu -> R.id.language_ur
                else -> R.id.language_default
            }
        }

        language_picker.check(languageSelection)
    }
}
