package com.jetbrains.handson.mpp.mobile

import kotlinx.coroutines.CoroutineScope

interface ApplicationContract {
    interface View {
        fun setLabel(text: String)
        fun openLink(linkString: String)
        fun setStations()
        //fun setText (to update text output from JSON)
    }

    abstract class Presenter: CoroutineScope {
        abstract fun onViewTaken(view: View)
        abstract fun onTicketButtonTapped(stationStart:Station, stationEnd: Station)
        abstract fun onButtonTapped(stationStart:Station, stationEnd:Station)
        abstract val stations: List<Station>
        abstract fun checkForDiffStations(station1: Station, station2: Station): Boolean
    }
}
