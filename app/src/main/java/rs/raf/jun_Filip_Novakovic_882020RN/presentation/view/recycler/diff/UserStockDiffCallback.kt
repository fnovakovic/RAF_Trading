package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.UserStocks

class UserStockDiffCallback : DiffUtil.ItemCallback<UserStocks>() {

    override fun areItemsTheSame(oldItem: UserStocks, newItem: UserStocks): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: UserStocks, newItem: UserStocks): Boolean {
        return oldItem.name == newItem.name

    }

}