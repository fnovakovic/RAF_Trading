package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.recycler.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_news.News

class NewsDiffCallBack : DiffUtil.ItemCallback<News>() {

    override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
        return oldItem.title == newItem.title

    }

}