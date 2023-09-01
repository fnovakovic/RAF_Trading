package rs.raf.jun_Filip_Novakovic_882020RN.data.model_news

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class NewsEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val content: String,
    val link: String,
    val date: String,
    val image: String
)