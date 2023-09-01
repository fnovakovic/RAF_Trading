package rs.raf.jun_Filip_Novakovic_882020RN.data.datasources.local_stocks

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks.StocksEntity


@Database(
    entities = [StocksEntity::class],
    version = 7,
    exportSchema = false)
@TypeConverters()
abstract class StocksDataBase : RoomDatabase() {
    abstract fun getStockstDao(): StocksDao
}