package com.egyyazilim.today.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.egyyazilim.today.room.TodayDao
import com.egyyazilim.today.room.TodayEntity
import kotlinx.coroutines.launch

class TodayViewModel (val db:TodayDao,application: Application):AndroidViewModel(application){
    var todayList=MutableLiveData<List<TodayEntity>>()

    init {
        getTumToday()
    }

    private fun getTumToday(){
        viewModelScope.launch {
            todayList.value=db.tumToday()
        }
    }
    fun ekletoday(today:TodayEntity){
        viewModelScope.launch {
            db.todayEkle(today)
        }
    }
    fun guncelleToday(today: TodayEntity) {
        viewModelScope.launch {
            db.todayGuncelle(today)
        }
    }

    fun silToday(today:TodayEntity){
        viewModelScope.launch {
            db.todaySil(today)
        }
    }

}