package kjxv.dietmy.com.presentation.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import kjxv.dietmy.com.databinding.FragmentGreetingBinding

@AndroidEntryPoint
class GreetingActivity : AppCompatActivity() {

    private val binding: FragmentGreetingBinding by lazy {
        FragmentGreetingBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.start.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            finish()
        }
    }

}