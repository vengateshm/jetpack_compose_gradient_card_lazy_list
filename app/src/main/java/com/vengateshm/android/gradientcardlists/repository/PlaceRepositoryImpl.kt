package com.vengateshm.android.gradientcardlists.repository

import com.vengateshm.android.gradientcardlists.model.Place
import com.vengateshm.android.gradientcardlists.utils.PlaceService

class PlaceRepositoryImpl : PlaceRepository {
    override fun getAllPlaces(): List<Place> = PlaceService.getPlaces()
}