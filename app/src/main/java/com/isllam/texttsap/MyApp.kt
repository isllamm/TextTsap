package com.isllam.texttsap

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.isllam.texttsap.data.PrefsHelper
import com.isllam.texttsap.utils.updateLanguage
import com.yariksoffice.lingver.Lingver
import java.util.*


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        PrefsHelper.init(applicationContext)
        Lingver.init(this, Locale("ar", "EG"))
        updateLanguage(applicationContext)
    }
}