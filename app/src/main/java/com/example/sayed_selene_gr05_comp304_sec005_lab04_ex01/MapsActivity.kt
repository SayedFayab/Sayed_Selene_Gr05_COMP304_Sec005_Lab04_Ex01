package com.example.sayed_selene_gr05_comp304_sec005_lab04_ex01


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.sayed_selene_gr05_comp304_sec005_lab04_ex01.databinding.ActivityMapsBinding
import com.google.android.gms.maps.SupportMapFragment


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding
    private var landmark : String? = null
    private var mapType : Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        landmark = intent.getStringExtra("selectedLandmark")
        mapType = intent.getIntExtra("mapType", 1)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        if (landmark != null) {
            Log.d("SELECTEDLANDMARK", this.landmark!!)
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        if (mapType == 2){
            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
        } else {
            mMap.mapType = GoogleMap.MAP_TYPE_TERRAIN
        }

        showLocation()
    }


    private fun showLocation() {
        var latLongInfo: DoubleArray = DoubleArray(2)

        when (landmark) {
            "CN Tower" -> {
                latLongInfo = doubleArrayOf(43.64263604726139, -79.38704607480089)
            }

            "Ripley's Aquarium of Canada" -> {
                latLongInfo = doubleArrayOf(43.642357044499306, -79.38630179623084)
            }

            "The Ed Mirvish Theatre" -> {
                latLongInfo = doubleArrayOf(43.65523, -79.3795)
            }

            "Canada's Wonderland" -> {
                latLongInfo = doubleArrayOf(43.84314758006409, -79.53947070362476)
            }

            "Art Gallery of Ontario (AGO)" -> {
                latLongInfo = doubleArrayOf(43.65375406023005, -79.39248011712834)
            }

            "Royal Ontario Museum (ROM)" -> {
                latLongInfo = doubleArrayOf(43.6679114508344, -79.39480929014314)
            }

            "The Aga Khan Museum" -> {
                latLongInfo = doubleArrayOf(43.725383877533496, -79.33213727294441)
            }
            "Museum of Contemporary Art" -> {
                latLongInfo = doubleArrayOf(41.383190, 2.166867)
            }


            "High Park" -> {
                latLongInfo = doubleArrayOf(43.64671090454008, -79.4637010324725)
            }

            "Toronto Islands" -> {
                latLongInfo = doubleArrayOf(43.620560, -79.376511)
            }

            "Centennial Park" -> {
                latLongInfo = doubleArrayOf(43.65373, -79.58476)
            }

            "Eaton Centre" -> {
                latLongInfo = doubleArrayOf(43.6548, -79.3807)
            }

            "Sherway Gardens" -> {
                latLongInfo = doubleArrayOf(43.611294, -79.558067)
            }

            "Scarborough Town Center" -> {
                latLongInfo = doubleArrayOf(43.776035, -79.257713)
            }
            "Fairview Mall(Don Mills)" -> {
                latLongInfo = doubleArrayOf(43.777882, -79.345879)
            }

            "Casa Loma" -> {
                latLongInfo = doubleArrayOf(43.678055, -79.409538)
            }

            "Rogers Centre" -> {
                latLongInfo = doubleArrayOf(43.641796, -79.390083)
            }

            "Toronto Zoo" -> {
                latLongInfo = doubleArrayOf(43.8179, -79.1854)
            }
        }

        mMap.addMarker(
            MarkerOptions().position(LatLng(latLongInfo[0], latLongInfo[1])).title(landmark)
        )
        mMap.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(latLongInfo[0], latLongInfo[1]),
                17f
            )
        )
        supportActionBar?.title = landmark
    }
}
