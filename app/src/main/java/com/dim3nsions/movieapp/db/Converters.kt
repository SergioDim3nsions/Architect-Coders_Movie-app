package com.dim3nsions.movieapp.db

import androidx.room.TypeConverter
import com.dim3nsions.movieapp.db.model.DBCast
import com.dim3nsions.movieapp.db.model.DBCompany
import com.dim3nsions.movieapp.db.model.DBGenre
import com.google.gson.Gson

@Suppress("UNCHECKED_CAST")
class Converters {

    @TypeConverter
    fun fromListGenreToString(list: List<DBGenre>): String = list.toString()

    @TypeConverter
    fun fromStringToListGenre(listString: String): List<DBGenre> =
        listString.toList() as List<DBGenre>


    @TypeConverter
    fun fromListCompanyToString(list: List<DBCompany>): String = list.toString()

    @TypeConverter
    fun fromStringToListCompany(listString: String): List<DBCompany> =
        listString.toList() as List<DBCompany>


    @TypeConverter
    fun fromListCastToString(list: List<DBCast>): String = list.toString()

    @TypeConverter
    fun fromStringToListCast(listString: String): List<DBCast> = listString.toList() as List<DBCast>


    @TypeConverter
    fun fromListIntToJson(value: List<Int>) = Gson().toJson(value)

    @TypeConverter
    fun jsonToListInt(value: String) = Gson().fromJson(value, Array<Int>::class.java).toList()
}