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





    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val presenter = ApplicationPresenter()
        var station1:String = ""
        var station2:String =""
        presenter.onViewTaken(this)



        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, presenter.stations)

        stations_spinner.adapter= aa
        stations_spinner2.adapter= aa
        stations_spinner.onItemSelectedListener = object :
           AdapterView.OnItemSelectedListener {

            override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
                Toast.makeText(this@MainActivity,

                                "" + presenter.stations[position], Toast.LENGTH_SHORT).show()
                station1= presenter.convertToCode(presenter.stations[position])
            }

            override fun onNothingSelected(arg0: AdapterView<*>) {
            }
        }
        stations_spinner2.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {

            override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
                Toast.makeText(this@MainActivity,

                        "" + presenter.stations[position], Toast.LENGTH_SHORT).show()
                station2= presenter.convertToCode(presenter.stations[position])

            }

            override fun onNothingSelected(arg0: AdapterView<*>) {
            }
        }
        // Set Adapter to Spinner
        val searchButton:Button = findViewById(R.id.my_button)
        searchButton.setOnClickListener {
            val URL = presenter.onButtonTapped(station1, station2)
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse(URL)
            startActivity(openURL)
        }

    }

    override fun setLabel(text: String) {
        findViewById<TextView>(R.id.main_text).text = text
    }



}
