package com.pim.planta.pantallaencuesta

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
import com.google.relay.compose.RelayText
import com.google.relay.compose.RelayVector
import com.pim.planta.R

/**
 * Pantalla de Encuesta para la elección de planta
 *
 * This composable was generated from the UI Package 'pantalla_encuesta'.
 * Generated code; do not edit directly
 */
@Composable
fun PantallaEncuesta(modifier: Modifier = Modifier) {
    TopLevel(modifier = modifier) {
        Rectangle4(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 57.0.dp,
                    y = 320.0.dp
                )
            )
        )
        Rectangle5(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 57.0.dp,
                    y = 390.0.dp
                )
            )
        )
        Rectangle6(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 57.0.dp,
                    y = 460.0.dp
                )
            )
        )
        Rectangle7(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 59.0.dp,
                    y = 530.0.dp
                )
            )
        )
        SelectYourObjective(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 53.0.dp,
                    y = 189.0.dp
                )
            )
        )
        IncreaseProductivity(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 68.0.dp,
                    y = 331.0.dp
                )
            )
        )
        ReduceDigitalDependency(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 68.0.dp,
                    y = 402.0.dp
                )
            )
        )
        ImproveMentalHealth(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 68.0.dp,
                    y = 471.0.dp
                )
            )
        )
        BuildHealthierDigitalHabits(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 71.0.dp,
                    y = 541.0.dp
                )
            )
        )
        Boton(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopCenter,
                offset = DpOffset(
                    x = 0.0.dp,
                    y = 709.0.dp
                )
            )
        ) {
            Button()
        }
    }
}

@Preview(widthDp = 428, heightDp = 926)
@Composable
private fun PantallaEncuestaPreview() {
    MaterialTheme {
        RelayContainer {
            PantallaEncuesta(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
        }
    }
}

@Composable
fun Rectangle4(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_encuesta_rectangle_4),
        modifier = modifier.requiredWidth(314.0.dp).requiredHeight(40.0.dp)
    )
}

@Composable
fun Rectangle5(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_encuesta_rectangle_5),
        modifier = modifier.requiredWidth(314.0.dp).requiredHeight(40.0.dp)
    )
}

@Composable
fun Rectangle6(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_encuesta_rectangle_6),
        modifier = modifier.requiredWidth(314.0.dp).requiredHeight(40.0.dp)
    )
}

@Composable
fun Rectangle7(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_encuesta_rectangle_7),
        modifier = modifier.requiredWidth(308.0.dp).requiredHeight(40.0.dp)
    )
}

@Composable
fun SelectYourObjective(modifier: Modifier = Modifier) {
    RelayText(
        content = "Select your objective:",
        fontSize = 32.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 0.9375.em,
        maxLines = -1,
        modifier = modifier.requiredWidth(334.0.dp)
    )
}

@Composable
fun IncreaseProductivity(modifier: Modifier = Modifier) {
    RelayText(
        content = "Increase productivity ",
        fontFamily = kumbhSans,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2857142639160157.em,
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}

@Composable
fun ReduceDigitalDependency(modifier: Modifier = Modifier) {
    RelayText(
        content = "Reduce digital dependency",
        fontFamily = kumbhSans,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2857142639160157.em,
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}

@Composable
fun ImproveMentalHealth(modifier: Modifier = Modifier) {
    RelayText(
        content = "Improve mental health",
        fontFamily = kumbhSans,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2857142639160157.em,
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}

@Composable
fun BuildHealthierDigitalHabits(modifier: Modifier = Modifier) {
    RelayText(
        content = "Build healthier digital habits",
        fontFamily = kumbhSans,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2857142639160157.em,
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}

@Composable
fun Button(modifier: Modifier = Modifier) {
    RelayText(
        content = "Continue",
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
        backgroundColor = Color(
            alpha = 255,
            red = 228,
            green = 246,
            blue = 239
        ),
        isStructured = false,
        content = content,
        modifier = modifier.fillMaxWidth(1.0f).fillMaxHeight(1.0f)
    )
}
