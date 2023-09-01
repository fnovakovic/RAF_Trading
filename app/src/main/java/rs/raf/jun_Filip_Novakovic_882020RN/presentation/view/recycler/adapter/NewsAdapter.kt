package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_news.News
import rs.raf.jun_Filip_Novakovic_882020RN.databinding.NewsItemBinding
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.recycler.diff.NewsDiffCallBack
import rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.recycler.viewholder.NewsHolder

class NewsAdapter : ListAdapter<News, NewsHolder>(NewsDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val itemBinding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        holder.bind(getItem(position))
    }

}