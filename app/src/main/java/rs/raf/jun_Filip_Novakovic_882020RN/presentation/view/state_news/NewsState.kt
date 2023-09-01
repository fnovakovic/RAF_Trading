package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_news

import rs.raf.jun_Filip_Novakovic_882020RN.data.model_news.News

sealed class NewsState {
    object Loading: NewsState()
    object DataFetched: NewsState()
    data class Success(val new: List<News>): NewsState()
    data class Error(val message: String): NewsState()
}