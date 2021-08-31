package com.vengateshm.android.gradientcardlists.presentation.activity

import RatingBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.vengateshm.android.gradientcardlists.R
import com.vengateshm.android.gradientcardlists.model.Place
import com.vengateshm.android.gradientcardlists.presentation.viewmodel.MainViewModel
import com.vengateshm.android.gradientcardlists.ui.theme.GradientCardListsTheme
import com.vengateshm.android.gradientcardlists.utils.toColorFormat

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @ExperimentalUnitApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val places = mainViewModel.places
        mainViewModel.getAllPlaces()

        setContent {
            GradientCardListsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = {
                            TopAppBar(title = {
                                Text(text = "Food Places",
                                    style = TextStyle(
                                        color = Color.White,
                                        fontFamily = FontFamily.SansSerif,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = TextUnit(20f, TextUnitType.Sp)
                                    ))
                            })
                        }) {
                        LazyColumn(contentPadding = PaddingValues(
                            start = 8.dp, end = 8.dp, bottom = 8.dp
                        )) {
                            items(places.value) { place ->
                                Spacer(Modifier.height(8.dp))
                                PlaceCard(Place(place.name,
                                    place.category,
                                    place.location,
                                    place.rating,
                                    place.linearGradientStartColor,
                                    place.linearGradientEndColor),
                                    R.drawable.thumb_image,
                                    R.drawable.ic_baseline_location_on_24,
                                    R.drawable.ic_baseline_star_rate_24)
                            }
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalUnitApi
@Composable
private fun PlaceCard(place: Place, thumbImgRes: Int, locImgRes: Int, ratingImgRes: Int) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(24.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.linearGradient(
                listOf(Color(place.linearGradientStartColor.toColorFormat()),
                    Color(place.linearGradientEndColor.toColorFormat()))
            ))
        ) {
            Canvas(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(width = 100.dp, height = 150.dp),
            ) {
                val radius = 24.0f
                drawPath(
                    path = Path().apply {
                        moveTo(0f, size.height)
                        lineTo(size.width - radius, size.height)
                        quadraticBezierTo(
                            size.width, size.height, size.width, size.height - radius)
                        lineTo(size.width, radius)
                        quadraticBezierTo(size.width, 0f, size.width - radius, 0f)
                        lineTo(size.width - 1.5f * radius, 0f)
                        quadraticBezierTo(-radius, 2 * radius, 0f, size.height)
                        close()
                    },
                    brush = Brush.linearGradient(colors = listOf(Color(place.linearGradientStartColor.toColorFormat()),
                        Color(place.linearGradientEndColor.toColorFormat())))
                )
            }
            Row(modifier = Modifier
                .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {
                Row {
                    Image(modifier = Modifier
                        .size(64.dp, 64.dp),
                        painter = painterResource(id = thumbImgRes),
                        contentDescription = null)
                    Column {
                        Text(text = place.name,
                            style = TextStyle(
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontFamily = FontFamily.SansSerif
                            ))
                        Text(text = place.category,
                            style = TextStyle(
                                color = Color.White,
                                fontFamily = FontFamily.SansSerif
                            ))
                        Spacer(Modifier.height(16.dp))
                        Row {
                            Image(painter = painterResource(locImgRes),
                                contentDescription = null)
                            Spacer(Modifier.height(8.dp))
                            Text(text = place.location,
                                style = TextStyle(
                                    color = Color.White,
                                    fontFamily = FontFamily.SansSerif,
                                    fontSize = TextUnit(12f, TextUnitType.Sp)
                                ))
                        }
                    }
                }
                Column(modifier = Modifier
                    .padding(end = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = place.rating.toString(),
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                            fontSize = TextUnit(12f, TextUnitType.Sp)
                        ))
                    RatingBar(place.rating, ratingImgRes)
                }
            }
        }
    }
}