package com.vengateshm.android.gradientcardlists

import RatingBar
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vengateshm.android.gradientcardlists.utils.RATING_BAR
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RatingBarTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun testRatingBarWithRatingGreaterThan4() {
        composeRule.setContent {
            RatingBar(rating = 4.4, iconRes = R.drawable.ic_baseline_star_rate_24)
        }
        composeRule.onRoot(useUnmergedTree = true).printToLog("TAG")
        composeRule.onNodeWithTag(RATING_BAR)
            .assertExists()
            .onChildren()
            .assertCountEquals(4)
    }

    @Test
    fun testRatingBarWithRatingEqualTo1() {
        composeRule.setContent {
            RatingBar(rating = 1.0, iconRes = R.drawable.ic_baseline_star_rate_24)
        }
        composeRule.onRoot(useUnmergedTree = true).printToLog("TAG")
        composeRule.onNodeWithTag(RATING_BAR)
            .assertExists()
            .onChildren()
            .assertCountEquals(1)
    }

    @Test
    fun testRatingBarWithRatingEqualToZero() {
        composeRule.setContent {
            RatingBar(rating = 0.0, iconRes = R.drawable.ic_baseline_star_rate_24)
        }
        composeRule.onRoot(useUnmergedTree = true).printToLog("TAG")
        composeRule.onNodeWithTag(RATING_BAR)
            .assertExists()
            .onChildren()
            .assertCountEquals(0)
    }
}