package com.egyyazilim.today.room

import androidx.room.*
import java.util.*

@Dao
interface TodayDao {
    @Query("SELECT * FROM today WHERE timestamp BETWEEN:startDate AND :endDate")
    fun fetchBy(startDate: Date, endDate: Date): List<TodayEntity>
    @Insert
    fun todayEkle(today:TodayEntity)

    @Update
    fun todayGuncelle(today: TodayEntity)

    @Delete
    fun todaySil(today: TodayEntity)

    @Query("SELECT * FROM today_app")
    suspend fun tumToday():List<TodayEntity>

    @Query("SELECT * FROM today_app WHERE id =:key")
    suspend fun todayGetir(key:Int):TodayEntity?
}