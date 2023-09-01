package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_news

sealed class AddNewsState {
    object Success: AddNewsState()
    data class Error(val message: String): AddNewsState()
}