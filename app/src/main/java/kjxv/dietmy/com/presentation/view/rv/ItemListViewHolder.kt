package kjxv.dietmy.com.presentation.view.rv

import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kjxv.dietmy.com.R
import kjxv.dietmy.com.databinding.ListItemBinding
import kjxv.dietmy.com.domain.model.ArticleModel
import kjxv.dietmy.com.domain.model.DietModel
import kjxv.dietmy.com.domain.model.ExerciseModel
import kjxv.dietmy.com.domain.model.Searchable
import kjxv.dietmy.com.presentation.view.ListFragmentDirections


class ItemListViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Searchable) {
        when (item) {
            is ExerciseModel -> bindTypes(item)
            is DietModel -> bindTypes(item)
            is ArticleModel -> bindTypes(item)
        }
    }

    private fun bindTypes(item: ExerciseModel) {
        with(binding) {
            title.text = item.title
            Glide.with(binding.root.context).load(item.img).into(img)
            itemView.setOnClickListener {
                //viewModel.loadExercise(exerciseModel)
                val action = ListFragmentDirections.actionListFragmentToItemListFragment(item)
                val navController = Navigation.findNavController(itemView)
                navController.navigate(action)
            }
        }
    }

    private fun bindTypes(item: DietModel) {
        with(binding) {
            title.text = item.title
            Glide.with(binding.root.context).load(item.img).into(img)

            itemView.setOnClickListener {
                //viewModel.loadExercise(exerciseModel)
                val navController = Navigation.findNavController(itemView)
                navController.navigate(R.id.action_listFragment_to_itemListFragment)
            }
        }
    }

    private fun bindTypes(item: ArticleModel) {
        with(binding) {
            title.text = item.title
            Glide.with(binding.root.context).load(item.img).into(img)

            itemView.setOnClickListener {
                //viewModel.loadExercise(exerciseModel)
                val navController = Navigation.findNavController(itemView)
                navController.navigate(R.id.action_listFragment_to_itemListFragment)
            }
        }
    }
}