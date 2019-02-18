package com.zeugmasolutions.localeexample

import android.os.Bundle
import com.zeugmasolutions.localehelper.Locales
import kotlinx.android.synthetic.main.activity_second.*
import java.util.*

class SecondActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        setTitle(R.string.second_activity_title)

        toTRButton.setOnClickListener { updateLocale(Locales.Turkish) }
        toENButton.setOnClickListener { updateLocale(Locale.ENGLISH) }
        toCNButton.setOnClickListener { updateLocale(Locale.CHINA) }

        backButton.setOnClickListener { finish() }
    }
}
