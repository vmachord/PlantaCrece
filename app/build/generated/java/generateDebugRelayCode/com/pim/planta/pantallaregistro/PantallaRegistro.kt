package com.pim.planta.pantallaregistro

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.google.relay.compose.CrossAxisAlignment
import com.google.relay.compose.MainAxisAlignment
import com.google.relay.compose.RelayContainer
import com.google.relay.compose.RelayContainerArrangement
import com.google.relay.compose.RelayContainerScope
import com.google.relay.compose.RelayText
import com.google.relay.compose.RelayVector
import com.pim.planta.R

/**
 * Pantalla registro
 *
 * This composable was generated from the UI Package 'pantalla_registro'.
 * Generated code; do not edit directly
 */
@Composable
fun PantallaRegistro(modifier: Modifier = Modifier) {
    TopLevel(modifier = modifier) {
        Component2(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 30.0.dp,
                    y = 372.0.dp
                )
            )
        ) {
            Text()
        }
        Component5(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 30.0.dp,
                    y = 302.0.dp
                )
            )
        ) {
            Text1()
        }
        Component6(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 220.0.dp,
                    y = 302.0.dp
                )
            )
        ) {
            Text2()
        }
        Component3(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 30.0.dp,
                    y = 442.0.dp
                )
            )
        ) {
            Text3()
        }
        CreateAnAccount(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopCenter,
                offset = DpOffset(
                    x = 1.5.dp,
                    y = 202.0.dp
                )
            )
        )
        AlreadyHaveOneLogIn(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopCenter,
                offset = DpOffset(
                    x = 0.0.dp,
                    y = 239.0.dp
                )
            )
        )
        IAgreeToTheTermsConditions(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 56.0.dp,
                    y = 505.0.dp
                )
            )
        )
        OrRegisterWith(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopCenter,
                offset = DpOffset(
                    x = 0.0.dp,
                    y = 565.0.dp
                )
            )
        )
        Rectangle1(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 30.0.dp,
                    y = 502.0.dp
                )
            )
        )
        Vector1(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 279.5.dp,
                    y = 571.5.dp
                )
            )
        )
        Vector2(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 28.5.dp,
                    y = 571.5000052453665.dp
                )
            )
        )
        Group2(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 29.0.dp,
                    y = 602.0.dp
                )
            )
        ) {
            Rectangle2()
            Google(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 84.99945068359375.dp,
                        y = 14.0.dp
                    )
                )
            )
            Vector(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
        }
        Group3(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 219.0.dp,
                    y = 602.0.dp
                )
            )
        ) {
            Rectangle3()
            Apple(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 87.41400146484375.dp,
                        y = 13.0.dp
                    )
                )
            )
            Group1(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 53.0.dp,
                        y = 8.0.dp
                    )
                )
            ) {
                Vector1(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
                Vector2(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = -1.0.dp,
                            y = -1.0.dp
                        )
                    ).rowWeight(1.0f).columnWeight(1.0f)
                )
            }
        }
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
        Frame72(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 312.0.dp,
                    y = 25.0.dp
                )
            )
        ) {}
    }
}

@Preview(widthDp = 428, heightDp = 926)
@Composable
private fun PantallaRegistroPreview() {
    MaterialTheme {
        RelayContainer {
            PantallaRegistro(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
        }
    }
}

@Composable
fun Text(modifier: Modifier = Modifier) {
    RelayText(
        content = "Email",
        fontFamily = aventaPersonalUseOnly,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        textAlign = TextAlign.Left,
        fontWeight = FontWeight(300.0.toInt()),
        modifier = modifier
    )
}

@Composable
fun Component2(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        backgroundColor = Color(
            alpha = 255,
            red = 252,
            green = 252,
            blue = 252
        ),
        mainAxisAlignment = MainAxisAlignment.Start,
        crossAxisAlignment = CrossAxisAlignment.Start,
        arrangement = RelayContainerArrangement.Row,
        padding = PaddingValues(
            start = 16.0.dp,
            top = 10.0.dp,
            end = 16.0.dp,
            bottom = 10.0.dp
        ),
        itemSpacing = 10.0,
        clipToParent = false,
        radius = 10.0,
        content = content,
        modifier = modifier.height(IntrinsicSize.Min).requiredWidth(370.0.dp)
    )
}

