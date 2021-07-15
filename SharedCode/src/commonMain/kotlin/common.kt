package com.jetbrains.handson.mpp.mobile

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

expect fun platformName(): String

val client = HttpClient(){
    install(JsonFeature) {
        serializer = KotlinxSerializer()
    }
}

fun getUrl(station1:Station, station2:Station):String{

    return "https://mobile-api-softwire2.lner.co.uk/v1/fares?originStation=${station1.code}" +
            "&destinationStation=${station2.code}&noChanges=false&numberOfAdults=2" +
            "&numberOfChildren=0&journeyType=single" +
            "&outboundDateTime=2021-07-24T14%3A30%3A00.000%2B01%3A00&outboundIsArriveBy=false"
}
suspend fun makeGetRequestForData(view: ApplicationContract.View, url: String) {
    val response: JsonObject = client.get(url)
    println(response)
}

fun createApplicationScreenMessage(): String {
    return "Kotlin Rocks on ${platformName()}"
}

