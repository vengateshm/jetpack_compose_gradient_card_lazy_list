package com.vengateshm.android.gradientcardlists.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.vengateshm.android.gradientcardlists.model.Place
import com.vengateshm.android.gradientcardlists.repository.PlaceRepository
import com.vengateshm.android.gradientcardlists.repository.PlaceRepositoryImpl

class MainViewModel : ViewModel() {
    private val repository: PlaceRepository = PlaceRepositoryImpl()

    private var _places = mutableStateOf<List<Place>>(emptyList())
    val places = _places

    fun getAllPlaces() {
        _places.value = repository.getAllPlaces()
    }
}