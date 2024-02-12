package kjxv.dietmy.com.domain.usecase

import kjxv.dietmy.com.domain.repository.MyDietRepository
import javax.inject.Inject

class GetFavoriteExerciseUseCase @Inject constructor(private val repository: MyDietRepository) {
    operator fun invoke() = repository.getExerciseByFavorite(true)
}