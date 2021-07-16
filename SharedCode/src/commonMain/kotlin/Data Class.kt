package com.jetbrains.handson.mpp.mobile
import com.soywiz.klock.Date
import kotlinx.serialization.*
@Serializable
data class JsonStation(val displayName:String, val crs:String)
@Serializable
data class TrainData(val outboundJourneys:List<Train>)
@Serializable
class Train(val originStation: JsonStation
                     , val destinationStation: JsonStation
                     , val departureTime: String
                     , val arrivalTime: String
                     , val journeyDurationInMinutes: Int
                     , val status: String)
