package com.heinnovator.koenawin.utils

import android.content.Context
import androidx.activity.ComponentActivity
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class SharePreferencesDelegate(
    private val context: Context,
    private val name: String,
    private val defValue: Int,
): ReadWriteProperty<Any?, Int> {

    private val sharedPreferences by lazy {
        context.getSharedPreferences("my_prefs", ComponentActivity.MODE_PRIVATE)
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return sharedPreferences.getInt(name, defValue)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        sharedPreferences.edit().putInt(name, value).apply()
    }
}

fun Context.mySharedPrefs(name: String, defValue: Int) = SharePreferencesDelegate(this, name, defValue)
