package com.pim.planta.inicio

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.google.relay.compose.RelayContainer
import com.google.relay.compose.RelayContainerArrangement
import com.google.relay.compose.RelayContainerScope
import com.google.relay.compose.RelayImage
import com.google.relay.compose.RelayText
import com.google.relay.compose.relayDropShadow
import com.pim.planta.R

/**
 * Página principal
 *
 * This composable was generated from the UI Package 'inicio'.
 * Generated code; do not edit directly
 */
@Composable
fun Inicio(modifier: Modifier = Modifier) {
    TopLevel(modifier = modifier) {
        MeshGradient11()
        Plantoo(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopCenter,
                offset = DpOffset(
                    x = 0.0.dp,
                    y = 340.0.dp
                )
            )
        )
        CultivateYourTimeHarvestYourWellBeing(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopCenter,
                offset = DpOffset(
                    x = -0.5.dp,
                    y = 440.0.dp
                )
            )
        )
        Boton(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 152.0.dp,
                    y = 560.0.dp
                )
            )
        ) {
            Button()
        }
    }
}

@Preview(widthDp = 428, heightDp = 926)
@Composable
private fun InicioPreview() {
    MaterialTheme {
        RelayContainer {
            Inicio(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
        }
    }
}

@Composable
fun MeshGradient11(modifier: Modifier = Modifier) {
    RelayImage(
        image = painterResource(R.drawable.inicio_mesh_gradient_1_1),
        contentScale = ContentScale.Crop,
        modifier = modifier.requiredWidth(428.0.dp).requiredHeight(926.0.dp)
    )
}

@Composable
fun Plantoo(modifier: Modifier = Modifier) {
    RelayText(
        content = "plantoo",
        fontSize = 96.0.sp,
        fontFamily = fredoka,
        color = Color(
            alpha = 255,
            red = 52,
            green = 208,
            blue = 142
        ),
        height = 0.5208333206176757.em,
        textAlign = TextAlign.Left,
        maxLines = -1,
        modifier = modifier.requiredWidth(334.0.dp).requiredHeight(80.0.dp)
    )
}

@Composable
fun CultivateYourTimeHarvestYourWellBeing(modifier: Modifier = Modifier) {
    RelayText(
        content = "Cultivate your time, harvest your well-being",
        fontSize = 18.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2349999745686848.em,
        maxLines = -1,
        modifier = modifier.requiredWidth(263.0.dp)
    )
}

@Composable
fun Button(modifier: Modifier = Modifier) {
    RelayText(
        content = "Get Started",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 255,
            green = 255,
            blue = 255
        ),
        height = 0.875.em,
        modifier = modifier.wrapContentHeight(
            align = Alignment.CenterVertically,
            unbounded = true
        )
    )
}

@Composable
fun Boton(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        backgroundColor = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        arrangement = RelayContainerArrangement.Row,
        padding = PaddingValues(
            start = 16.0.dp,
            top = 8.0.dp,
            end = 16.0.dp,
            bottom = 8.0.dp
        ),
        itemSpacing = 10.0,
        clipToParent = false,
        radius = 24.0,
        content = content,
        modifier = modifier.requiredWidth(124.0.dp).requiredHeight(45.0.dp)
    )
}

@Composable
fun TopLevel(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        strokeWidth = 1.0,
        strokeColor = Color(
            alpha = 255,
            red = 0,
            green = 0,
            blue = 0
        ),
        content = content,
        modifier = modifier.fillMaxWidth(1.0f).fillMaxHeight(1.0f).relayDropShadow(
            color = Color(
                alpha = 63,
                red = 0,
                green = 0,
                blue = 0
            ),
            borderRadius = 0.0.dp,
            blur = 4.0.dp,
            offsetX = 0.0.dp,
            offsetY = 4.0.dp,
            spread = 0.0.dp
        )
    )
}
