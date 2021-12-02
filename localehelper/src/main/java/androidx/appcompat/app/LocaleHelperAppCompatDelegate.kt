package androidx.appcompat.app

import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.view.ActionMode
import androidx.appcompat.widget.Toolbar
import com.zeugmasolutions.localehelper.LOG_TAG
import com.zeugmasolutions.localehelper.LocaleHelper
import com.zeugmasolutions.localehelper.log
import com.zeugmasolutions.localehelper.toDebugString

class LocaleHelperAppCompatDelegate(private val superDelegate: AppCompatDelegate) :
    AppCompatDelegate() {

    override fun getSupportActionBar() = superDelegate.supportActionBar

    override fun setSupportActionBar(toolbar: Toolbar?) = superDelegate.setSupportActionBar(toolbar)

    override fun getMenuInflater(): MenuInflater? = superDelegate.menuInflater

    override fun onCreate(savedInstanceState: Bundle?) {
        superDelegate.onCreate(savedInstanceState)
        removeActivityDelegate(superDelegate)
        addActiveDelegate(this)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) =
        superDelegate.onPostCreate(savedInstanceState)

    override fun onConfigurationChanged(newConfig: Configuration?) =
        superDelegate.onConfigurationChanged(newConfig)

    override fun onStart() = superDelegate.onStart()

    override fun onStop() = superDelegate.onStop()

    override fun onPostResume() = superDelegate.onPostResume()

    override fun setTheme(themeResId: Int) = superDelegate.setTheme(themeResId)

    override fun <T : View?> findViewById(id: Int) = superDelegate.findViewById<T>(id)

    override fun setContentView(v: View?) = superDelegate.setContentView(v)

    override fun setContentView(resId: Int) = superDelegate.setContentView(resId)

    override fun setContentView(v: View?, lp: ViewGroup.LayoutParams?) =
        superDelegate.setContentView(v, lp)

    override fun addContentView(v: View?, lp: ViewGroup.LayoutParams?) =
        superDelegate.addContentView(v, lp)

    override fun attachBaseContext2(originalContext: Context): Context {
        val superDelegateContext = super.attachBaseContext2(originalContext)
        val wrappedContext = wrap(superDelegateContext)
        log {
            "\n -> " + originalContext.toDebugString() +
            "\n -> appCompatDelegateContext (AppCompatDelegate): " + superDelegateContext.toDebugString() +
            "\n -> wrappedContext: " + wrappedContext.toDebugString() + "\n"
        }
        return wrappedContext
    }

    override fun setTitle(title: CharSequence?) = superDelegate.setTitle(title)

    override fun invalidateOptionsMenu() = superDelegate.invalidateOptionsMenu()

    override fun onDestroy() {
        superDelegate.onDestroy()
        removeActivityDelegate(this)
    }

    override fun getDrawerToggleDelegate() = superDelegate.drawerToggleDelegate

    override fun requestWindowFeature(featureId: Int) =
        superDelegate.requestWindowFeature(featureId)

    override fun hasWindowFeature(featureId: Int) = superDelegate.hasWindowFeature(featureId)

    override fun startSupportActionMode(callback: ActionMode.Callback) =
        superDelegate.startSupportActionMode(callback)

    override fun installViewFactory() = superDelegate.installViewFactory()

    override fun createView(
        parent: View?,
        name: String?,
        context: Context,
        attrs: AttributeSet
    ): View? = superDelegate.createView(parent, name, context, attrs)

    override fun setHandleNativeActionModesEnabled(enabled: Boolean) {
        superDelegate.isHandleNativeActionModesEnabled = enabled
    }

    override fun isHandleNativeActionModesEnabled() = superDelegate.isHandleNativeActionModesEnabled

    override fun onSaveInstanceState(outState: Bundle?) =
        superDelegate.onSaveInstanceState(outState)

    override fun applyDayNight() = superDelegate.applyDayNight()

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun setLocalNightMode(mode: Int) {
        superDelegate.localNightMode = mode
    }

    override fun getLocalNightMode() = superDelegate.localNightMode

    private fun wrap(context: Context): Context = LocaleHelper.onAttach(context)

    override fun attachBaseContext(context: Context?) = superDelegate.attachBaseContext(context)
}
