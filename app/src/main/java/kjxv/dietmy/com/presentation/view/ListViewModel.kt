package kjxv.dietmy.com.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kjxv.dietmy.com.domain.model.Searchable
import kjxv.dietmy.com.domain.usecase.GetAllDataByTypeUseCase
import kjxv.dietmy.com.domain.usecase.GetAllDataUseCase
import kjxv.dietmy.com.domain.usecase.GetFavoriteExerciseUseCase
import kjxv.dietmy.com.domain.util.DataType
import kjxv.dietmy.com.presentation.view.state.ViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getAllDataByTypeUseCase: GetAllDataByTypeUseCase,
    private val getFavoriteExerciseUseCase: GetFavoriteExerciseUseCase
) : ViewModel() {
    private var _dataState = MutableStateFlow<ViewState<List<Searchable>>>(ViewState.Loading)
    val dataState: StateFlow<ViewState<List<Searchable>>>
        get() = _dataState.asStateFlow()

    fun loadData(dataType: DataType, type: String) {
        viewModelScope.launch {
            getAllDataByTypeUseCase(dataType, type).collect {
                setState(it)
            }
        }
    }

    fun loadFavoriteExercise(){
        viewModelScope.launch {
            getFavoriteExerciseUseCase().collect{
                setState(it)
            }
        }
    }

    private fun setState(data: List<Searchable>){
        try {
            _dataState.value = ViewState.Success(data)
        } catch (e: Throwable) {
            _dataState.value = ViewState.Error(e)
        }
    }
}