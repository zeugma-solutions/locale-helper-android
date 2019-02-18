package com.zeugmasolutions.localeexample

import android.content.Intent
import android.os.Bundle
import com.zeugmasolutions.localehelper.Locales
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTitle(R.string.main_activity_title)

        toTRButton.setOnClickListener { updateLocale(Locales.Turkish) }
        toENButton.setOnClickListener { updateLocale(Locale.ENGLISH) }
        toCNButton.setOnClickListener { updateLocale(Locale.CHINA) }

        secondButton.setOnClickListener { startActivity(Intent(this, SecondActivity::class.java)) }
    }

}
