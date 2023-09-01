package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.UserStocks
import rs.raf.jun_Filip_Novakovic_882020RN.databinding.PortfolioItemBinding


class UserStockHolder(private val itemBinding: PortfolioItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(stock: UserStocks) {
        itemBinding.name.text = stock.name
        itemBinding.symbol.text = stock.symbol
        itemBinding.qty.setText(stock.quantity)
        itemBinding.price.setText(stock.price)
        //itemBinding.userId.text = stock.userId



       /* var entries : ArrayList<Entry>  =  ArrayList<Entry>();
       // for (d in stock.chart.bars  ) {
            // turn your data into Entry objects
            entries.add( Entry(0F, 0F));
            entries.add( Entry(0F, 1F));
            entries.add( Entry(0F, 2F));
            entries.add( Entry(0F, 3F));
       // }

        var dataset = LineDataSet(entries, "Label"); // add entries to dataset

        val lineData = LineData(dataset)
        itemBinding.chart.setData(lineData)
        itemBinding.chart.invalidate() // refresh*/



    }

}