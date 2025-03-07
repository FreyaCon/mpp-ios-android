package com.jetbrains.handson.mpp.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import android.net.Uri
import android.widget.Button
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ApplicationContract.View {

    var stations: List<Station> = listOf()
    var station1:Station = Station("","")
    var station2:Station = Station("","")
    private val presenter: ApplicationPresenter = ApplicationPresenter()
    lateinit var recyclerView: RecyclerView

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
            if (presenter.checkForSameStations(station1, station2)) {
                toast("Start and End stations should be different")
            } else {
                presenter.onButtonTapped(station1, station2)
            }
        }

        val ticketButton:Button = findViewById(R.id.ticket_button)
        ticketButton.setOnClickListener {
            if (presenter.checkForSameStations(station1, station2)) {
                toast("Start and End stations should be different")
            } else {
                presenter.onTicketButtonTapped(station1, station2)
            }
        }

    }

    //displays pop up message with string text
    override fun toast(text:String) {
        Toast.makeText(this@MainActivity, text, Toast.LENGTH_LONG).show()
    }

    override fun setStations() {
        stations = presenter.stations
    }

    override fun openLink(linkString: String) {
        val openURL = Intent(android.content.Intent.ACTION_VIEW)
        openURL.data = Uri.parse(linkString)
        startActivity(openURL)
    }

    override fun displayJourneys(trains: TrainData) {
        val adapter = TrainAdapter(trains)

        recyclerView = findViewById<RecyclerView>(R.id.rvTrains)
        recyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        // add divider line
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, layoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    override fun trainVisibility(bool: Boolean) {
        if (this::recyclerView.isInitialized) {
            if (bool) {
                recyclerView.setVisibility(View.VISIBLE)
            } else {
                recyclerView.setVisibility(View.INVISIBLE)
            }
        }
    }
}
