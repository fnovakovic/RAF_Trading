package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_stocks

sealed class AddStocksState {
    object Success: AddStocksState()
    data class Error(val message: String): AddStocksState()
}