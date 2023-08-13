# Localization

[![](https://jitpack.io/v/prongbang/Localization.svg)](https://jitpack.io/#prongbang/Localization)
[![](https://jitpack.io/v/prongbang/Localization/month.svg)](https://jitpack.io/#prongbang/Localization)

[!["Buy Me A Coffee"](https://www.buymeacoffee.com/assets/img/custom_images/orange_img.png)](https://www.buymeacoffee.com/prongbang)

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

- AndroidX

```gradle
implementation 'com.github.prongbang:localization:2.1.2'
```

- Support Library

```gradle
implementation 'com.github.prongbang:localization:1.0.1'
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
import com.prongbang.localization.THAI
import com.prongbang.localization.LocalizationAppCompatActivity
import com.prongbang.localization.Localize

class SettingActivity : LocalizationAppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_setting)

		thaiButton.setOnClickListener { setLocale(Localize.THAI) }
		englishButton.setOnClickListener { setLocale(Localize.ENGLISH) }
	}

	override fun onConfigurationChanged(newConfig: Configuration) {
		openPrepareLocalize() // used only in setting activity
		super.onConfigurationChanged(newConfig)
	}
}
```
