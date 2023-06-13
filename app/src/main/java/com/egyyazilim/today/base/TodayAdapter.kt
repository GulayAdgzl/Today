package com.egyyazilim.today.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.egyyazilim.today.databinding.FragmentAnasayfaBinding
import com.egyyazilim.today.databinding.ItemCardBinding
import com.egyyazilim.today.room.TodayEntity

class TodayAdapter(private var todayList: List<TodayEntity?>):
RecyclerView.Adapter<TodayAdapter.CardHolder>() {
    class CardHolder(val itemCardBinding: ItemCardBinding):RecyclerView.ViewHolder(itemCardBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val itemCardBinding=ItemCardBinding.inflate(layoutInflater,parent,false)
        return CardHolder(itemCardBinding)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        val today=todayList[position]

        holder.itemCardBinding.apply {
            today?.let{
                textViewTarih.text=today.todayDate.toString()
                textViewYazi.text=today.todayCom
            }
            itemCard.setOnClickListener { button ->
                today?.let {
                    //Burda kaldım
                    val anasayfaToDetay=
                        AnasayfaFragmentDirections.anasayfaToGuncelle(urun)
                    Navigation.findNavController(button).navigate(anasayfaToDetay)
                }
            }
        }
    }

    override fun getItemCount() = todayList.size

}