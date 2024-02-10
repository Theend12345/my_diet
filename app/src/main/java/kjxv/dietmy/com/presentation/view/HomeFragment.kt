package kjxv.dietmy.com.presentation.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kjxv.dietmy.com.databinding.FragmentHomeBinding
import kjxv.dietmy.com.presentation.view.state.ViewState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.search("тест")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.searchState.collectLatest {
                when (it) {
                    is ViewState.Error -> Log.d("tikerror", it.e.toString())
                    is ViewState.Loading -> Log.d("tikloading", "LOADING")
                    is ViewState.Success -> {
                        delay(1000)
                        Log.d("tiksuccess", it.data.toString())
                    }
                }
            }
        }
    }

}