package com.dim3nsions.movieapp.manager

import android.annotation.SuppressLint
import android.location.Geocoder
import android.location.Location
import com.dim3nsions.movieapp.MovieAppApplication
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

interface LocationManager {

    suspend fun findLastRegion(): String

    companion object {
        val instance: LocationManager by lazy {
            LocationManagerImp()
        }
    }
}

class LocationManagerImp(
    private val geocoder: Geocoder = Geocoder(MovieAppApplication.instance),
    private val locationService: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(
        MovieAppApplication.instance
    )
) : LocationManager {

    @SuppressLint("MissingPermission")
    override suspend fun findLastRegion(): String =
        suspendCancellableCoroutine { continuation ->
            locationService.lastLocation
                .addOnCompleteListener {
                    continuation.resume(it.result.toRegion())
                }
        }

    private fun Location?.toRegion(): String {
        val addresses = this?.let {
            geocoder.getFromLocation(latitude, longitude, 1)
        }
        return addresses?.firstOrNull()?.countryCode ?: "ES"
    }
}