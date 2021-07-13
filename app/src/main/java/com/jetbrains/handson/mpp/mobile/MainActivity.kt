package com.jetbrains.handson.mpp.mobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.content.Intent
import android.net.Uri
import android.widget.Button

class MainActivity : AppCompatActivity(), ApplicationContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val presenter = ApplicationPresenter()
        presenter.onViewTaken(this)
        val searchButton:Button = findViewById(R.id.my_button)
        searchButton.setOnClickListener {
            val URL = presenter.onButtonTapped("DHM", "KGX")
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse(URL)
            startActivity(openURL)
        }
    }

    override fun setLabel(text: String) {
        findViewById<TextView>(R.id.main_text).text = text
    }


}
