package com.zeugmasolutions.localehelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*
import java.util.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        setTitle(R.string.second_activity_title)

        toTRButton.setOnClickListener { updateLanguage(Locales.Turkish) }
        toENButton.setOnClickListener { updateLanguage(Locale.ENGLISH) }
        toCNButton.setOnClickListener { updateLanguage(Locale.CHINA) }

        backButton.setOnClickListener { finish() }
    }

    private fun updateLanguage(locale: Locale) {
        LocaleHelper.setLocale(this, locale)
        recreate()
    }
}
