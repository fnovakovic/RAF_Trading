package rs.raf.jun_Filip_Novakovic_882020RN.data.datasources.local_user

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.jun_Filip_Novakovic_882020RN.data.datasources.model_user.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 13,
    exportSchema = false)
@TypeConverters()
abstract class UserDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
}