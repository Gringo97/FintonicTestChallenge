package com.fintonic.presentation_layer.beer_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fintonic.presentation_layer.databinding.BeerItemBinding
import com.fintonic.presentation_layer.domain.BeerVo
import com.squareup.picasso.Picasso

class BeersAdapter(
    private var beerList: List<BeerVo>,
    private val onItemSelected: (BeerActionView) -> Unit
) : RecyclerView.Adapter<BeersAdapter.BeerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BeerViewHolder(
        BeerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )


    override fun getItemCount() = beerList.size

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {

        with(beerList[position]) {

            holder.apply {

                this.itemView.setOnClickListener {
                    onItemSelected.invoke(BeerActionView.BeerItemClick(this@with))
                }

                Picasso.get().load(this@with.imgUrl)
                    //.error(R.drawable.)
                    .into(this.binding.beerImg)
            }
        }
    }


    /**
     * Updates the data displayed by the adapter
     *
     * @param [newData] A list with the new data to update
     */
    fun updateData(newData: List<BeerVo>) {
        beerList = newData
        notifyDataSetChanged()
    }



    inner class BeerViewHolder(val binding: BeerItemBinding) : RecyclerView.ViewHolder(binding.root)

}