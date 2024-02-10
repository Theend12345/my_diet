package kjxv.dietmy.com.domain.usecase

import kjxv.dietmy.com.domain.repository.MyDietRepository
import kjxv.dietmy.com.domain.util.DataType
import javax.inject.Inject

class GetAllDataByTypeUseCase @Inject constructor(private val repository: MyDietRepository) {
    operator fun invoke(dataType: DataType, type: String) =
        when(dataType){
            DataType.DIET -> repository.getDietByType(type)
            DataType.ARTICLE -> repository.getArticleByType(type)
            DataType.EXERCISE -> repository.getExerciseByType(type)
        }
}