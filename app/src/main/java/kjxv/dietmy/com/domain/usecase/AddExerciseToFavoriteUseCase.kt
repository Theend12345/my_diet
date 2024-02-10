package kjxv.dietmy.com.domain.usecase

import kjxv.dietmy.com.domain.model.ExerciseModel
import kjxv.dietmy.com.domain.repository.MyDietRepository
import javax.inject.Inject

class AddExerciseToFavoriteUseCase @Inject constructor(private val repository: MyDietRepository) {
    operator fun invoke(exerciseModel: ExerciseModel){
        repository.updateExercise(exerciseModel)
    }
}