package iimdemo.killiangalea.com.gameapp.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

object SharedPreferencesUtil {

    private const val COUNTER_KEY = "counter"

    private var sharedPreferences: SharedPreferences? = null

    fun initSharedPreferences(context: Context) {
        sharedPreferences = context.getSharedPreferences(context.packageName, MODE_PRIVATE)
    }

    fun resetCounter() {
        sharedPreferences?.edit()?.remove(COUNTER_KEY)?.apply()
    }

    fun setCounter(count: Int) {
        sharedPreferences?.edit()?.putInt(COUNTER_KEY, count)?.apply()
    }

    fun getCounter(): Int? {
        return sharedPreferences?.getInt(COUNTER_KEY, 0)
    }

}