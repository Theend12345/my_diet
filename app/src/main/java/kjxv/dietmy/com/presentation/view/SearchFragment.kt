package kjxv.dietmy.com.presentation.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kjxv.dietmy.com.databinding.SearchDialogBinding
import kjxv.dietmy.com.presentation.view.rv.ListAdapter
import kjxv.dietmy.com.presentation.view.state.ViewState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

const val SEARCH_FIELD = "search"
@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var searchField: String

    private val binding: SearchDialogBinding by lazy {
        SearchDialogBinding.inflate(layoutInflater)
    }

    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this)[SearchViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        searchField = arguments?.getString(SEARCH_FIELD) ?: ""
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.search(searchField)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchState.collectLatest {
                    when (it) {
                        is ViewState.Error -> Toast.makeText(
                            context,
                            it.e.message,
                            Toast.LENGTH_SHORT
                        ).show()

                        ViewState.Loading -> binding.progress.visibility = View.VISIBLE
                        is ViewState.Success -> {
                            binding.progress.visibility = View.GONE
                            binding.recycler.adapter = ListAdapter(it.data)
                        }
                    }
                }
            }
        }
    }
}