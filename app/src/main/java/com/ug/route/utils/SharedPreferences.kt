package com.ug.route.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferences {

    private var sharedPrefs : SharedPreferences? = null

    fun initPrefs(context: Context){
        sharedPrefs = context.getSharedPreferences(SHARED_PREFS_NAME,Context.MODE_PRIVATE)
    }

    var loggedEmail : String?
        get() = sharedPrefs?.getString(LOGGED_EMAIL_KEY, null)
        set(value) {
            sharedPrefs?.edit()?.putString(LOGGED_EMAIL_KEY,value)?.apply()
        }

    var selectedCategory : String?
        get() = sharedPrefs?.getString(SELECTED_CATEGORY_KEY, null)
        set(value) {
            sharedPrefs?.edit()?.putString(SELECTED_CATEGORY_KEY,value)?.apply()
        }

    private const val SHARED_PREFS_NAME = "RoutePreferences"
    private const val LOGGED_EMAIL_KEY = "LoggedEmailKey"
    private const val SELECTED_CATEGORY_KEY = "SelectedCategoryKey"
}