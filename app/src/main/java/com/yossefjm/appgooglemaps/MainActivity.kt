package com.yossefjm.appgooglemaps

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarker()
    }

    private fun createMarker() {
        val miInstitute = LatLng(42.172691,2.476645)
        map.addMarker(
            MarkerOptions()
            .position(miInstitute)
            .title("Mi instituto")
        )

        val favoriteVacation = LatLng(25.111241, 55.141075)
        map.addMarker(
            MarkerOptions()
            .position(favoriteVacation)
            .title("Vacaciones")
            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        )


        val vacations = LatLng(33.563289, -7.643524)
        val icon = BitmapDescriptorFactory.fromResource(R.mipmap.ic_house_maps_foreground)

        if (icon == null) {
            println("Error al crear el icono")
            return
        } else {
            // Agregar un marcador con el icono personalizado
            map.addMarker(
                MarkerOptions()
                    .position(vacations)
                    .title("Vacaciones")
                    .icon(icon)
            )
        }
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(vacations, 18f),
            4000,
            null
        )
        map.uiSettings.isZoomControlsEnabled = true
        map.uiSettings.isCompassEnabled = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createMapFragment()
    }

    private fun createMapFragment() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

}