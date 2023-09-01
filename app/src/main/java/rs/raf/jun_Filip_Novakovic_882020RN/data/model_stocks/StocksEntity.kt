package rs.raf.jun_Filip_Novakovic_882020RN.data.model_stocks

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stocks")
data class StocksEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val userId: String,
    val quantity: String,
    val symbol: String,
    val price: String
)