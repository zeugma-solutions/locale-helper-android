/**
 * Debug support.
 */
package com.zeugmasolutions.localehelper

import android.content.Context
import android.content.res.Configuration
import android.util.Log

const val debuggingIsEnabled = true

fun Configuration.toDebugString(): String {
    return "Configuration[" + this.locales.toLanguageTags() + ",uiMode=" + this.uiMode.toDebugString() + "]"
}

private fun Int.toDebugString(): String {
    return when (this and Configuration.UI_MODE_NIGHT_MASK) {
        Configuration.UI_MODE_NIGHT_NO -> "Day"
        Configuration.UI_MODE_NIGHT_YES -> "Night"
        else -> {
            "Unknown"
        }
    }
}

fun Context.toDebugString(): String {
    return "[Context: " + this.javaClass.simpleName + "@" + this.hashCode() + ", " + this.resources.configuration.toDebugString() + ", theme: " +
            this.safeTheme() + "]"
}

private fun Context.safeTheme(): String {
    return try {
        this.theme.toString()
    } catch (e: Throwable) {
        "null"
    }
}

fun log(throwable: Throwable? = null, block: () -> String) {
    if (debuggingIsEnabled) {
        if (throwable != null) {
            Log.d(LOG_TAG, block(), throwable)
        } else {
            Log.d(LOG_TAG, block())
        }
    }
}
