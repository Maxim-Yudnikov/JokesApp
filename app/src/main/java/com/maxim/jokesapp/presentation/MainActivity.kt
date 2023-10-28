package com.maxim.jokesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.maxim.jokesapp.CommonDataRecyclerAdapter
import com.maxim.jokesapp.JokeApp
import com.maxim.jokesapp.R
import com.maxim.jokesapp.data.CommonDataModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = (application as JokeApp).viewModel
        val favoriteDataView = findViewById<FavoriteDataView>(R.id.jokeFavoriteDataView)
        favoriteDataView.linkWith(viewModel)
        viewModel.observe(this) { state ->
            favoriteDataView.show(state)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CommonDataRecyclerAdapter<Int>()
        recyclerView.adapter = adapter
        viewModel.observeList(this,) {
            adapter.show(it)
        }

        viewModel.getItemList()
    }
}


//        val quoteViewModel = (application as JokeApp).quoteViewModel
//        val quoteFavoriteDataView = findViewById<FavoriteDataView>(R.id.quoteDataView)
//        quoteFavoriteDataView.linkWith(quoteViewModel)
//        quoteViewModel.observe(this) { state ->
//            quoteFavoriteDataView.show(state)
//        }