package com.egyyazilim.today.room

import androidx.room.*
import java.util.*

@Dao
interface TodayDao {
    @Query("SELECT * FROM today WHERE timestamp BETWEEN:startDate AND :endDate")
    suspend fun fetchBy(startDate: Date, endDate: Date): List<TodayEntity>
    @Insert
    suspend fun todayEkle(today:TodayEntity)

    @Update
    suspend fun todayGuncelle(today: TodayEntity)

    @Delete
    suspend fun todaySil(today: TodayEntity)

    @Query("SELECT * FROM today_app")
    suspend fun tumToday():List<TodayEntity>

    @Query("SELECT * FROM today_app WHERE id =:key")
    suspend fun todayGetir(key:Int):TodayEntity?
}