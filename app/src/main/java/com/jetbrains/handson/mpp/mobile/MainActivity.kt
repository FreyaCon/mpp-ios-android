package com.jetbrains.handson.mpp.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.Spinner
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity(), ApplicationContract.View {

    var stations: List<Station> = listOf()
    var station1:Station = Station("","")
    var station2:Station = Station("","")
    val presenter: ApplicationPresenter = ApplicationPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.onViewTaken(this)
        setStations()

        //Retrieve array of station names for spinner
        fun names(stations: List<Station>): List<String> {
            var stationNames: MutableList<String> = mutableListOf()
            stations.forEach{
                stationNames.add(it.name)
            }
            return stationNames
        }
        //setting adapter for spinners
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, names(stations))
        stations_spinner.adapter= aa
        stations_spinner2.adapter= aa

        //spinner 1
        stations_spinner.onItemSelectedListener = object :
           AdapterView.OnItemSelectedListener {

            override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
                station1= stations[position]
            }

            override fun onNothingSelected(arg0: AdapterView<*>) {
            }
        }

        //spinner 2
        stations_spinner2.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {

                override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
                 station2= stations[position]
                }

                 override fun onNothingSelected(arg0: AdapterView<*>) {
                 }
        }

        //Set response to hitting the button
        val searchButton:Button = findViewById(R.id.my_button)
        searchButton.setOnClickListener {
            getDataNow(this)
        }

    }

    //functions promised in the contract
    override fun setLabel(text: String) {
        findViewById<TextView>(R.id.main_text).text = text
    }

    fun getDataNow(view: ApplicationContract.View) {
        val url = presenter.addSelectedStations(station1,station2)
        print(presenter.getData(view,url))
    }

    override fun setStations() {
        stations = presenter.stations
    }

    override fun openLink(linkString: String) {
        val openURL = Intent(android.content.Intent.ACTION_VIEW)
        openURL.data = Uri.parse(linkString)
        startActivity(openURL)
    }


}
