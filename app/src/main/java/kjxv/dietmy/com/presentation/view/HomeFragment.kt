package kjxv.dietmy.com.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kjxv.dietmy.com.R
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

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        with(binding) {
            search.setOnEditorActionListener { _, id, keyEvent ->
                if (id == EditorInfo.IME_ACTION_DONE) {
                    viewModel.search("тест")
                }
                false
            }

            search.setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_UP) {
                    val drawableLeft = 0
                    val padding = 100
                    val bounds = search.compoundDrawables[drawableLeft].bounds
                    val x = event.rawX.toInt()
                    if (x >= bounds.left && x <= bounds.right + padding) {
                        viewModel.search("тест")
                        return@setOnTouchListener true
                    }
                }
                false
            }

            fitness.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_fitnessFragment)
            }
        }
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