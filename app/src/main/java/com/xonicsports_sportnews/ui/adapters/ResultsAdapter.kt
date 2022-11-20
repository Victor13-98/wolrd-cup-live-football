package com.xonicsports_sportnews.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xonicsports_sportnews.R
import com.xonicsports_sportnews.dataClasses.PredictionResult

class ResultsAdapter(private val list: List<PredictionResult>, private val context: Context) :
    RecyclerView.Adapter<ResultsAdapter.ViewResultsViewHolder>() {
    class ViewResultsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date : TextView = itemView.findViewById(R.id.date)
        val gameItemRecyclerView : RecyclerView = itemView.findViewById(R.id.gameItemRecyclerView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewResultsViewHolder {
        return ViewResultsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.results_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: ViewResultsViewHolder, position: Int) {
        holder.date.text = list[position].date
        holder.gameItemRecyclerView.also {
            it.layoutManager = LinearLayoutManager(context)
            it.setHasFixedSize(true)
            it.adapter = SingleResultsAdapter(list[position].gamesList)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }
}