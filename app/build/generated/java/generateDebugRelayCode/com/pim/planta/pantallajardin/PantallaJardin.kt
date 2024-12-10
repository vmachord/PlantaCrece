package com.pim.planta.pantallajardin

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.google.relay.compose.RelayContainer
import com.google.relay.compose.RelayContainerScope
import com.google.relay.compose.RelayText
import com.google.relay.compose.RelayVector
import com.pim.planta.R

/**
 * Planta
 *
 * This composable was generated from the UI Package 'pantalla_jardin'.
 * Generated code; do not edit directly
 */
@Composable
fun PantallaJardin(modifier: Modifier = Modifier) {
    TopLevel(modifier = modifier) {
        Vector(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 3.576469994464901.dp,
                    y = 4.728579293375105.dp
                )
            ).rowWeight(1.0f).columnWeight(1.0f)
        )
        Menu(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopCenter,
                offset = DpOffset(
                    x = 0.0.dp,
                    y = 840.0.dp
                )
            )
        ) {
            Perfil(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
            Moodtrack(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
            Jardin(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
            LineaDestacada(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 99.0.dp,
                        y = 26.0.dp
                    )
                )
            )
            Home(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
        }
        QuickStatistic(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 30.0.dp,
                    y = 710.0.dp
                )
            )
        ) {
            Base4()
            MyCares(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.Center,
                    offset = DpOffset(
                        x = 0.0.dp,
                        y = 0.0.dp
                    )
                )
            )
        }
        NachoElTulipNL2(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopCenter,
                offset = DpOffset(
                    x = 0.5.dp,
                    y = 82.0.dp
                )
            )
        )
        Vector154(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 29.0.dp,
                    y = 129.5.dp
                )
            )
        )
        Group4(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopCenter,
                offset = DpOffset(
                    x = 81.0.dp,
                    y = 162.0.dp
                )
            )
        ) {
            Vector1(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = -1.0.dp,
                        y = -1.0.dp
                    )
                ).rowWeight(1.0f).columnWeight(1.0f)
            )
        }
        FLORES061(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopCenter,
                offset = DpOffset(
                    x = 0.0.dp,
                    y = 263.0.dp
                )
            )
        ) {
            Ellipse21(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
            Vector2(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 9.99908447265625.dp,
                        y = 8.905283477572993.dp
                    )
                ).rowWeight(1.0f).columnWeight(1.0f)
            )
            Vector3(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
            Vector4(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 12.28240966796875.dp,
                        y = 17.381070082834412.dp
                    )
                ).rowWeight(1.0f).columnWeight(1.0f)
            )
            Vector5(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = -2.001861572265625.dp,
                        y = -1.04644775390625.dp
                    )
                ).rowWeight(1.0f).columnWeight(1.0f)
            )
            Vector6(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
            Vector7(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 14.178314208984375.dp,
                        y = 13.030193417007467.dp
                    )
                ).rowWeight(1.0f).columnWeight(1.0f)
            )
            Vector8(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
        }
    }
}

@Preview(widthDp = 428, heightDp = 926)
@Composable
private fun PantallaJardinPreview() {
    MaterialTheme {
        RelayContainer {
            PantallaJardin(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
        }
    }
}

@Composable
fun Vector(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_jardin_vector),
        modifier = modifier.padding(
            paddingValues = PaddingValues(
                start = 115.94407148317526.dp,
                top = 159.4455638306884.dp,
                end = 293.29996042295267.dp,
                bottom = 744.8015331846925.dp
            )
        ).fillMaxWidth(1.0f).fillMaxHeight(1.0f)
    )
}

@Composable
fun Perfil(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_jardin_perfil),
        modifier = modifier.padding(
            paddingValues = PaddingValues(
                start = 294.0.dp,
                top = 2.0.dp,
                end = 0.0.dp,
                bottom = 7.0.dp
            )
        ).fillMaxWidth(1.0f).fillMaxHeight(1.0f)
    )
}

@Composable
fun Moodtrack(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_jardin_moodtrack),
        modifier = modifier.padding(
            paddingValues = PaddingValues(
                start = 193.0.dp,
                top = 3.0.dp,
                end = 95.0.dp,
                bottom = 8.0.dp
            )
        ).fillMaxWidth(1.0f).fillMaxHeight(1.0f)
    )
}

@Composable
fun Jardin(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_jardin_jardin),
        modifier = modifier.padding(
            paddingValues = PaddingValues(
                start = 97.0.dp,
                top = 0.0.dp,
                end = 195.0.dp,
                bottom = 6.0.dp
            )
        ).fillMaxWidth(1.0f).fillMaxHeight(1.0f)
    )
}

@Composable
fun LineaDestacada(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_jardin_linea_destacada),
        modifier = modifier.requiredWidth(12.0.dp).requiredHeight(0.0.dp)
    )
}

@Composable
fun Home(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_jardin_home),
        modifier = modifier.padding(
            paddingValues = PaddingValues(
                start = 0.0.dp,
                top = 2.0.dp,
                end = 290.0.dp,
                bottom = 7.0.dp
            )
        ).fillMaxWidth(1.0f).fillMaxHeight(1.0f)
    )
}

