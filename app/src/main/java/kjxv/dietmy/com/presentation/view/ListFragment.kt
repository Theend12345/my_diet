package kjxv.dietmy.com.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kjxv.dietmy.com.R
import kjxv.dietmy.com.databinding.FragmentListBinding
import kjxv.dietmy.com.domain.util.DataType
import kjxv.dietmy.com.presentation.util.ArticleType
import kjxv.dietmy.com.presentation.util.DietType
import kjxv.dietmy.com.presentation.util.FitnessType
import kjxv.dietmy.com.presentation.view.rv.ListAdapter
import kjxv.dietmy.com.presentation.view.state.ViewState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var dataType: DataType
    private lateinit var actionType: String

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

        binding.filter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                itemSelected: View, selectedItemPosition: Int, selectedId: Long
            ) {
                when (selectedItemPosition) {
                    0 -> viewModel.loadALLDiet()
                    1 -> viewModel.loadData(dataType, DietType.VEGAN.name.lowercase())
                    2 -> viewModel.loadData(dataType, DietType.NOGLUTEN.name.lowercase())
                    3 -> viewModel.loadData(dataType, DietType.CARBOHYDRATE.name.lowercase())
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                viewModel.loadALLDiet()
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        if (actionType != FitnessType.FAVOURITE.name.lowercase()) {
            viewModel.loadData(dataType, actionType)
        } else if (dataType == DataType.DIET) {
            viewModel.loadALLDiet()
        } else {
            viewModel.loadFavoriteExercise()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            if (dataType == DataType.DIET) {
                binding.filter.visibility = View.VISIBLE
            }
            recycler.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            viewLifecycleOwner.lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    setToolDarTitle()
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

    private fun setToolDarTitle() {
        when (dataType) {
            DataType.DIET -> (activity as MainActivity).setToolBarTitle(getString(R.string.diet))
            DataType.ARTICLE -> {
                when (actionType) {
                    ArticleType.SELF.name.lowercase() -> (activity as MainActivity).setToolBarTitle(
                        getString(R.string.personal_care)
                    )

                    ArticleType.BEAUTY.name.lowercase() -> (activity as MainActivity).setToolBarTitle(
                        getString(R.string.beauty)
                    )
                }
            }

            DataType.EXERCISE -> {
                when (actionType) {
                    FitnessType.FAVOURITE.name.lowercase() -> (activity as MainActivity).setToolBarTitle(
                        getString(R.string.favourite)
                    )

                    FitnessType.EXERCISE.name.lowercase() -> (activity as MainActivity).setToolBarTitle(
                        getString(R.string.exercise)
                    )

                    FitnessType.CARDIO.name.lowercase() -> (activity as MainActivity).setToolBarTitle(
                        getString(R.string.cardio)
                    )

                    FitnessType.YOGA.name.lowercase() -> (activity as MainActivity).setToolBarTitle(
                        getString(R.string.Yga)
                    )
                }
            }
        }
    }
}