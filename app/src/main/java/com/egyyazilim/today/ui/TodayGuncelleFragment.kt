package com.egyyazilim.today.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.egyyazilim.today.R
import com.egyyazilim.today.databinding.FragmentTodayGuncelleBinding
import com.egyyazilim.today.room.TodayDatabase
import com.egyyazilim.today.room.TodayEntity
import com.egyyazilim.today.viewModel.TodayViewModel
import com.egyyazilim.today.viewModel.TodayViewModelFactory


class TodayGuncelleFragment : Fragment() {
    private lateinit var today:TodayEntity
    private lateinit var binding: FragmentTodayGuncelleBinding
    private lateinit var todayDB: TodayDatabase
    private lateinit var todayViewModel: TodayViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_today_guncelle,container,false)

        val bundle: TodayGuncelleFragmentArgs by navArgs()
        today=bundle.today

        val application= requireNotNull(this.activity).application
        val dataSource=TodayDatabase.getTodayDatabase(application)?.todayDao

        val viewModelFactory=dataSource?.let { TodayViewModelFactory(it, application) }
            todayViewModel= viewModelFactory?.let {
                ViewModelProvider(this,it).get(TodayViewModel::class.java)
            }!!



        binding.setLifecycleOwner(this)

        return binding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        todayDB=TodayDatabase.getTodayDatabase(requireContext())!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val todayID=today.id
        binding.apply {
            ediTextGuncelToday.setText(today.todayCom)
            SelectGuncelDate.setText(today.todayDate)

            btnGuncelle.setOnClickListener {
                val adTarih=SelectGuncelDate.text.toString()
                val todayCom=ediTextGuncelToday.text.toString()

                today.todayCom=todayCom
                today.todayDate=adTarih

                todayViewModel.guncelleToday(today)

                findNavController().navigate(R.id.guncelleToAnasayfa)
            }
            btnSil.setOnClickListener {
                todayViewModel.silToday(today)

                findNavController().navigate(R.id.guncelleToAnasayfa)
            }

        }
    }


}