package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.Stocks

class StockDiffCallback : DiffUtil.ItemCallback<Stocks>() {

    override fun areItemsTheSame(oldItem: Stocks, newItem: Stocks): Boolean {
        return oldItem.instrumentId == newItem.instrumentId
    }

    override fun areContentsTheSame(oldItem: Stocks, newItem: Stocks): Boolean {
        return oldItem.name == newItem.name

    }

}