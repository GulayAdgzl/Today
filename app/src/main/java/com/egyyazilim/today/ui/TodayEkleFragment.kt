package com.egyyazilim.today.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.egyyazilim.today.R
import com.egyyazilim.today.databinding.FragmentTodayEkleBinding
import androidx.databinding.DataBindingUtil
import com.egyyazilim.today.room.TodayDatabase
import com.egyyazilim.today.room.TodayEntity
import com.egyyazilim.today.viewModel.TodayViewModel
import com.egyyazilim.today.viewModel.TodayViewModelFactory




class TodayEkleFragment : Fragment() {
    private lateinit var binding:FragmentTodayEkleBinding
    private lateinit var todayDB:TodayDatabase
    private lateinit var todayViewModel: TodayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_today_ekle,container,false)

        val application= requireNotNull(this.activity).application
        val dataSource=TodayDatabase.getTodayDatabase(application)?.todayDao


        val viewModelFactory=dataSource?.let { TodayViewModelFactory(it,application) }

        todayViewModel=viewModelFactory?.let {
            ViewModelProvider(this,it).get(TodayViewModel::class.java)
        }!!

        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        todayDB=TodayDatabase.getTodayDatabase(requireContext())!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.apply {
            btnEkle.setOnClickListener {
                val todayCom=ediTextToday.text.toString()
                val todayDate=editSelectDate.text.toString()
                todayViewModel.ekletoday(
                    TodayEntity(
                        todayCom = todayCom,
                        todayDate = todayDate
                    )
                )
                findNavController().navigate(R.id.todayEkleToAnasayfa)
            }

        }
    }


}