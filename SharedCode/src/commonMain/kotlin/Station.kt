package com.jetbrains.handson.mpp.mobile

class Station(stationName: String, stationCode: String){
    val name = stationName
    val code = stationCode
}

val kings = Station("London King's Cross", "KGX")
val euston = Station("London Euston", "EUS")
val durham = Station("Durham", "DHM")
val birm = Station("Birmingham New Street", "BHM")
val york = Station("York", "YRK")
