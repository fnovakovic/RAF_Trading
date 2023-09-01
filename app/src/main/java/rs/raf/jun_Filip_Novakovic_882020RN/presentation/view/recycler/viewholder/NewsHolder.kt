package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import rs.raf.jun_Filip_Novakovic_882020RN.data.model_news.News
import rs.raf.jun_Filip_Novakovic_882020RN.databinding.NewsItemBinding


class NewsHolder(private val itemBinding: NewsItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(stock: News) {
  /*      itemBinding.title.text = stock.title
        itemBinding.content.text = stock.content*/
        itemBinding.link.text = stock.title
        itemBinding.date.text = stock.date

        Glide
            .with(itemView)
            .load(stock.image)
            .centerCrop()
            .into(itemBinding.newsPicture)

    }

}