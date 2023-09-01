package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_stocks

import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.Stocks

sealed class StockState {
    object Loading: StockState()
    object DataFetched: StockState()
    data class Success(val stock: List<Stocks>): StockState()
    data class Error(val message: String): StockState()
}