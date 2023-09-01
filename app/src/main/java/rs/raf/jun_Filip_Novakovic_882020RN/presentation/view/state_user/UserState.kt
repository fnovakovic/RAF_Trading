package rs.raf.jun_Filip_Novakovic_882020RN.presentation.view.state_user

import rs.raf.jun_Filip_Novakovic_882020RN.data.datasources.model_user.User

sealed class UserState {
    object Loading: UserState()
    data class DataFetched(val userr: List<User>):  UserState()
    data class Success(val user: List<User>): UserState()
    data class Error(val message: String): UserState()
}