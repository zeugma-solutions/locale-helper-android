Change Language Programmatically in Android
==============================

![Header image](assets/change-language-programmatically.jpg)

This is a helper library to change the language programmatically in Android.

> Android by default uses the locale of the device to select the
> appropriate language dependent resources. And most of the time this
> behaviour is enough for common applications.

However, there are cases where you would want to change the language of your app and the UI of the app. As a result, ```LocaleHelper``` has emerged.

**Download**
=
```groovy
implementation 'com.zeugmasolutions.localehelper:locale-helper-android:1.0.2'
```
**Features**
=
1. Changes language on-the-fly
2. Persists the changes in `Preferences` automatically
3. Detects changes when activity loads from backstack
4. Detects Right-To-Left (RTL) languages and updates layout direction
5. Small footprint (~3KB, ~50 methods), easy to use

**Demo**
=
![Demo video](https://media.giphy.com/media/1yidskG8wYqVIy3Pku/giphy.gif)

[Demo source code](https://github.com/zeugma-solutions/locale-helper-android/tree/master/app "Demo source code")

**Setup**
=
**(Option 1) Using base classes**
1. Extend your app class
```kotlin
class App : LocaleAwareApplication() {
}
```
2. Extend your base activity class
```kotlin
open class BaseActivity : LocaleAwareCompatActivity() {  
}
```
`LocaleAwareCompatActivity` provides a helper method called ```updateLocale```

That's it.

**(Option 2) Using delegates**

This option requires you to do extra steps if you don't want to extend from base classes.
1. On your custom Application class override `onAttach` and `onConfiguration` change methods.
```kotlin
class MyApp : Application() {  
    private val localeAppDelegate = LocaleHelperApplicationDelegate()

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(localeAppDelegate.attachBaseContext(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        localeAppDelegate.onConfigurationChanged(this)
    } 
}
```
2. On your base activity class override `onAttach` and add a helper method
```kotlin
open class BaseActivity : AppCompatActivity() {  
    private val localeDelegate = LocaleHelperActivityDelegateImpl()

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(localeDelegate.attachBaseContext(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        localeDelegate.onCreate(this)
    }

    override fun onResume() {
        super.onResume()
        localeDelegate.onResumed(this)
    }

    override fun onPause() {
        super.onPause()
        localeDelegate.onPaused()
    }

    open fun updateLocale(locale: Locale) {
        localeDelegate.setLocale(this, locale)
    }  
}
```
**Usage**
=
(Option 1)

If you're using the base classes, just call `updateLocale(newLocale)`. It will then update the locale and restart the activity.

Example:
```kotlin 
toTRButton.setOnClickListener { updateLocale(Locales.Turkish) }
``` 
In `java.util.Locale` class most of the common `Locales` and their variants are defined. However, it doesn't contain all the Locales so `com.zeugmasolutions.Locales` provides the missing ones for easy access. 


(Option 2)

To change the locale you can call `setLocale` on the delegate
```kotlin 
localeDelegate.setLocale(this, locale)
``` 
The delegate will set the new locale and recreate the activity.

**Notes**
=
1. actionbar(toolbar) title should be set when `onCreate` is called.
```kotlin 
override fun onCreate(savedInstanceState: Bundle?) {
	super.onCreate(savedInstanceState)
	setContentView(R.layout.activity_main) //sample

	setTitle(R.string.main_activity_title) //sample
}
``` 
2. If your locale is Right-To-Left(RTL) don't forget to enable it in the `AndroidManifest.xml`
```xml
<application
	android:supportsRtl="true">
</application>
``` 
3. Google introduced a new App Bundle format to split apk files in smaller sizes when they’re being installed on the client devices. However, this means that we cannot have dynamic language changes in our applications.

To prevent that split for language files we need to add extra lines in our build.gradle file inside the app folder like below.
```groovy
android {
    //...
    //... removed for brevity
    bundle {
        language {
            enableSplit = false
        }
    }
}
```
