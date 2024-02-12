package kjxv.dietmy.com.data.repository

import kjxv.dietmy.com.data.db.MyDietDao
import kjxv.dietmy.com.data.db.MyDietDatabase
import kjxv.dietmy.com.data.util.toEntity
import kjxv.dietmy.com.data.util.toModel
import kjxv.dietmy.com.domain.model.ArticleModel
import kjxv.dietmy.com.domain.model.DietModel
import kjxv.dietmy.com.domain.model.ExerciseModel
import kjxv.dietmy.com.domain.repository.MyDietRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MyDietRepositoryImp(database: MyDietDatabase) : MyDietRepository {

    private val dao: MyDietDao = database.myDietDao()

    override fun getAllDiet(): Flow<List<DietModel>> =
        dao.getAllDiet().map { list ->
            list.map { entity ->
                entity.toModel()
            }
        }

    override fun getAllArticle(): Flow<List<ArticleModel>> =
        dao.getAllArticle().map { list ->
            list.map { entity ->
                entity.toModel()
            }
        }

    override fun getAllExercise(): Flow<List<ExerciseModel>> =
        dao.getAllExercise().map { list ->
            list.map { entity ->
                entity.toModel()
            }
        }

    override fun getDietByType(type: String): Flow<List<DietModel>> =
        dao.getDietByType(type).map { list ->
            list.map { entity ->
                entity.toModel()
            }
        }

    override fun getArticleByType(type: String): Flow<List<ArticleModel>> =
        dao.getArticleByType(type).map { list ->
            list.map { entity ->
                entity.toModel()
            }
        }

    override fun getExerciseByType(type: String): Flow<List<ExerciseModel>> =
        dao.getExerciseByType(type).map { list ->
            list.map { entity ->
                entity.toModel()
            }
        }

    override fun getExerciseByFavorite(favorite: Boolean): Flow<List<ExerciseModel>> =
        dao.getExerciseByFavorite(favorite).map { list ->
            list.map { entity ->
                entity.toModel()
            }
        }

    override fun updateExercise(exerciseModel: ExerciseModel) {
        dao.updateExercise(exerciseModel.toEntity())
    }
}