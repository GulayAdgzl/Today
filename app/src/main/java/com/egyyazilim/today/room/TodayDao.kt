package com.egyyazilim.today.room

import androidx.room.*

@Dao
interface TodayDao {

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