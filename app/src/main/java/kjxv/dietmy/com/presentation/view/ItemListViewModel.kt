package kjxv.dietmy.com.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kjxv.dietmy.com.domain.model.ExerciseModel
import kjxv.dietmy.com.domain.model.Searchable
import kjxv.dietmy.com.domain.usecase.AddExerciseToFavoriteUseCase
import kjxv.dietmy.com.presentation.view.state.ViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemListViewModel @Inject constructor(
    private val addExerciseToFavoriteUseCase: AddExerciseToFavoriteUseCase
) : ViewModel() {
    private var _searchState = MutableStateFlow<ViewState<Searchable>>(ViewState.Loading)
    val searchState: StateFlow<ViewState<Searchable>>
        get() = _searchState.asStateFlow()

    fun loadData(searchable: Searchable){
        _searchState.value = ViewState.Success(searchable)
    }

    fun addToFavorite(exerciseModel: ExerciseModel){
        viewModelScope.launch(Dispatchers.IO){
            addExerciseToFavoriteUseCase(exerciseModel)
        }
    }
}