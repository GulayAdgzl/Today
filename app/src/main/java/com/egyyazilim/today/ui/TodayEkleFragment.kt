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
import com.egyyazilim.today.utils.extensions.toFormat
import com.egyyazilim.today.viewModel.TodayViewModel
import com.egyyazilim.today.viewModel.TodayViewModelFactory
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.*

const val CURRENT_DATE_FORMAT="dd MMM yyyy"
const val TAG_DATE_PICKER="Tag_Date_Picker"

class TodayEkleFragment : Fragment() {
    private var selectedDate= Date()
    private lateinit var binding:FragmentTodayEkleBinding
    private lateinit var todayDB:TodayDatabase
    private lateinit var todayViewModel: TodayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=inflater.inflate(R.layout.fragment_today_ekle,container,false)

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
        todayViewModel.fetchDate(selectedDate)

        binding.apply {
            btnEkle.setOnClickListener {
                val todayCom=ediTextToday.text.toString()

                todayViewModel.ekletoday(
                    TodayEntity(
                        todayCom = todayCom,
                    )
                )
                findNavController().navigate(R.id.todayEkleToAnasayfa)
            }

        }
    }
    private fun initViews()= with(binding){
        btnSelectDate.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText(getString(R.string.select_date))
                .setSelection(selectedDate.time)
                .build()
            datePicker.addOnPositiveButtonClickListener { timestamp ->
                fetchDate(Date(timestamp))
            }
        }
    }

    private fun fetchDate(date: Date) {
        selectedDate=date
        binding.btnSelectDate.text=selectedDate.toFormat(CURRENT_DATE_FORMAT)
            //.toFormat(CURRENT_DATE_FORMAT)
        todayViewModel.fetchDate(selectedDate)


    }

}