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
        val originStation: TextView = itemView.findViewById<TextView>(R.id.departureStation)
        val destinationStation: TextView = itemView.findViewById<TextView>(R.id.arrivalStation)
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
        val journey = dataset.outboundJourneys[position]

        TrainHolder.destinationStation.text = journey.endStation.name
        TrainHolder.originStation.text = journey.startStation.name
        TrainHolder.departureTime.text = timeToString(journey.startTime)
        TrainHolder.arrivalTime.text = timeToString(journey.endTime)

    }

    private fun timeToString(time:SimpleDateTime):String {
        if(time.minute<10) {
            return time.hour.toString()+":0"+time.minute.toString()
        }
        return time.hour.toString()+":"+time.minute.toString()
    }


}
