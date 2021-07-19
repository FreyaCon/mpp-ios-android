package com.jetbrains.handson.mpp.mobile

import kotlinx.serialization.*
@Serializable
data class JsonStation(val displayName:String, val crs:String)
@Serializable
data class TrainData(val outboundJourneys:List<Train>)
@Serializable
class Train(private val originStation: JsonStation,
            private val destinationStation: JsonStation,
            private val departureTime: String,
            private val arrivalTime: String,
            private val journeyDurationInMinutes: Int,
            val status: String)
{
    val startStation: Station
        get() = Station(originStation.displayName,originStation.crs)
    val endStation: Station
        get() = Station(destinationStation.displayName,destinationStation.crs)
    val startTime: SimpleDateTime
        get() = timeConverter(departureTime)
    val endTime: SimpleDateTime
        get() = timeConverter(arrivalTime)
    val journeyTime
        get() = journeyTimeConverter(journeyDurationInMinutes)
}

class SimpleDateTime(
    var day: Int,
    var month: Int,
    var year: Int,
    var hour: Int,
    var minute: Int,
    var gmt: Int)


