package com.maxim.jokesapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.maxim.jokesapp.JokeApp
import com.maxim.jokesapp.R
import com.maxim.jokesapp.core.presentation.CommonCommunication
import com.maxim.jokesapp.core.presentation.CommonViewModel

abstract class BaseFragment<V: BaseViewModel<T>, T>: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.data_fragment, container, false)
    }

    protected abstract fun checkBoxText(): String
    protected abstract fun actionButtonText(): String
    protected abstract fun getViewModelClass(): Class<V>

    private lateinit var viewModel: BaseViewModel<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, (requireActivity().application as JokeApp).viewModelFactory).get(getViewModelClass())
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favoriteDataView = view.findViewById<FavoriteDataView>(R.id.favoriteDataView)
        favoriteDataView.linkWith(viewModel)
        viewModel.observe(this) { state ->
            favoriteDataView.show(state)
        }
        favoriteDataView.checkBoxText(checkBoxText())
        favoriteDataView.actionButtonText(actionButtonText())

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = CommonDataRecyclerAdapter(object :
            CommonDataRecyclerAdapter.FavoriteItemClickListener<T> {
            override fun change(id: T) {
                Snackbar.make(
                    favoriteDataView,
                    "Remove from favorites?",
                    Snackbar.LENGTH_SHORT
                ).setAction("yes") {
                    viewModel.changeItemStatus(id)
                }.show()
            }
        }, viewModel.communication)

        recyclerView.adapter = adapter
        viewModel.observeList(this) {
            adapter.update()
        }
        viewModel.getItemList()
    }
}