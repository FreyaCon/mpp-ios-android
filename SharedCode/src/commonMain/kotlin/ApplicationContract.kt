package com.jetbrains.handson.mpp.mobile

import kotlinx.coroutines.CoroutineScope

interface ApplicationContract {
    interface View {
        fun setLabel(text: String)
        fun openLink(linkString: String)
        fun setStations()

    }

    abstract class Presenter: CoroutineScope {
        abstract fun onViewTaken(view: View)
        abstract fun onButtonTapped(stationStart:Station, stationEnd:Station)
        abstract val stations: List<Station>
    }
}
