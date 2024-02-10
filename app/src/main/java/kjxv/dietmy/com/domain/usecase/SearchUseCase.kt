package kjxv.dietmy.com.domain.usecase

import kjxv.dietmy.com.domain.model.Searchable
import kjxv.dietmy.com.domain.util.DataType
import kjxv.dietmy.com.domain.util.flatMapIterable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SearchUseCase @Inject constructor(private val getAllDataUseCase: GetAllDataUseCase) {
    suspend operator fun invoke(searchQuery: String): Flow<Searchable?> {
        return withContext(Dispatchers.IO) {
            val tempData =
                merge(
                    getAllDataUseCase(DataType.DIET).flatMapIterable(),
                    getAllDataUseCase(DataType.ARTICLE).flatMapIterable(),
                    getAllDataUseCase(DataType.EXERCISE).flatMapIterable()
                )
            tempData.map {
                it.takeIf { item ->
                    item.searchField().contains(searchQuery)
                }
            }
        }
    }
}

