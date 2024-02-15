package kjxv.dietmy.com.domain.repository

import kjxv.dietmy.com.domain.model.*
import kotlinx.coroutines.flow.Flow

interface MyDietRepository {
    fun getAllDiet(): Flow<List<DietModel>>
    fun getAllArticle(): Flow<List<ArticleModel>>
    fun getAllExercise(): Flow<List<ExerciseModel>>
    fun getDietByType(type: String): Flow<List<DietModel>>
    fun getArticleByType(type: String): Flow<List<ArticleModel>>
    fun getExerciseByType(type: String): Flow<List<ExerciseModel>>
    fun getExerciseByFavorite(favorite: Boolean): Flow<List<ExerciseModel>>

    fun updateExercise(exerciseModel: ExerciseModel)
}