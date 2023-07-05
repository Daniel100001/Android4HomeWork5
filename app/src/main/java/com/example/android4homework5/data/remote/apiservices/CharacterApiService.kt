package com.example.android4homework5.data.remote.apiservices

import com.example.android4homework5.data.models.CharacterModel
import com.example.android4homework5.data.models.RickAndMortyResponse
import retrofit2.Response
import retrofit2.http.GET

interface CharacterApiService {

    @GET("api/character")
    suspend fun fetchCharacter(): Response<RickAndMortyResponse<CharacterModel>>
}