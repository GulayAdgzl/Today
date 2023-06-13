package com.egyyazilim.today.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.egyyazilim.today.R
import com.egyyazilim.today.databinding.FragmentAnasayfaBinding
import com.egyyazilim.today.room.TodayDatabase
import com.egyyazilim.today.room.TodayEntity
import com.google.android.material.snackbar.Snackbar


class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var todayList: List<TodayEntity>
    private lateinit var todayDB: TodayDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        todayDB=TodayDatabase.getTodayDatabase(requireContext())!!
        todayList=todayDB.todayDao.tumToday()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonYeniUrun.setOnClickListener {
                findNavController().navigate(R.id.anasayfaTotodayEkleFragment)
            } } }
    fun tumTodayGoster(){
        binding.apply {
            if(todayList.isEmpty()){
                Snackbar.make(requireView(),"Today Bulunamadı",1000).show()
            }else{
                val todayAdapter=TodayAdapter(todayList)
                rvToday.adapter=todayAdapter
                rvToday.layoutManager=GridLayoutManager(context,2)
                rvToday.setHasFixedSize(true)
            }
        }
    }
}