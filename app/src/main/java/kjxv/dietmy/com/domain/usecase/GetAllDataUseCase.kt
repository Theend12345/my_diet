package kjxv.dietmy.com.domain.usecase

import kjxv.dietmy.com.domain.repository.MyDietRepository
import kjxv.dietmy.com.domain.util.DataType
import javax.inject.Inject

class GetAllDataUseCase @Inject constructor(private val repository: MyDietRepository) {
    operator fun invoke(dataType: DataType) =
        when(dataType){
            DataType.DIET -> repository.getAllDiet()
            DataType.ARTICLE -> repository.getAllArticle()
            DataType.EXERCISE -> repository.getAllExercise()
        }
}