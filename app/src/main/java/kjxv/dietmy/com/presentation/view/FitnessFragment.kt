package kjxv.dietmy.com.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kjxv.dietmy.com.R
import kjxv.dietmy.com.databinding.FragmentFitnessBinding
import kjxv.dietmy.com.domain.util.DataType
import kjxv.dietmy.com.presentation.util.FitnessType

const val DATA_TYPE = "data-type"
const val ACTION_TYPE = "action-type"

class FitnessFragment : Fragment() {

    private val binding: FragmentFitnessBinding by lazy {
        FragmentFitnessBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            exercise.setOnClickListener {
                navigate(FitnessType.EXERCISE.name.lowercase())
            }
            yoga.setOnClickListener {
                navigate(FitnessType.YOGA.name.lowercase())
            }
            cardio.setOnClickListener {
                navigate(FitnessType.CARDIO.name.lowercase())
            }
            favourite.setOnClickListener {
                navigate(FitnessType.FAVOURITE.name.lowercase())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    private fun navigate(type: String) {
        val bundle = Bundle()
        bundle.putString(ACTION_TYPE, type)
        bundle.putSerializable(DATA_TYPE, DataType.EXERCISE)
        findNavController().navigate(R.id.action_fitnessFragment_to_listFragment, bundle)
    }
}