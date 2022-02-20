package com.example.cupist.util

import android.content.Context
import android.content.SharedPreferences

class Preference {

    var introduce: String?
        get() = preferenceData.getString(INTRODUCE, null)
        set(introduce) {
            val edit = preferenceData.edit()
            edit.putString(INTRODUCE, introduce)
            edit.apply()
        }

    var height: String?
        get() = preferenceData.getString(HEIGHT, null)
        set(introduce) {
            val edit = preferenceData.edit()
            edit.putString(HEIGHT, introduce)
            edit.apply()
        }

    var bodyType: String?
        get() = preferenceData.getString(BODY_TYPE, null)
        set(introduce) {
            val edit = preferenceData.edit()
            edit.putString(BODY_TYPE, introduce)
            edit.apply()
        }

    var job: String?
        get() = preferenceData.getString(JOB, null)
        set(introduce) {
            val edit = preferenceData.edit()
            edit.putString(JOB, introduce)
            edit.apply()
        }

    var education: String?
        get() = preferenceData.getString(EDUCATION, null)
        set(introduce) {
            val edit = preferenceData.edit()
            edit.putString(EDUCATION, introduce)
            edit.apply()
        }

    companion object {
        private lateinit var preferenceData: SharedPreferences
        private var instance: Preference? = null
        private const val GLAM_PREFERENCE_NAME = "glam"
        const val INTRODUCE = "user_introduce"
        const val HEIGHT = "user_height"
        const val BODY_TYPE = "user_body_type"
        const val JOB = "user_job"
        const val EDUCATION = "user_education"

        fun initPreference(context: Context?) {
            preferenceData =
                context!!.getSharedPreferences(GLAM_PREFERENCE_NAME, Context.MODE_PRIVATE)
        }

        fun getInstance(): Preference {
            if (instance == null) {
                instance = Preference()
            }
            return instance as Preference
        }
    }
}