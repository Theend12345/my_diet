package kjxv.dietmy.com.presentation.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kjxv.dietmy.com.R
import kjxv.dietmy.com.databinding.FragmentListBinding
import kjxv.dietmy.com.domain.util.DataType
import kjxv.dietmy.com.presentation.util.FitnessType
import kjxv.dietmy.com.presentation.view.rv.ListAdapter
import kjxv.dietmy.com.presentation.view.state.ViewState
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class ListFragment : Fragment() {

    lateinit var dataType: DataType
    lateinit var actionType: String

    private val binding: FragmentListBinding by lazy {
        FragmentListBinding.inflate(layoutInflater)
    }

    private val viewModel: ListViewModel by lazy {
        ViewModelProvider(this)[ListViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataType = arguments?.getSerializable(DATA_TYPE) as DataType
        actionType = arguments?.getString(ACTION_TYPE) ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (actionType != FitnessType.FAVOURITE.name.lowercase()) {
            viewModel.loadData(dataType, actionType)
        } else {
            viewModel.loadFavoriteExercise()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            recycler.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            lifecycleScope.launchWhenStarted {
                viewModel.dataState.collectLatest {
                    when (it) {
                        is ViewState.Error -> Toast.makeText(
                            requireContext(),
                            it.e.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        ViewState.Loading -> progress.visibility = View.VISIBLE
                        is ViewState.Success -> {
                            progress.visibility = View.GONE
                            recycler.adapter = ListAdapter(it.data)
                        }
                    }
                }
            }
        }
    }
}