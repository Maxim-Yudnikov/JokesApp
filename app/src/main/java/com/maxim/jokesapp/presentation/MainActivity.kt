package com.maxim.jokesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.maxim.jokesapp.JokeApp
import com.maxim.jokesapp.R

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: CommonDataRecyclerAdapter<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = (application as JokeApp).viewModel
        val jokeCommunication = (application as JokeApp).jokeCommunication
        val favoriteDataView = findViewById<FavoriteDataView>(R.id.jokeFavoriteDataView)
        favoriteDataView.linkWith(viewModel)
        viewModel.observe(this) { state ->
            favoriteDataView.show(state)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val observer: (t: List<CommonUiModel<Int>>) -> Unit = { _ ->
            adapter.update()
        }
        adapter = CommonDataRecyclerAdapter(object :
            CommonDataRecyclerAdapter.FavoriteItemClickListener<Int> {
            override fun change(id: Int) {
                Snackbar.make(
                    favoriteDataView,
                    "Remove from favorites?",
                    Snackbar.LENGTH_SHORT
                ).setAction("yes") {
                    viewModel.changeItemStatus(id)
                }.show()
            }
        }, jokeCommunication)
        recyclerView.adapter = adapter
        viewModel.observeList(this, observer)
        viewModel.getItemList()
    }
}


//        val quoteViewModel = (application as JokeApp).quoteViewModel
//        val quoteFavoriteDataView = findViewById<FavoriteDataView>(R.id.quoteDataView)
//        quoteFavoriteDataView.linkWith(quoteViewModel)
//        quoteViewModel.observe(this) { state ->
//            quoteFavoriteDataView.show(state)
//        }