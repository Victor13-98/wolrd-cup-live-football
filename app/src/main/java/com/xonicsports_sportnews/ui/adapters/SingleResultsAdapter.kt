package com.xonicsports_sportnews.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xonicsports_sportnews.R
import com.xonicsports_sportnews.dataClasses.Games

class SingleResultsAdapter(private val singleResultsList : List<Games>) : RecyclerView.Adapter<SingleResultsAdapter.SingleResultsViewHolder>() {
    class SingleResultsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fixture : TextView = itemView.findViewById(R.id.fixture)
        val prediction : TextView = itemView.findViewById(R.id.prediction)
        val result : TextView = itemView.findViewById(R.id.results)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleResultsViewHolder {
       return SingleResultsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.single_result_item_layout, parent, false))
    }

    override fun onBindViewHolder(holder: SingleResultsViewHolder, position: Int) {
        holder.fixture.text = singleResultsList[position].fixture
        holder.prediction.text = singleResultsList[position].prediction
        holder.result.text = singleResultsList[position].results

        if ("WIN" in singleResultsList[position].results ){
            holder.result.setTextColor(Color.parseColor("#FF03C04A"))

        }
        if ("LOSS" in singleResultsList[position].results ){
            holder.result.setTextColor(Color.parseColor("#FFFF001E"))
        }
    }

    override fun getItemCount(): Int {
        return singleResultsList.size
    }
}