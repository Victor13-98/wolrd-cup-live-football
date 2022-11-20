package com.xonicsports_sportnews.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceUtils(context: Context) {
    private val phoneNumberKey: String = "phone"
    private val planKey: String = "plan"
    private val startDateKey: String = "startDate"
    private val endDateKey: String = "endDate"



    private val sharedPreferencesName: String = "CashOceanSharedPreference"
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()


    fun savePhoneNumber(phoneNumber: String) {
        editor.putString(phoneNumberKey, phoneNumber).commit()
    }

    fun getPhoneNumber(): String? {
        return sharedPreferences.getString(phoneNumberKey, "")
    }

    fun savePlan(plan: String) {
        editor.putString(planKey, plan).commit()
    }

    fun getPlan(): String? {
        return sharedPreferences.getString(planKey, "")
    }

    fun saveStartDay(date: String) {
        editor.putString(startDateKey, date).commit()
    }

    fun getStartDay(): String? {
        return sharedPreferences.getString(startDateKey, "")
    }


    fun saveEndDay(date: String) {
        editor.putString(endDateKey, date).commit()
    }

    fun getEndDay(): String? {
        return sharedPreferences.getString(endDateKey, "")
    }



}