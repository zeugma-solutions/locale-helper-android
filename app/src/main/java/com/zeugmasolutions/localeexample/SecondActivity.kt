package com.zeugmasolutions.localeexample

import android.os.Bundle
import com.zeugmasolutions.localehelper.Locales
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.view_language_buttons.*
import java.util.*

class SecondActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        setTitle(R.string.second_activity_title)

        toTRButton.setOnClickListener { updateLocale(Locales.Turkish) }
        toENButton.setOnClickListener { updateLocale(Locale.ENGLISH) }
        toCNButton.setOnClickListener { updateLocale(Locale.CHINA) }
        toURButton.setOnClickListener { updateLocale(Locales.Urdu) }

        backButton.setOnClickListener { finish() }

        mapView.onCreate(savedInstanceState)
    }
}
