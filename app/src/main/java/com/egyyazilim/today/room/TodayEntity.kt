package com.egyyazilim.today.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "today_app")
data class TodayEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id" )
    var id:Int=0,
    @ColumnInfo(name = "today_date")
    var todayDate:Date,
    @ColumnInfo(name = "today_com")
    var todayCom:String

):Serializable