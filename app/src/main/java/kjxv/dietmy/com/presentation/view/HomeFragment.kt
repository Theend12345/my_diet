package kjxv.dietmy.com.presentation.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kjxv.dietmy.com.R
import kjxv.dietmy.com.databinding.FragmentHomeBinding
import kjxv.dietmy.com.domain.util.DataType
import kjxv.dietmy.com.presentation.util.ArticleType
import kjxv.dietmy.com.presentation.util.FitnessType

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        with(binding) {
            search.setOnEditorActionListener { _, id, _ ->
                if (id == EditorInfo.IME_ACTION_DONE) {
                    val bundle = Bundle()
                    bundle.putString(SEARCH_FIELD, search.text.toString())
                    findNavController().navigate(R.id.action_homeFragment_to_searchFragment, bundle)
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
                        val bundle = Bundle()
                        bundle.putString(SEARCH_FIELD, search.text.toString())
                        findNavController().navigate(
                            R.id.action_homeFragment_to_searchFragment,
                            bundle
                        )
                        return@setOnTouchListener true
                    }
                }
                false
            }

            fitness.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_fitnessFragment)
            }
            diet.setOnClickListener {
                categoryNavigateBundle(FitnessType.FAVOURITE.name.lowercase(), DataType.DIET)
            }
            beauty.setOnClickListener {
                categoryNavigateBundle(ArticleType.BEAUTY.name.lowercase(), DataType.ARTICLE)
            }

            personalCare.setOnClickListener {
                categoryNavigateBundle(ArticleType.SELF.name.lowercase(), DataType.ARTICLE)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    private fun categoryNavigateBundle(string: String, dataType: DataType) {
        val bundle = Bundle()
        bundle.putString(ACTION_TYPE, string)
        bundle.putSerializable(DATA_TYPE, dataType)
        findNavController().navigate(R.id.action_homeFragment_to_listFragment, bundle)
    }

}