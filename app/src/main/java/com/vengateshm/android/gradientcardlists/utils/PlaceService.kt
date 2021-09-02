package com.vengateshm.android.gradientcardlists.utils

import com.vengateshm.android.gradientcardlists.model.Place

object PlaceService {
    fun getPlaces(): List<Place> = listOf(
        Place("Dubai Mall Food Court",
            "Cosy · Casual · Good for kids",
            "Dubai · In The Dubai Mall",
            4.4,
            "#6DC8F3",
            "#73A1F9"),
        Place("Hamriyah Food Court",
            "All you can eat · Casual · Groups",
            "Sharjah",
            3.7,
            "#FFB157",
            "#FFA057"),
        Place("Gate of Food Court",
            "Casual · Groups",
            "Dubai · Near Dubai Aquarium",
            4.5,
            "#FF5B95",
            "#F8556D"),
        Place("Express Food Court",
            "Cosy · Casual · Good for kids",
            "Dubai · In The Dubai Mall",
            4.1,
            "#D76EF5",
            "#8F7AFE"),
        Place("BurJuman Food Court",
            "Self service · Stand in",
            "Dubai · In BurJuman",
            2.8,
            "#42E695",
            "#3BB2B8")
    ).shuffled()
}