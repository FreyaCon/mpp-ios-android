package com.jetbrains.handson.mpp.mobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.traindata.view.*

class TrainAdapter(private val dataset: TrainData) : RecyclerView.Adapter<TrainAdapter.TrainHolder>() {

    class TrainHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        val originstation: TextView = itemView.findViewById<TextView>(R.id.departureStation)
        val desitinationStation: TextView = itemView.findViewById<TextView>(R.id.arrivalStation)
        val departureTime: TextView = itemView.findViewById<TextView>(R.id.departureTime)
        val arrivalTime: TextView = itemView.findViewById<TextView>(R.id.arrivalTime)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.traindata, parent, false)

        return TrainHolder(itemView)

    }

    override fun getItemCount(): Int {
       return dataset.outboundJourneys.size
    }

    override fun onBindViewHolder(TrainHolder: TrainHolder, position: Int) {
        val trainPost = dataset.outboundJourneys[position]
        TrainHolder.desitinationStation.text = trainPost.destinationStation.toString()
        TrainHolder.originstation.text = trainPost.originStation.toString()
        TrainHolder.departureTime.text = trainPost.departureTime
        TrainHolder.arrivalTime.text = trainPost.arrivalTime

    }




}
