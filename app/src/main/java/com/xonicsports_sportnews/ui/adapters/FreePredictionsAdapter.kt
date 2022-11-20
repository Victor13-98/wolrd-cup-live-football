package com.xonicsports_sportnews.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xonicsports_sportnews.R
import com.xonicsports_sportnews.dataClasses.FreePrediction

class FreePredictionsAdapter(
    private val list: List<FreePrediction>,
    private val context: Context,
) : RecyclerView.Adapter<FreePredictionsAdapter.FreePredictionViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FreePredictionViewHolder {
        return FreePredictionViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.free_layout_item, parent, false)
        )
    }



    override fun onBindViewHolder(holder: FreePredictionViewHolder, position: Int) {
        holder.date.text = list[position].date
        holder.fixture.text = list[position].fixture
        holder.prediction.text = list[position].prediction
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class FreePredictionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date : TextView = itemView.findViewById(R.id.date)
        val fixture : TextView = itemView.findViewById(R.id.fixture)
        val prediction : TextView = itemView.findViewById(R.id.prediction)
    }

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }
}