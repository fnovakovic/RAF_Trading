package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.sigleStockState

import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.SingleStock

sealed class SingleStockState {
    object Loading: SingleStockState()
    object DataFetched: SingleStockState()
    data class Success(val new: SingleStock): SingleStockState()
    data class Error(val message: String): SingleStockState()
}