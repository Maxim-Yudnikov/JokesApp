package com.maxim.jokesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.maxim.jokesapp.core.presentation.CommonCommunication
import com.maxim.jokesapp.core.presentation.CommonViewModel
import com.maxim.jokesapp.presentation.BaseCommunication
import com.maxim.jokesapp.presentation.BaseViewModel
import com.maxim.jokesapp.presentation.CommonDataRecyclerAdapter
import com.maxim.jokesapp.presentation.CommonUiModel
import com.maxim.jokesapp.presentation.FavoriteDataView

abstract class BaseFragment<T>: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.data_fragment, container, false)
    }

    protected abstract fun getViewModel(app: JokeApp): CommonViewModel<T>
    protected abstract fun getCommunication(app: JokeApp): CommonCommunication<T>
    protected abstract fun checkBoxText(): String
    protected abstract fun actionButtonText(): String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val application = requireActivity().application as JokeApp
        val viewModel = getViewModel(application)

        val communication = getCommunication(application)
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
        }, communication)

        recyclerView.adapter = adapter
        viewModel.observeList(this) {
            adapter.update()
        }
        viewModel.getItemList()
    }
}