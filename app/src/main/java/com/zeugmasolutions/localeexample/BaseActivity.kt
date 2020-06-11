package com.zeugmasolutions.localeexample

import android.content.Context
import com.zeugmasolutions.localehelper.LocaleAwareCompatActivity
import com.zeugmasolutions.localehelper.LocaleHelper

open class BaseActivity : LocaleAwareCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        LocaleHelper.configurationBlock = {
            it.fontScale = 1.0f
        }
        super.attachBaseContext(newBase)
    }
}