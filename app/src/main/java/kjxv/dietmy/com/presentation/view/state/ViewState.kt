package kjxv.dietmy.com.presentation.view.state

sealed class ViewState<out T: Any> {
    data object Loading: ViewState<Nothing>()
    data class Success<out T: Any>(val data: T): ViewState<T>()
    data class Error(val e: Throwable): ViewState<Nothing>()
}