@Composable
fun Text1(modifier: Modifier = Modifier) {
    RelayText(
        content = "Name",
        fontFamily = aventaPersonalUseOnly,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        textAlign = TextAlign.Left,
        fontWeight = FontWeight(300.0.toInt()),
        modifier = modifier
    )
}

@Composable
fun Component5(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        backgroundColor = Color(
            alpha = 255,
            red = 252,
            green = 252,
            blue = 252
        ),
        mainAxisAlignment = MainAxisAlignment.Start,
        crossAxisAlignment = CrossAxisAlignment.Start,
        arrangement = RelayContainerArrangement.Row,
        padding = PaddingValues(
            start = 16.0.dp,
            top = 10.0.dp,
            end = 16.0.dp,
            bottom = 10.0.dp
        ),
        itemSpacing = 10.0,
        clipToParent = false,
        radius = 10.0,
        content = content,
        modifier = modifier.height(IntrinsicSize.Min).requiredWidth(180.0.dp)
    )
}

@Composable
fun Text2(modifier: Modifier = Modifier) {
    RelayText(
        content = "Last name",
        fontFamily = aventaPersonalUseOnly,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        textAlign = TextAlign.Left,
        fontWeight = FontWeight(300.0.toInt()),
        modifier = modifier
    )
}

@Composable
fun Component6(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        backgroundColor = Color(
            alpha = 255,
            red = 252,
            green = 252,
            blue = 252
        ),
        mainAxisAlignment = MainAxisAlignment.Start,
        crossAxisAlignment = CrossAxisAlignment.Start,
        arrangement = RelayContainerArrangement.Row,
        padding = PaddingValues(
            start = 16.0.dp,
            top = 10.0.dp,
            end = 16.0.dp,
            bottom = 10.0.dp
        ),
        itemSpacing = 10.0,
        clipToParent = false,
        radius = 10.0,
        content = content,
        modifier = modifier.height(IntrinsicSize.Min).requiredWidth(180.0.dp)
    )
}

@Composable
fun Text3(modifier: Modifier = Modifier) {
    RelayText(
        content = "Password",
        fontFamily = aventaPersonalUseOnly,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        textAlign = TextAlign.Left,
        fontWeight = FontWeight(300.0.toInt()),
        modifier = modifier
    )
}

@Composable
fun Component3(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        backgroundColor = Color(
            alpha = 255,
            red = 252,
            green = 252,
            blue = 252
        ),
        mainAxisAlignment = MainAxisAlignment.Start,
        crossAxisAlignment = CrossAxisAlignment.Start,
        arrangement = RelayContainerArrangement.Row,
        padding = PaddingValues(
            start = 16.0.dp,
            top = 10.0.dp,
            end = 16.0.dp,
            bottom = 10.0.dp
        ),
        itemSpacing = 10.0,
        clipToParent = false,
        radius = 10.0,
        content = content,
        modifier = modifier.height(IntrinsicSize.Min).requiredWidth(370.0.dp)
    )
}

@Composable
fun CreateAnAccount(modifier: Modifier = Modifier) {
    RelayText(
        content = "Create an account",
        fontSize = 32.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 0.4375.em,
        textAlign = TextAlign.Left,
        maxLines = -1,
        modifier = modifier.requiredWidth(299.0.dp).requiredHeight(20.0.dp)
    )
}

