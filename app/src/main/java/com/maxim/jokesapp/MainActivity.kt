package com.maxim.jokesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.maxim.jokesapp.customView.FavoriteDataView
import com.maxim.jokesapp.databinding.ActivityMainBinding
import io.realm.Realm

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = (application as JokeApp).viewModel

        val favoriteDataView = findViewById<FavoriteDataView>(R.id.jokeFavoriteDataView)
        favoriteDataView.listenChanges { isChecked ->
            viewModel.chooseFavorites(isChecked)
        }
        favoriteDataView.handleChangeButton {
            viewModel.changeJokeStatus()
        }
        favoriteDataView.handleActionButton {
            viewModel.getJoke()
        }

        viewModel.observe(this) { state ->
            favoriteDataView.show(state)
        }
    }
}