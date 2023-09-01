package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.UserStocks
import rs.raf.jun_Filip_Novakovic_882020RN.databinding.PortfolioItemBinding
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.recycler.diff.UserStockDiffCallback
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.recycler.viewholder.UserStockHolder

class UserStockAdapter : ListAdapter<UserStocks, UserStockHolder>(UserStockDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserStockHolder {
        val itemBinding = PortfolioItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserStockHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UserStockHolder, position: Int) {
        holder.bind(getItem(position))
    }

}