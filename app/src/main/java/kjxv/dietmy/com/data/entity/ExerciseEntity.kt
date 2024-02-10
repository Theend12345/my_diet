package kjxv.dietmy.com.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise")
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val title: String,
    val content: String,
    val img: String,
    val type: String,
    var video: String? = null,
    val favorite: Boolean,
)
