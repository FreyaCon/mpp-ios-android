package com.jetbrains.handson.mpp.mobile

import kotlinx.coroutines.CoroutineScope

interface ApplicationContract {
    interface View {
        fun setLabel(text: String)

    }

    abstract class Presenter: CoroutineScope {
        abstract fun onViewTaken(view: View)
        abstract fun onButtonTapped(stationStart:String, stationEnd:String):String
        abstract fun convertToCode(stationName:String): String
        abstract fun addSelectedStations(arrivalStation: String, departureStation: String): String
        abstract fun getData(view: ApplicationContract.View,url: String)
    }
}
