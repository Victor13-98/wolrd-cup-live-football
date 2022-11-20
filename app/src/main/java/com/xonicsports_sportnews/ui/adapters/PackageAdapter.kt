package com.xonicsports_sportnews.ui.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.xonicsports_sportnews.R
import com.xonicsports_sportnews.dataClasses.Plan


class PackageAdapter(private val packages: List<Plan>, private val context: Context, private val listener: onClickItemListener) : RecyclerView.Adapter<PackageAdapter.ViewAdapterViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAdapterViewHolder {
        return ViewAdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.package_item_card, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewAdapterViewHolder, position: Int) {
        val item = packages[position]
        holder.planName.text = item.name
        holder.price.text = item.amount
        holder.time.text = item.accessDays
        holder.odds.text = item.odds

        if(item.name == "Free Tips"){
            holder.subscribeButton.text = "View"
            holder.price.visibility = View.GONE
        }

    }

    inner class ViewAdapterViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        val planName : TextView = itemView.findViewById(R.id.plan_name)
        val price : TextView = itemView.findViewById(R.id.plan_price)
        val time : TextView = itemView.findViewById(R.id.access_days)
        val odds : TextView = itemView.findViewById(R.id.odds)
        val mainCard : LinearLayoutCompat = itemView.findViewById(R.id.main_card)
        val subscribeButton : AppCompatButton = itemView.findViewById(R.id.subscribe_button)



        init {
            itemView.setOnClickListener(this)
            subscribeButton.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onClickItem(position)
                }
            }
        }

        override fun onClick(p0: View?) {

        }


    }

    override fun getItemCount(): Int {
        return packages.size
    }

    interface onClickItemListener {
        fun onClickItem(position : Int)
    }

}