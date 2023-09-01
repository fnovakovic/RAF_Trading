package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user

sealed class AddUserState {
    object Success: AddUserState()
    data class Error(val message: String): AddUserState()
}