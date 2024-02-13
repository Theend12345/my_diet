package kjxv.dietmy.com.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kjxv.dietmy.com.domain.model.Searchable
import kjxv.dietmy.com.domain.usecase.SearchUseCase
import kjxv.dietmy.com.presentation.view.state.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {
    private var _searchState = MutableStateFlow<ViewState<List<Searchable>>>(ViewState.Loading)
    val searchState: StateFlow<ViewState<List<Searchable>>>
        get() = _searchState.asStateFlow()

    fun search(searchQuery: String) {
        val data = mutableListOf<Searchable>()
        viewModelScope.launch {
            searchUseCase(searchQuery).collectLatest {
                try {
                    if (it != null) {
                        data.add(it)
                    }
                } catch (e: Throwable) {
                    _searchState.value = ViewState.Error(e)
                }
                _searchState.value = ViewState.Success(data)
            }
        }
    }
}