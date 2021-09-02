package com.vengateshm.android.gradientcardlists

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vengateshm.android.gradientcardlists.model.Place
import com.vengateshm.android.gradientcardlists.presentation.activity.MainActivity
import com.vengateshm.android.gradientcardlists.presentation.activity.MainScreenContent
import com.vengateshm.android.gradientcardlists.presentation.viewmodel.MainViewModel
import com.vengateshm.android.gradientcardlists.ui.theme.GradientCardListsTheme
import com.vengateshm.android.gradientcardlists.utils.MAIN_PLACES_LIST
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val androidComposeTestRule = createAndroidComposeRule<MainActivity>()

    private val mainViewModel = MainViewModel()

    @ExperimentalUnitApi
    @Test
    fun testTopAppBarTitle() {
        androidComposeTestRule.launchMainScreen(emptyList())
        // Verify title in Top App Bar
        androidComposeTestRule.onNodeWithText(text = "Food Places").assertIsDisplayed()
    }

    @ExperimentalUnitApi
    @Test
    fun testPlacesListEmptyState() {
        androidComposeTestRule.launchMainScreen(emptyList())
        // Verify places list empty state
        androidComposeTestRule.apply {
            onNodeWithText(text = "No Places found.").assertIsDisplayed()
        }
    }

    @ExperimentalUnitApi
    @Test
    fun testPlacesListMustBeVisible() {
        mainViewModel.getAllPlaces()
        androidComposeTestRule.launchMainScreen(mainViewModel.places.value)
        androidComposeTestRule.apply {
            onNodeWithTag(MAIN_PLACES_LIST).assertIsDisplayed()
        }
    }

    @ExperimentalUnitApi
    @Test
    fun testPlacesListCount() {
        mainViewModel.getAllPlaces()
        androidComposeTestRule.launchMainScreen(mainViewModel.places.value)
        androidComposeTestRule.apply {
            onNodeWithTag(MAIN_PLACES_LIST)
                .assertIsDisplayed()
                .onChildren()
                .assertCountEquals(5)
        }
    }

    @ExperimentalUnitApi
    @Test
    fun testPlacesListFirstAndLastItem() {
        mainViewModel.getAllPlaces()
        androidComposeTestRule.launchMainScreen(mainViewModel.places.value)

        androidComposeTestRule.onRoot().printToLog("TAG")

        androidComposeTestRule.apply {
            onNodeWithTag(MAIN_PLACES_LIST)
                .assertIsDisplayed()
                .onChildren()
                .onFirst()
                .onChildren()
                .assertCountEquals(5)
                .assertAny(hasText("Dubai Mall Food Court"))

            onNodeWithTag(MAIN_PLACES_LIST)
                .assertIsDisplayed()
                .onChildren()
                .onLast()
                .onChildren()
                .assertCountEquals(5)
                .assertAny(hasText("2.8"))
        }
    }
}

@ExperimentalUnitApi
fun AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>.launchMainScreen(places: List<Place>) {
    setContent {
        GradientCardListsTheme {
            MainScreenContent(places = places)
        }
    }
}