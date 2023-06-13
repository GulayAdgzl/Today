package com.egyyazilim.today.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.egyyazilim.today.room.TodayDao
import com.egyyazilim.today.room.TodayEntity
import com.egyyazilim.today.utils.extensions.endOfDay
import com.egyyazilim.today.utils.extensions.startOfDay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*

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
    fun silToday(today:TodayEntity){
        viewModelScope.launch {
            db.todaySil(today)
        }
    }
    fun fetchDate(date: Date) {
        viewModelScope.launch(Dispatchers.IO) {
            val waterList = db.fetchBy(
                startDate = date.startOfDay(),
                endDate = date.endOfDay()
            )

        }
    }
}