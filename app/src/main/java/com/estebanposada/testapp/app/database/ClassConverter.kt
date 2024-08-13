package com.estebanposada.testapp.app.database

import androidx.room.TypeConverter
import com.google.gson.Gson


class ClassConverter {
    @TypeConverter
    fun convertAttributeListToJSONString(attributeList: AttributeList): String =
        Gson().toJson(attributeList)

    @TypeConverter
    fun convertJSONStringToAttributeList(jsonString: String): AttributeList =
        Gson().fromJson(jsonString, AttributeList::class.java)
}
