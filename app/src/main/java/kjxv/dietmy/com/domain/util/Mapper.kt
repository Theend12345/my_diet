package kjxv.dietmy.com.domain.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <T> Flow<List<T>>.flatMapIterable() = flow {
    collect {
        it.map { element ->
            emit(element)
        }
    }
}