package com.jetbrains.handson.mpp.mobile

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.UnstableDefault

class ApplicationPresenter: ApplicationContract.Presenter() {

    private val dispatchers = AppDispatchersImpl()
    private var view: ApplicationContract.View? = null
    private val job: Job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = dispatchers.main + job
    private val coroutineScope = CoroutineScope(coroutineContext)

    //promised items from contract
    override val stations = listOf(kings, birm, euston, durham, york).sortedBy{it.name}

    override fun onViewTaken(view: ApplicationContract.View) {
        this.view = view
    }

    @UnstableDefault
    override fun  onButtonTapped(stationStart:Station, stationEnd:Station){
        coroutineScope.launch {
            val trains = getData(stationStart, stationEnd)
            if (trains.outboundJourneys.isEmpty()){
                view?.toast("There are no trains available for this journey.")
            } else {
                view?.displayJourneys(trains)
            }
        }
    }

    override fun onTicketButtonTapped(stationStart:Station, stationEnd:Station){
        view?.openLink("https://www.lner.co.uk/travel-information/travelling-now/live-train-times/depart/"+stationStart.code+"/"+stationEnd.code+"/#LiveDepResults")
    }

    override fun checkForSameStations(station1:Station, station2:Station):Boolean{
        if(station1==station2) {
            return true
        }
        return false
    }

}
