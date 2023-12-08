package com.ensoft.recyclerviewswipedelete

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MainAdapter(private val dataset: MutableList<String>) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var removedPosition: Int = 0
    private var removedItem: String = ""

    class MainViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val title: TextView = v.findViewById(R.id.title)

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MainAdapter.MainViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row, viewGroup, false)
        return MainViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: MainViewHolder, position: Int) {
        viewHolder.title.text = dataset[position]
    }

    fun removeItem(position: Int, viewHolder: RecyclerView.ViewHolder) {
        removedItem = dataset[position]
        removedPosition = position

        dataset.removeAt(position)
        notifyItemRemoved(position)

        Snackbar.make(viewHolder.itemView, "$removedItem removed", Snackbar.LENGTH_LONG).setAction("UNDO") {
            dataset.add(removedPosition, removedItem)
            notifyItemInserted(removedPosition)

        }.show()
    }

    override fun getItemCount() = dataset.size
}