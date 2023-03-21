package com.natiqhaciyef.voyagers.data.roomdb

import android.text.TextUtils
import androidx.room.TypeConverter
import com.google.gson.Gson


class RequestConverter <P> {
    @TypeConverter
    inline fun <reified T : P> stringToCustomModel(string: String): T? {
        if (TextUtils.isEmpty(string))
            return null
        return Gson().fromJson(string, T::class.java)
    }

    @TypeConverter
    fun customModelToString(sectionModel: P): String {
        return Gson().toJson(sectionModel)
    }
}