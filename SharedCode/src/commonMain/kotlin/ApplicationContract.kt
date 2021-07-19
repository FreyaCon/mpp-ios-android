package com.jetbrains.handson.mpp.mobile

import kotlinx.coroutines.CoroutineScope

interface ApplicationContract {
    interface View {
        fun setLabel(text: String)
        fun openLink(linkString: String)
        fun setStations()
        fun displayJourneys(trains: TrainData)
        fun toast(text:String)

    }

    abstract class Presenter: CoroutineScope {
        abstract fun onViewTaken(view: View)
        abstract fun onTicketButtonTapped(stationStart:Station, stationEnd: Station)
        abstract fun onButtonTapped(stationStart:Station, stationEnd:Station)
        abstract val stations: List<Station>
        abstract fun checkForSameStations(station1: Station, station2: Station): Boolean
    }
}
