package com.jetbrains.handson.mpp.mobile

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ApplicationPresenter: ApplicationContract.Presenter() {
    override fun convertToCode(stationName:String):String{
        when(stationName){
            "Durham"-> return "DHM"
            "York" -> return "YRK"
            "Kings Cross" -> return "KGX"
            "Euston" -> return "EUS"
            "Birmingham New Street"-> return "BHM"
        }
        return "KGX"
    }


    private val dispatchers = AppDispatchersImpl()
    private var view: ApplicationContract.View? = null
    private val job: Job = SupervisorJob()
    val stations = arrayOf("Kings Cross", "Euston", "Durham", "York", "Birmingham New Street")

    override val coroutineContext: CoroutineContext
        get() = dispatchers.main + job

    override fun onViewTaken(view: ApplicationContract.View) {
        this.view = view
        view.setLabel(createApplicationScreenMessage())
    }
    override fun  onButtonTapped(stationStart:String, stationEnd:String):String{

        return "https://www.lner.co.uk/travel-information/travelling-now/live-train-times/depart/"+stationStart+"/"+stationEnd+"/#LiveDepResults"

    }


}
