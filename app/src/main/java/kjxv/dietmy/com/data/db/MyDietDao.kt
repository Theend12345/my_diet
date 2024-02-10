package kjxv.dietmy.com.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kjxv.dietmy.com.data.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDietDao {
    @Query("SELECT * FROM diet")
    fun getAllDiet(): Flow<List<DietEntity>>
    @Query("SELECT * FROM article")
    fun getAllArticle(): Flow<List<ArticleEntity>>
    @Query("SELECT * FROM exercise")
    fun getAllExercise(): Flow<List<ExerciseEntity>>

    @Query("SELECT * FROM diet WHERE type=:type")
    fun getDietByType(type: String): Flow<List<DietEntity>>
    @Query("SELECT * FROM article WHERE type=:type")
    fun getArticleByType(type: String): Flow<List<ArticleEntity>>
    @Query("SELECT * FROM exercise WHERE type=:type")
    fun getExerciseByType(type: String): Flow<List<ExerciseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateExercise(exerciseEntity: ExerciseEntity)
}