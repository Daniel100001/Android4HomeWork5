package com.example.android4homework5.ui.framgments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android4homework5.data.models.PairModel
import com.example.android4homework5.data.repositories.CharacterRepository
import com.example.android4homework5.data.repositories.LocationRepository
import com.example.android4homework5.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RickAndMortyViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val locationRepository: LocationRepository
) : ViewModel() {

    private val _noteLiveData = MutableLiveData<Resource<List<PairModel>>>(Resource.Loading())
    val noteLiveData: LiveData<Resource<List<PairModel>>> = _noteLiveData

    init {
        getData()
    }

    private fun getData() {

        val character = viewModelScope.async {
            characterRepository.fetchCharacter()
        }

        val location = viewModelScope.async {
            locationRepository.fetchLocation()
        }

        viewModelScope.launch {
            character.await().combine(location.await()) { character, location ->
                Pair(character, location)
            }.collect {
                when {
                    it.first is Resource.Error && it.second is Resource.Error -> {
                        _noteLiveData.value = Resource.Error(
                            message = it.first.message + it.second.message,
                            data = null
                        )
                    }
                    it.first is Resource.Success && it.second is Resource.Success -> {
                        val modelsList = mutableListOf<PairModel>()
                        it.first.data!!.results.zip(it.second.data!!.results).forEach { models ->
                            modelsList.add(
                                PairModel(
                                    models.first,
                                    models.second,
                                    models.first.id

                                )
                            )
                        }
                        _noteLiveData.value = Resource.Success(modelsList)
                    }
                }
            }
        }
    }
}