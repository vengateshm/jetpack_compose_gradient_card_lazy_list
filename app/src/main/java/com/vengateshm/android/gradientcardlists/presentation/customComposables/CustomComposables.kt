import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vengateshm.android.gradientcardlists.utils.RATING_BAR
import kotlin.math.floor

@Composable
fun RatingBar(rating: Double, iconRes: Int) {
    val flooredRating = floor(rating)
    Row(modifier = Modifier.testTag(RATING_BAR)) {
        repeat(flooredRating.toInt()) {
            Image(modifier = Modifier.size(16.dp, 16.dp),
                painter = painterResource(id = iconRes),
                contentDescription = "Rating star")
        }
    }
}