@Composable
fun Menu(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(310.0.dp).requiredHeight(27.0.dp)
    )
}

@Composable
fun Base4(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_jardin_base_4),
        modifier = modifier.requiredWidth(369.0.dp).requiredHeight(40.0.dp)
    )
}

@Composable
fun MyCares(modifier: Modifier = Modifier) {
    RelayText(
        content = "My cares",
        fontSize = 18.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 255,
            green = 255,
            blue = 255
        ),
        height = 1.2349999745686848.em,
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}

@Composable
fun QuickStatistic(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        radius = 24.0,
        content = content,
        modifier = modifier.requiredWidth(369.0.dp).requiredHeight(40.0.dp)
    )
}

@Composable
fun NachoElTulipNL2(modifier: Modifier = Modifier) {
    RelayText(
        content = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color(
                        alpha = 255,
                        red = 52,
                        green = 208,
                        blue = 142
                    ),
                    fontFamily = aventa,
                    fontWeight = FontWeight(400.0.toInt())
                )
            ) {
                append("Nacho el Tulipán")
            }
            append("  |  L.2")
        },
        fontSize = 24.0.sp,
        fontFamily = aventa,
        height = 1.2349999745686848.em,
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}

@Composable
fun Vector154(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_jardin_vector_154),
        modifier = modifier.requiredWidth(370.0.dp).requiredHeight(0.0.dp)
    )
}

@Composable
fun Vector1(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_jardin_vector1),
        modifier = modifier.fillMaxWidth(1.0f).fillMaxHeight(1.0f)
    )
}

@Composable
fun Group4(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(14.0.dp).requiredHeight(17.0.dp)
    )
}

@Composable
fun Ellipse21(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_jardin_ellipse_21),
        modifier = modifier.padding(
            paddingValues = PaddingValues(
                start = 6.0.dp,
                top = 318.2470703125.dp,
                end = 0.0.dp,
                bottom = 11.952930450439453.dp
            )
        ).fillMaxWidth(1.0f).fillMaxHeight(1.0f)
    )
}

@Composable
fun Vector2(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_jardin_vector2),
        modifier = modifier.padding(
            paddingValues = PaddingValues(
                start = 80.43646715126681.dp,
                top = 5.874544368676197.dp,
                end = 62.96956159629178.dp,
                bottom = 303.23816467673396.dp
            )
        ).fillMaxWidth(1.0f).fillMaxHeight(1.0f)
    )
}

@Composable
fun Vector3(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_jardin_vector3),
        modifier = modifier.padding(
            paddingValues = PaddingValues(
                start = 0.0.dp,
                top = 144.5260009765625.dp,
                end = 0.5099945068359375.dp,
                bottom = 24.757553100585938.dp
            )
        ).fillMaxWidth(1.0f).fillMaxHeight(1.0f)
    )
}

@Composable
fun Vector4(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_jardin_vector4),
        modifier = modifier.padding(
            paddingValues = PaddingValues(
                start = 66.30231299931825.dp,
                top = 118.52021485823184.dp,
                end = 44.83419212763488.dp,
                bottom = 73.75746458512754.dp
            )
        ).fillMaxWidth(1.0f).fillMaxHeight(1.0f)
    )
}

@Composable
fun Vector5(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_jardin_vector5),
        modifier = modifier.padding(
            paddingValues = PaddingValues(
                start = 58.9072265625.dp,
                top = 73.94387817382812.dp,
                end = 41.435699462890625.dp,
                bottom = 50.8365478515625.dp
            )
        ).fillMaxWidth(1.0f).fillMaxHeight(1.0f)
    )
}

@Composable
fun Vector6(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_jardin_vector6),
        modifier = modifier.padding(
            paddingValues = PaddingValues(
                start = 0.0.dp,
                top = 144.61566162109375.dp,
                end = 0.5099945068359375.dp,
                bottom = 24.757537841796875.dp
            )
        ).fillMaxWidth(1.0f).fillMaxHeight(1.0f)
    )
}

@Composable
fun Vector7(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_jardin_vector7),
        modifier = modifier.padding(
            paddingValues = PaddingValues(
                start = 92.58255046676663.dp,
                top = 17.625064039182917.dp,
                end = 50.73388630081149.dp,
                bottom = 305.4643105030534.dp
            )
        ).fillMaxWidth(1.0f).fillMaxHeight(1.0f)
    )
}

@Composable
fun Vector8(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_jardin_vector8),
        modifier = modifier.padding(
            paddingValues = PaddingValues(
                start = 164.77880859375.dp,
                top = 119.7431640625.dp,
                end = 31.111705780029297.dp,
                bottom = 200.34627151489258.dp
            )
        ).fillMaxWidth(1.0f).fillMaxHeight(1.0f)
    )
}

@Composable
fun FLORES061(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        content = content,
        modifier = modifier.requiredWidth(228.0.dp).requiredHeight(381.0.dp)
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
            red = 244,
            green = 244,
            blue = 244
        ),
        isStructured = false,
        content = content,
        modifier = modifier.fillMaxWidth(1.0f).fillMaxHeight(1.0f)
    )
}
