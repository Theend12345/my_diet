package kjxv.dietmy.com.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "diet")
data class DietEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String,
    val content: String,
    val img: String,
    val type: String
)
