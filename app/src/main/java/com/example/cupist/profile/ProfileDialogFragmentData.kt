package com.example.cupist.profile

import android.content.Context
import android.graphics.Point
import android.view.WindowManager
import com.example.cupist.data.ProfileMetaData

object ProfileDialogFragmentData {
    fun getDeviceSize(context: Context): Point {
        val windowManager =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val deviceSize = Point()
        display.getSize(deviceSize)
        return deviceSize
    }

    fun makeArrayData(type: Int, data: ProfileMetaData?): List<String> {
        val array = ArrayList<String>()
        when (type) {
            0 -> {
                var min = data?.heightRange?.min ?: 120
                var max = data?.heightRange?.max ?: 220

                while (min <= max) {
                    array.add("${min}cm")
                    min += 1
                }

            }
            1 -> {
                data?.bodyTypes?.forEachIndexed { _, metaData ->
                    if (metaData.name != null) {
                        array.add(metaData.name!!)
                    }
                }
            }
            else -> {
                data?.educations?.forEachIndexed { _, metaData ->
                    if (metaData.name != null) {
                        array.add(metaData.name!!)
                    }
                }
            }
        }
        return array
    }

}