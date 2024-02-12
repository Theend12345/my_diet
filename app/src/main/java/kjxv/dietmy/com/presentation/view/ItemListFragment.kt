package kjxv.dietmy.com.presentation.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kjxv.dietmy.com.R
import kjxv.dietmy.com.databinding.FragmentItemListBinding
import kjxv.dietmy.com.domain.model.ArticleModel
import kjxv.dietmy.com.domain.model.DietModel
import kjxv.dietmy.com.domain.model.ExerciseModel
import kjxv.dietmy.com.domain.model.Searchable
import kjxv.dietmy.com.presentation.view.state.ViewState
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class ItemListFragment : Fragment() {

    private val binding: FragmentItemListBinding by lazy {
        FragmentItemListBinding.inflate(layoutInflater)
    }

    private val args: ItemListFragmentArgs by navArgs()

    private val viewModel: ItemListViewModel by lazy {
        ViewModelProvider(this)[ItemListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.loadData(args.data)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.searchState.collectLatest {
                when (it) {
                    is ViewState.Error -> Unit
                    ViewState.Loading -> Unit
                    is ViewState.Success -> bind(it.data)
                }
            }
        }
    }

    private fun bind(searchable: Searchable) {
        with(binding) {
            when (searchable) {
                is ExerciseModel -> {
                    exerciseBind(searchable)
                }
                is DietModel -> {
                    favourite.visibility = View.GONE
                }
                is ArticleModel -> {
                    favourite.visibility = View.GONE
                }
                else -> {}
            }
        }
    }

    private fun exerciseBind(exerciseModel: ExerciseModel){
        with(binding){
            favoriteCheck(exerciseModel)
            title.text = exerciseModel.title
            Glide.with(requireContext()).load(exerciseModel.img).into(img)
            content.text = exerciseModel.content
            if (exerciseModel.video != null) {
                ytbBtn.visibility = View.VISIBLE
                ytbBtn.setOnClickListener {
                    openYouTubeVideo(exerciseModel.video.toString())
                }
            }
            favourite.setOnClickListener{
                exerciseModel.favorite = !exerciseModel.favorite
                favoriteCheck(exerciseModel)
                viewModel.addToFavorite(exerciseModel)
            }
        }
    }

    private fun openYouTubeVideo(url: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }

    private fun favoriteCheck(exerciseModel: ExerciseModel) {
        binding.favourite.setImageResource(R.drawable.ic_favorite_false)
        if (exerciseModel.favorite) {
            binding.favourite.setImageResource(R.drawable.ic_favorite_true)
        }
    }

}