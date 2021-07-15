package com.jetbrains.handson.mpp.mobile

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ApplicationPresenter: ApplicationContract.Presenter() {
    private val dispatchers = AppDispatchersImpl()
    private var view: ApplicationContract.View? = null
    private val job: Job = SupervisorJob()

    override val stations = listOf(kings, birm, euston, durham, york).sortedBy{it.name}

    override val coroutineContext: CoroutineContext
        get() = dispatchers.main + job

    override fun onViewTaken(view: ApplicationContract.View) {
        this.view = view
        view.setLabel(createApplicationScreenMessage())
    }

    override fun  onButtonTapped(stationStart:Station, stationEnd:Station) {
        view!!.openLink("https://www.lner.co.uk/travel-information/travelling-now/live-train-times/depart/"+stationStart.code+"/"+stationEnd.code+"/#LiveDepResults")
    }


}