@Composable
fun AlreadyHaveOneLogIn(modifier: Modifier = Modifier) {
    RelayText(
        content = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color(
                        alpha = 255,
                        red = 7,
                        green = 62,
                        blue = 36
                    ),
                    fontFamily = aventa,
                    fontWeight = FontWeight(400.0.toInt())
                )
            ) {
                append("Already have one?")
            }
            withStyle(
                style = SpanStyle(
                    color = Color(
                        alpha = 255,
                        red = 7,
                        green = 62,
                        blue = 36
                    ),
                    fontFamily = aventa,
                    fontWeight = FontWeight(400.0.toInt())
                )
            ) {
                append(" ")
            }
            withStyle(
                style = SpanStyle(
                    color = Color(
                        alpha = 255,
                        red = 7,
                        green = 62,
                        blue = 36
                    ),
                    fontFamily = aventa,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight(400.0.toInt())
                )
            ) {
                append("Log in")
            }
        },
        fontFamily = aventaPersonalUseOnly,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        textAlign = TextAlign.Left,
        fontWeight = FontWeight(300.0.toInt()),
        modifier = modifier
    )
}

@Composable
fun IAgreeToTheTermsConditions(modifier: Modifier = Modifier) {
    RelayText(
        content = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color(
                        alpha = 255,
                        red = 7,
                        green = 62,
                        blue = 36
                    )
                )
            ) {
                append("I agree to the ")
            }
            withStyle(
                style = SpanStyle(
                    color = Color(
                        alpha = 255,
                        red = 7,
                        green = 62,
                        blue = 36
                    ),
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append("Terms & Conditions")
            }
        },
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}

@Composable
fun OrRegisterWith(modifier: Modifier = Modifier) {
    RelayText(
        content = "or register with",
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}

@Composable
fun Rectangle1(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_registro_rectangle_1),
        modifier = modifier.requiredWidth(20.0.dp).requiredHeight(20.0.dp)
    )
}

@Composable
fun Vector1(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_registro_vector_1),
        modifier = modifier.requiredWidth(120.0.dp).requiredHeight(0.0.dp)
    )
}

@Composable
fun Vector2(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_registro_vector_2),
        modifier = modifier.requiredWidth(120.0.dp).requiredHeight(0.0.dp)
    )
}

@Composable
fun Rectangle2(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_registro_rectangle_2),
        modifier = modifier.requiredWidth(180.0.dp).requiredHeight(40.0.dp)
    )
}

@Composable
fun Google(modifier: Modifier = Modifier) {
    RelayText(
        content = "Google",
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}

@Composable
fun Vector(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_registro_vector),
        modifier = modifier.padding(
            paddingValues = PaddingValues(
                start = 47.0.dp,
                top = 9.0.dp,
                end = 113.00054931640625.dp,
                bottom = 11.000999450683594.dp
            )
        ).fillMaxWidth(1.0f).fillMaxHeight(1.0f)
    )
}

@Composable
fun Group2(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(180.0.dp).requiredHeight(40.0.dp)
    )
}

@Composable
fun Rectangle3(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_registro_rectangle_3),
        modifier = modifier.requiredWidth(180.0.dp).requiredHeight(40.0.dp)
    )
}

@Composable
fun Apple(modifier: Modifier = Modifier) {
    RelayText(
        content = "Apple",
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}

@Composable
fun Vector1(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_registro_vector1),
        modifier = modifier.padding(
            paddingValues = PaddingValues(
                start = 8.251510620117188.dp,
                top = 0.0.dp,
                end = 4.343488693237305.dp,
                bottom = 15.590998649597168.dp
            )
        ).fillMaxWidth(1.0f).fillMaxHeight(1.0f)
    )
}

@Composable
fun Vector2(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.pantalla_registro_vector2),
        modifier = modifier.padding(
            paddingValues = PaddingValues(
                start = 0.0.dp,
                top = 4.9954833984375.dp,
                end = 0.0.dp,
                bottom = 0.0000152587890625.dp
            )
        ).fillMaxWidth(1.0f).fillMaxHeight(1.0f)
    )
}

@Composable
fun Group1(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(16.413999557495117.dp).requiredHeight(20.24599838256836.dp)
    )
}

@Composable
fun Group3(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(180.0.dp).requiredHeight(40.0.dp)
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
fun Frame72(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        content = content,
        modifier = modifier.requiredWidth(100.0.dp).requiredHeight(100.0.dp)
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
