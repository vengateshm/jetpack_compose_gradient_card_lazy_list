package com.vengateshm.android.gradientcardlists.repository

import com.vengateshm.android.gradientcardlists.model.Place

interface PlaceRepository {
    fun getAllPlaces(): List<Place>
}