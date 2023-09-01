package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.sigleStockState

sealed class AddSingleStockState {
    object Success: AddSingleStockState()
    data class Error(val message: String): AddSingleStockState()
}