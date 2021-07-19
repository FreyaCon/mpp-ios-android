package com.jetbrains.handson.mpp.mobile

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import com.soywiz.klock.*

expect fun platformName(): String

fun createApplicationScreenMessage(): String {
    return "Kotlin Rocks on ${platformName()}"
}

@UnstableDefault
val client = HttpClient(){
    install(JsonFeature) {
        serializer = KotlinxSerializer(
            Json{ignoreUnknownKeys=true}

        )
    }
}

@UnstableDefault
suspend fun getData(station1:Station, station2:Station): TrainData {
    val time: DateTimeTz = DateTimeTz.nowLocal()+TimeSpan(60000.0)
    return client.get("https://mobile-api-softwire2.lner.co.uk/v1/fares") {
        parameter("originStation", station1.code)
        parameter("destinationStation", station2.code)
        parameter("numberOfAdults", 1)
        parameter("numberOfChildren", 0)
        parameter("journeyType","single")
        parameter("outboundDateTime", time.format("YYYY-MM-ddTHH:mm:ssXXX"))
    }
}

fun timeConverter(time: String): SimpleDateTime{
    var dt: SimpleDateTime = SimpleDateTime(0,0,0,0,0,0)
    dt.day = time.subSequence(8,10).toString().toInt()
    dt.month = time.subSequence(5,7).toString().toInt()
    dt.year = time.subSequence(0,4).toString().toInt()
    dt.hour = time.subSequence(11,13).toString().toInt()
    dt.minute = time.subSequence(14,16).toString().toInt()
    dt.gmt = time.subSequence(24,26).toString().toInt()
    return dt
}

fun journeyTimeConverter(time: Int): Triple<Int,Int,Int>  {
    //minutes
    val c = time%60
    //hours
    var t = time - time%60
    t /= 60
    val b = t%24
    t -= t%24
    //days
    val a = t/24
    return Triple(a,b,c)
}



