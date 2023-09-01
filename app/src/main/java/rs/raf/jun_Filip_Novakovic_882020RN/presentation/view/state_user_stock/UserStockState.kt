package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user_stock

import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.UserStocks

sealed class UserStockState {
    data class Loading(val stocks: List<UserStocks>): UserStockState()
    object DataFetched: UserStockState()
    data class Success(val stock: List<UserStocks>): UserStockState()
    data class Error(val message: String): UserStockState()
}