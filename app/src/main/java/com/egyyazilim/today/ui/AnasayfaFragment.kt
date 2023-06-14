package com.egyyazilim.today.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.egyyazilim.today.R
import com.egyyazilim.today.base.TodayAdapter
import com.egyyazilim.today.databinding.FragmentAnasayfaBinding
import com.egyyazilim.today.room.TodayDatabase
import com.egyyazilim.today.room.TodayEntity
import com.egyyazilim.today.viewModel.TodayViewModel
import com.egyyazilim.today.viewModel.TodayViewModelFactory
import com.google.android.material.snackbar.Snackbar


class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var todayList: List<TodayEntity>
    private lateinit var todayDB: TodayDatabase
    private lateinit var todayViewModel:TodayViewModel
    private lateinit var adapter:TodayAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa, container, false)

        val application= requireNotNull(this.activity).application
        val dataSource=TodayDatabase.getTodayDatabase(application)?.todayDao

        val viewModelFactory=dataSource?.let { TodayViewModelFactory(it,application) }
        todayViewModel=viewModelFactory?.let {
            ViewModelProvider(this,it).get(TodayViewModel::class.java)
        }!!
        todayViewModel.todayList.observe(viewLifecycleOwner){todaysList ->
            todayList=todaysList
            adapter= TodayAdapter(todayList)
            binding.adapter=adapter
        }
        binding.lifecycleOwner = this

        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        todayDB=TodayDatabase.getTodayDatabase(requireContext())!!

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tumTodayGoster()
        binding.apply {
            buttonYeniUrun.setOnClickListener {
                findNavController().navigate(R.id.anasayfaToTodayEkle)
            } } }
    fun tumTodayGoster(){
        todayViewModel.todayList.observe(viewLifecycleOwner) { todaysList ->
            todayList = todaysList

            binding.apply {
                if (todayList.isEmpty()) {
                    Snackbar.make(requireView(), "Today Bulunamadı", 1000).show()
                } else {
                    rvToday.layoutManager = GridLayoutManager(context, 2)
                    rvToday.setHasFixedSize(true)
                }
            }
        }
    }
}