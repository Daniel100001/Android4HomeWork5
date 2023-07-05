package com.example.android4homework5.data.repositories

import com.example.android4homework5.base.BaseRepository
import com.example.android4homework5.data.remote.apiservices.LocationApiService
import javax.inject.Inject

class LocationRepository @Inject constructor(private val service: LocationApiService) :
    BaseRepository() {

    fun fetchLocation() = doRequest {
        service.fetchLocation()
    }
}