package com.zeugmasolutions.localeexample

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.SeekBar
import com.zeugmasolutions.localehelper.Locales
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_language_buttons.*
import java.util.*
import kotlin.math.roundToInt

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

        fontSizeSeekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    fontSizeSeekbar?.setProgress(progress, true)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                seekBar?.run {
                    Application.FONT_SCALE = getFontScale(progress)
                    recreate()
                }
            }

        })

        fontSizeSeekbar.max = 3
        fontSizeSeekbar.progress = getSeekBarProgress(resources.configuration.fontScale)
    }

    private fun getSeekBarProgress(fontScale: Float) = ((fontScale - 0.8F) / 1.5F).roundToInt()

    private fun getFontScale(progressValue: Int) = 0.8F + (progressValue * 1.5F)

    override fun updateLocale(locale: Locale) {
        super.updateLocale(locale)
        setTitle(R.string.main_activity_title)
    }
}
