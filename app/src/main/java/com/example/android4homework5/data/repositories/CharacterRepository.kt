package com.example.android4homework5.data.repositories

import com.example.android4homework5.base.BaseRepository
import com.example.android4homework5.data.remote.apiservices.CharacterApiService
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val service: CharacterApiService) :
    BaseRepository() {

    fun fetchCharacter() = doRequest {
        service.fetchCharacter()
    }
}