package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.Stocks
import rs.raf.jun_Filip_Novakovic_882020RN.databinding.StockItemBinding
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.recycler.diff.StockDiffCallback
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.recycler.viewholder.StockHolder

class StockAdapter : ListAdapter<Stocks, StockHolder>(StockDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockHolder {
        val itemBinding = StockItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StockHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: StockHolder, position: Int) {
        holder.bind(getItem(position))
    }



}