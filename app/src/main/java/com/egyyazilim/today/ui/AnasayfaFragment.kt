package com.egyyazilim.today.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.egyyazilim.today.R
import com.egyyazilim.today.databinding.FragmentAnasayfaBinding
import com.egyyazilim.today.room.TodayDatabase
import com.egyyazilim.today.room.TodayEntity


class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var urunList: List<TodayEntity>
    private lateinit var urunDB: TodayDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonYeniUrun.setOnClickListener {
                findNavController().navigate(R.id.anasayfaTotodayEkleFragment)
            }

        }
    }
}