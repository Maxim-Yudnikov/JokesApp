package com.maxim.jokesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.maxim.jokesapp.databinding.ActivityMainBinding
import io.realm.Realm

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = (application as JokeApp).viewModel

        binding.apply {
            progressBar.visibility = View.INVISIBLE
            getJokeButton.setOnClickListener {
                getJokeButton.isEnabled = false
                progressBar.visibility = View.VISIBLE
                viewModel.getJoke()
            }

            favoriteButton.setOnClickListener {
                viewModel.changeJokeStatus()
            }

            checkbox.setOnCheckedChangeListener {_, isChecked ->
                viewModel.chooseFavorites(isChecked)
            }

            viewModel.init(object : DataCallback {
                override fun provideText(text: String) {
                    getJokeButton.isEnabled = true
                    progressBar.visibility = View.INVISIBLE
                    jokeText.text = text
                }

                override fun provideIconRes(id: Int) {
                    favoriteButton.setImageResource(id)
                }
            })
        }
    }

    override fun onDestroy() {
        viewModel.clear()
        Realm.getDefaultInstance().close()
        super.onDestroy()
    }
}