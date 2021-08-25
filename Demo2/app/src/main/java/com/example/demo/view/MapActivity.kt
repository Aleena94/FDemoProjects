package com.example.demo.view

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.demo.R
import com.example.demo.constants.Constants
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*

class MapActivity : FragmentActivity(), OnMapReadyCallback {

    private var mMap: GoogleMap? = null
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    private var mLocationPermissionGranted = false
    private var mLastKnownLocation: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.fragMap) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        updateLocationUI()
    }

    private fun updateLocationUI() {
        if (mMap == null) {
            return
        }
        try {
            if (mLocationPermissionGranted) {
                mMap!!.isMyLocationEnabled = true
                mMap!!.uiSettings.isMyLocationButtonEnabled = true

            } else {
                mMap!!.isMyLocationEnabled = false
                mMap!!.uiSettings.isMyLocationButtonEnabled = false
                mLastKnownLocation = null

            }
            locationPermission
        } catch (e: SecurityException) {
            Log.e("Exception: %s", e.message!!)
        }
    }

    private val locationPermission: Unit
        get() {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    Constants.REQUEST_MAP
                )
                return
            }
            mFusedLocationClient!!.lastLocation
                .addOnCompleteListener(
                    this
                ) { task ->
                    if (task.isSuccessful) {
                        mLastKnownLocation = task.result
                        val mLatLng =
                            LatLng(mLastKnownLocation!!.latitude, mLastKnownLocation!!.longitude)
                        mMap!!.moveCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                mLatLng,
                                Constants.ZOOM
                            )
                        )
                        mMap!!.animateCamera(
                            CameraUpdateFactory.zoomTo(Constants.ZOOM),
                            2000,
                            null
                        )
                        mMap!!.addMarker(
                            MarkerOptions().position(mLatLng).title(
                                getAddress(
                                    mLastKnownLocation!!.latitude,
                                    mLastKnownLocation!!.longitude
                                )
                            )
                        )

                    } else {
                        Toast.makeText(this@MapActivity, "No Location", Toast.LENGTH_SHORT).show()
                    }
                }
        }


    private fun getAddress(lat: Double, lon: Double): String? {
        val geocoder = Geocoder(this, Locale.getDefault())
        val addresses: List<Address> = geocoder.getFromLocation(lat, lon, 10)
        if (addresses.isNotEmpty()) {
            for (adr in addresses) {
                if (adr.locality != null && adr.locality.isNotEmpty()) {
                    return adr.locality + "," + adr.countryName
                }
            }
        }
        return null
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        mLocationPermissionGranted = false
        when (requestCode) {

            Constants.REQUEST_MAP -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true
            }
        }
        updateLocationUI()
    }
}