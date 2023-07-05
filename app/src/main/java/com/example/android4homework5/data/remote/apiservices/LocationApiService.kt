package com.example.android4homework5.data.remote.apiservices

import com.example.android4homework5.data.models.LocationModel
import com.example.android4homework5.data.models.RickAndMortyResponse
import retrofit2.Response
import retrofit2.http.GET

interface LocationApiService {

    @GET("api/location")
    suspend fun fetchLocation(): Response<RickAndMortyResponse<LocationModel>>
}