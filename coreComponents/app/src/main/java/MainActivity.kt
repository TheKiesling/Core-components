package com.example.basicfrontend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import android.content.Intent
import android.net.Uri
import android.widget.TextView
import com.example.basicfrontend.Data.placeData

class MainActivity : AppCompatActivity() {

    private lateinit var btnStart: Button
    private lateinit var btnDownload: Button
    private lateinit var btnDirections: Button
    private lateinit var btnDetails: Button
    private lateinit var txtPlaceName: TextView
    private lateinit var txtPlaceAddress: TextView
    private lateinit var txtPlaceSchedule: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart = findViewById(R.id.button_mainActivity_start)
        btnDownload = findViewById(R.id.button_mainActivity_download)
        btnDirections = findViewById(R.id.button_mainActivity_directions)
        btnDetails = findViewById(R.id.button_mainActivity_details)
        txtPlaceName = findViewById(R.id.text_mainActivity_placeName)
        txtPlaceAddress = findViewById(R.id.text_mainActivity_placeAddress)
        txtPlaceSchedule = findViewById(R.id.text_mainActivity_placeSchedule)

        initListeners()
    }

    private fun initListeners(){
        btnStart.setOnClickListener{
            Toast.makeText(this, getString(R.string.text_name), Toast.LENGTH_LONG).show()
        }

        btnDownload.setOnClickListener{
            val url = "https://play.google.com/store/search?q=discord&c=apps"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        btnDirections.setOnClickListener{
            val location = "http://maps.google.com/maps?q=loc:14.621224,-90.513971"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(location))
            startActivity(intent)
        }

        btnDetails.setOnClickListener{
            val placeName = txtPlaceName.text.toString()
            val placeAddress = txtPlaceAddress.text.toString()
            val placeSchedule = txtPlaceSchedule.text.toString()
            val place = placeData(placeName, placeAddress , placeSchedule)
            //val place = "$placeName $placeAddress $placeSchedule"
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("EXTRA_PLACE", place)
            startActivity(intent)
        }
    }
}