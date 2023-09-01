package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user_stock

sealed class AddUserStocksState {
    object Success: AddUserStocksState()
    data class Error(val message: String): AddUserStocksState()
}