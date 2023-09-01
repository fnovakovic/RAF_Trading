package rs.raf.jun_Filip_Novakovic_882020RN.data.datasources.model_user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val username: String,
    val password: String,
    val email: String,
    val logged: String,
    val money: String,
    val firstTime: String,
    val portfolioValue: String
)