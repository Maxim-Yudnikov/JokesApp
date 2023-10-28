package com.maxim.jokesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maxim.jokesapp.data.CommonDataModel
import com.maxim.jokesapp.presentation.CommonUiModel
import com.maxim.jokesapp.presentation.CorrectImageButton
import com.maxim.jokesapp.presentation.CorrectTextView
import com.maxim.jokesapp.presentation.FailedCommonUiModel

class CommonDataRecyclerAdapter<T>(private val listener: FavoriteItemClickListener<T>) :
    RecyclerView.Adapter<CommonDataRecyclerAdapter.CommonDataViewHolder<T>>() {

    private val list = ArrayList<CommonUiModel<T>>()

    fun show(data: List<CommonUiModel<T>>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonDataViewHolder<T> {
        val emptyList = viewType == 0
        val view = LayoutInflater.from(parent.context).inflate(
            if (emptyList)
                R.layout.common_data_error
            else
                R.layout.common_data_item,
            parent, false
        )
        return if(emptyList) EmptyFavoritesViewHolder(view) else CommonDataViewHolder.Base(view, listener)
    }

    override fun getItemViewType(position: Int) = when (list[position]) {
        is FailedCommonUiModel -> 0
        else -> 1
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CommonDataViewHolder<T>, position: Int) {
        holder.bind(list[position])
    }

    abstract class CommonDataViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
        private val textView = itemView.findViewById<CorrectTextView>(R.id.commonDataTextView)
        open fun bind(model: CommonUiModel<T>) = model.show(textView)

        class Base<T>(view: View, private val listener: FavoriteItemClickListener<T>) : CommonDataViewHolder<T>(view) {
            private val iconView = itemView.findViewById<CorrectImageButton>(R.id.favorite_button)
            override fun bind(model: CommonUiModel<T>) {
                super.bind(model)
                iconView.setOnClickListener {
                    model.change(listener)
                }
            }
        }
    }

    inner class EmptyFavoritesViewHolder<T>(view: View) : CommonDataViewHolder<T>(view)

    interface FavoriteItemClickListener<T> {
        fun change(id: T)
    }
}