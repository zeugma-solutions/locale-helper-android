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
implementation 'com.zeugmasolutions.localehelper:locale-helper-android:1.0.0'
```
**Usage**
=
**(Option 1) Using base classes**
1. Extend your app class
```kotlin
class App : LocaleAwareApp() {
}
```
2. Extend your base activity class
```kotlin
open class BaseActivity : LocaleAwareCompatActivity() {  
}
```
LocaleAwareCompatActivity provides a helper method called ```updateLocale```

That's it.

**(Option 2) Overriding methods**
This option requires you to do extra steps if you don't want to extend from base classes.
1. On your custom Application class override onAttach and onConfiguration change methods.
```kotlin
open class MyApp : Application() {  
    override fun attachBaseContext(base: Context) {  
        super.attachBaseContext(LocaleHelper.onAttach(base))  
    }  
  
    override fun onConfigurationChanged(newConfig: Configuration) {  
        LocaleHelper.onAttach(this)  
    }  
}
```
2. On your base activity class override onAttach and add a helper method
```kotlin
open class BaseActivity : AppCompatActivity() {  
  
    override fun attachBaseContext(newBase: Context) {  
        super.attachBaseContext(LocaleHelper.onAttach(newBase))  
    }  
  
    open fun updateLocale(locale: Locale) {  
        LocaleHelper.setLocale(this, locale)  
        recreate()  
    }  
}
```

