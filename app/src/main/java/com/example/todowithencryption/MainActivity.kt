package com.example.todowithencryption

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.todowithencryption.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding?= null
    val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        setUpActionBarGreetings()
    }

    private fun setUpActionBarGreetings() {
        val c = Calendar.getInstance()
        when (c[Calendar.HOUR_OF_DAY]) {
            in 0..11 -> {
                binding.actionBarGreeting.text = "GoodMorning"
                Toast.makeText(this, "Good Morning", Toast.LENGTH_SHORT).show()
            }
            in 12..15 -> {
                binding.actionBarGreeting.text = "Good AfterNoon"
                Toast.makeText(this, "Good Afternoon", Toast.LENGTH_SHORT).show()
            }
            in 16..20 -> {
                binding.actionBarGreeting.text = "Good Evening"
                Toast.makeText(this, "Good Evening", Toast.LENGTH_SHORT).show()
            }
            in 21..23 -> {
                binding.actionBarGreeting.text = "Good Night"
                Toast.makeText(this, "Good Night", Toast.LENGTH_SHORT).show()
            }
        }
    }
}