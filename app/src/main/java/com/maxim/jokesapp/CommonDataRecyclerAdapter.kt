package com.maxim.jokesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maxim.jokesapp.data.CommonDataModel
import com.maxim.jokesapp.presentation.CommonUiModel
import com.maxim.jokesapp.presentation.CorrectTextView

class CommonDataRecyclerAdapter<E> :
    RecyclerView.Adapter<CommonDataRecyclerAdapter<E>.CommonDataViewHolder>() {

    private val list = ArrayList<CommonUiModel>()

    fun show(data: List<CommonUiModel>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommonDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.common_data_item, parent, false)
        return CommonDataViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CommonDataViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class CommonDataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textView = itemView.findViewById<CorrectTextView>(R.id.commonDataTextView)
        fun bind(model: CommonUiModel) = model.show(textView)
    }
}