package kjxv.dietmy.com.presentation.view.rv

import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kjxv.dietmy.com.databinding.ListItemBinding
import kjxv.dietmy.com.domain.model.ArticleModel
import kjxv.dietmy.com.domain.model.DietModel
import kjxv.dietmy.com.domain.model.ExerciseModel
import kjxv.dietmy.com.domain.model.Searchable
import kjxv.dietmy.com.presentation.view.HomeFragmentDirections
import kjxv.dietmy.com.presentation.view.ListFragmentDirections
import kjxv.dietmy.com.presentation.view.SearchFragmentDirections


class ItemListViewHolder(private val binding: ListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Searchable) {
        with(binding) {
            title.text = item.searchField()
            Glide.with(binding.root.context).load(item.getImage()).into(img)
            itemView.setOnClickListener {
                try {
                    val action = ListFragmentDirections.actionListFragmentToItemListFragment(item)
                    val navController = Navigation.findNavController(itemView)
                    navController.navigate(action)
                }catch (e: Throwable){
                    val action = SearchFragmentDirections.actionSearchFragmentToItemListFragment(item)
                    val navController = Navigation.findNavController(itemView)
                    navController.navigate(action)
                }
            }
        }
    }
}