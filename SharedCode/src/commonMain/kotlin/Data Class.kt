package com.jetbrains.handson.mpp.mobile
import com.soywiz.klock.Date
import kotlinx.serialization.*

@Serializable
data class TrainData(val station1: String
                     , val station2: String
                     , val departureTime: String
                     , val arrivalTime: String
                     , val status: String)
