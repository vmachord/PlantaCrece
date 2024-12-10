package com.pim.planta.hellocard

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.google.relay.compose.CrossAxisAlignment
import com.google.relay.compose.MainAxisAlignment
import com.google.relay.compose.RelayContainer
import com.google.relay.compose.RelayContainerScope
import com.google.relay.compose.RelayImage
import com.google.relay.compose.RelayText
import com.pim.planta.R

/**
 * Omgwow locura
 *
 * This composable was generated from the UI Package 'hello_card'.
 * Generated code; do not edit directly
 */
@Composable
fun HelloCard(modifier: Modifier = Modifier) {
    TopLevel(modifier = modifier) {
        Image()
        Title()
    }
}

@Preview(widthDp = 248, heightDp = 201)
@Composable
private fun HelloCardPreview() {
    MaterialTheme {
        HelloCard()
    }
}

@Composable
fun Image(modifier: Modifier = Modifier) {
    RelayImage(
        image = painterResource(R.drawable.hello_card_image),
        contentScale = ContentScale.Crop,
        modifier = modifier.requiredWidth(216.0.dp).requiredHeight(124.0.dp)
    )
}

@Composable
fun Title(modifier: Modifier = Modifier) {
    RelayText(
        content = "Hello World",
        fontSize = 24.0.sp,
        fontFamily = montserrat,
        color = Color(
            alpha = 255,
            red = 27,
            green = 28,
            blue = 32
        ),
        height = 1.219000021616618.em,
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}

@Composable
fun TopLevel(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        backgroundColor = Color(
            alpha = 255,
            red = 252,
            green = 246,
            blue = 239
        ),
        mainAxisAlignment = MainAxisAlignment.Start,
        crossAxisAlignment = CrossAxisAlignment.Start,
        padding = PaddingValues(all = 16.0.dp),
        itemSpacing = 16.0,
        content = content,
        modifier = modifier.width(IntrinsicSize.Min)
    )
}
