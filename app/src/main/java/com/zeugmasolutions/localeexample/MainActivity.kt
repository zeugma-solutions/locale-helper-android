package com.zeugmasolutions.localeexample

import android.content.Intent
import android.os.Bundle
import com.zeugmasolutions.localehelper.Locales
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_language_buttons.*
import java.util.Locale

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTitle(R.string.main_activity_title)

        toTRButton.setOnClickListener { updateLocale(Locales.Turkish) }
        toENButton.setOnClickListener { updateLocale(Locale.ENGLISH) }
        toCNButton.setOnClickListener { updateLocale(Locale.CHINA) }
        toURButton.setOnClickListener { updateLocale(Locales.Urdu) }

        secondButton.setOnClickListener { startActivity(Intent(this, SecondActivity::class.java)) }
    }

    override fun updateLocale(locale: Locale) {
        super.updateLocale(locale)
        setTitle(R.string.main_activity_title)
    }
}
