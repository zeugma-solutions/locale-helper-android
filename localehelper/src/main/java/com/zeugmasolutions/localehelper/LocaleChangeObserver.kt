package com.zeugmasolutions.localehelper

import android.content.Context

class LocaleChangeObserver {
    fun attachBaseContext(newBase: Context): Context {
        return LocaleHelper.onAttach(newBase)
    }
}