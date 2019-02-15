package com.zeugmasolutions.localehelper

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toTRButton.setOnClickListener { updateLanguage(Locales.Turkish) }
        toENButton.setOnClickListener { updateLanguage(Locale.ENGLISH) }
        toCNButton.setOnClickListener { updateLanguage(Locale.CHINA) }

        secondButton.setOnClickListener { startActivity(Intent(this, SecondActivity::class.java)) }

        Log.i("gunhan", "onCreate")
    }

    override fun attachBaseContext(newBase: Context) {
        Log.i(
            "gunhan",
            "attachBaseContext: current:${newBase.resources.configuration.locale.language} new: ${LocaleHelper.onAttach(
                newBase
            ).resources.configuration.locale.language}"
        )
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }

//    override fun onResume() {
//        super.onResume()
//        Log.i("gunhan", "onResume")
//    }

    override fun onStop() {
        super.onStop()
        Log.i("gunhan", "onStop")
    }

//    override fun onPause() {
//        super.onPause()
//        Log.i("gunhan", "onPause")
//    }

    override fun onStart() {
        super.onStart()
        Log.i("gunhan", "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("gunhan", "onRestart")
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        Log.i("gunhan", "onConfigurationChanged: ${newConfig.locale.language}")
        super.onConfigurationChanged(newConfig)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("gunhan", "onDestroy")
    }

    private fun updateLanguage(locale: Locale) {
        Log.i("gunhan", "                    updateLanguage")

        LocaleHelper.setLocale(this, locale)
        recreate()
    }


}
