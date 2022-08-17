package com.example.basicfrontend

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.basicfrontend.Data.placeData
import android.widget.Button
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.core.app.ActivityCompat
import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import com.google.android.material.snackbar.Snackbar
import androidx.constraintlayout.widget.ConstraintLayout

class DetailsActivity : AppCompatActivity() {

    private lateinit var textPlaceName: TextView
    private lateinit var textPlaceAddress: TextView
    private lateinit var textPlaceSchedule: TextView
    private lateinit var btnstartVisit: Button
    private lateinit var rootLayout: ConstraintLayout
    private lateinit var btnCall: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        textPlaceName = findViewById(R.id.text_detailsActivity_placeName)
        textPlaceAddress = findViewById(R.id.text_detailsActivity_placeAddress)
        textPlaceSchedule = findViewById(R.id.text_detailsActivity_placeSchedule)
        btnstartVisit = findViewById(R.id.button_detailsActivity_startVisit)
        btnCall = findViewById(R.id.button_detailsActivity_call)
        rootLayout = findViewById(R.id.root_DetailsActivity)

        val place: placeData = intent.getSerializableExtra("EXTRA_PLACE") as placeData
        textPlaceName.text = place.name
        textPlaceAddress.text = place.address
        textPlaceSchedule.text = place.schedule

        initListeners()
    }

    private fun initListeners() {

        btnstartVisit.setOnClickListener {
            checkCameraPermission()
        }

        btnCall.setOnClickListener {
            val phoneNum = "+50223600294"
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phoneNum"))
            startActivity(intent)
        }
    }

    private fun checkCameraPermission(){
        if (!hasCameraPermission()) {
            checkRequestRationale()
        } else {
            Toast.makeText(this, "Permiso otorgado, abrir camara", Toast.LENGTH_LONG).show()
        }
    }

    private fun hasCameraPermission() =
        ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED

    private fun checkRequestRationale() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {

                val snackbar = Snackbar.make(
                    rootLayout,
                    "Acceso a cámara es necesario para poder iniciar la visita",
                    Snackbar.LENGTH_INDEFINITE
                )
                snackbar.setAction("Ok"){
                    requestCameraPermission()
                }
                snackbar.show()
            } else
                requestCameraPermission()
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.CAMERA),
            0
        )
    }
}