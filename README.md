# Localization

Supporting multiple languages on your android application.

![screenshot gif](https://github.com/prongbang/Localization/blob/master/screenshots/screenshots.gif?raw=true)

## Download

```build.gradle```:
```
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

- Support Library

```gradle
implementation 'com.github.prongbang:localization:1.0.1'
```

- AndroidX

```gradle
implementation 'com.github.prongbang:localization:2.1.2'
```

## How to use

- MainApplication.kt

```kotlin
import com.prongbang.localization.LocalizationApplication

class MainApplication : LocalizationApplication()
```

- MainActivity.kt

```kotlin
import com.prongbang.localization.LocalizationAppCompatActivity

class MainActivity : LocalizationAppCompatActivity() {

}
```

- SettingActivity.kt

```kotlin
import com.prongbang.localization.ENGLISH
import com.prongbang.localization.LocalizationAppCompatActivity
import com.prongbang.localization.Localize
import com.prongbang.localization.THAI

class SettingActivity : LocalizationAppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_setting)

		tvThai.setOnClickListener { setLocale(Localize.THAI) }
		tvEnglish.setOnClickListener { setLocale(Localize.ENGLISH) }
	}

	override fun onConfigurationChanged(newConfig: Configuration) {
		openPrepareLocalize() // used only in setting activity
		super.onConfigurationChanged(newConfig)
	}
}
```