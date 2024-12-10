package com.pim.planta.moodtrack11

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.google.relay.compose.CrossAxisAlignment
import com.google.relay.compose.MainAxisAlignment
import com.google.relay.compose.RelayContainer
import com.google.relay.compose.RelayContainerArrangement
import com.google.relay.compose.RelayContainerScope
import com.google.relay.compose.RelayText
import com.google.relay.compose.RelayVector
import com.pim.planta.R

/**
 * Calendario
 *
 * This composable was generated from the UI Package 'moodtrack_1_1'.
 * Generated code; do not edit directly
 */
@Composable
fun Moodtrack11(modifier: Modifier = Modifier) {
    TopLevel(modifier = modifier) {
        TarjetaMoodtrack(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 0.0.dp,
                    y = 450.0.dp
                )
            )
        )
        Menu(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 57.0.dp,
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
                        x = 197.0.dp,
                        y = 26.0.dp
                    )
                )
            )
            Home(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
        }
        BotNCalendario(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 40.0.dp,
                    y = 35.0.dp
                )
            )
        )
        Class2024(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 136.0.dp,
                    y = 45.0.dp
                )
            )
        )
        CambioMesIzquierda(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 18.585784912109375.dp,
                    y = 249.0.dp
                )
            )
        )
        CambioMesDerecha(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 399.0.dp,
                    y = 253.0.dp
                )
            )
        )
        MenuMoodtrackBeans(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 30.0.dp,
                    y = 600.0.dp
                )
            )
        ) {
            Group66 {
                Rectangle45()
                Group64(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 13.2105712890625.dp,
                            y = 63.0.dp
                        )
                    )
                ) {
                    Class20()
                    SUN(
                        modifier = Modifier.boxAlign(
                            alignment = Alignment.TopStart,
                            offset = DpOffset(
                                x = 0.0.dp,
                                y = 30.0.dp
                            )
                        )
                    )
                }
                Group65(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 76.43014526367188.dp,
                            y = 0.0.dp
                        )
                    )
                ) {
                    Rectangle46()
                    Group67(
                        modifier = Modifier.boxAlign(
                            alignment = Alignment.TopStart,
                            offset = DpOffset(
                                x = 74.54347229003906.dp,
                                y = 0.0.dp
                            )
                        )
                    ) {
                        Group69(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 151.9180450439453.dp,
                                    y = 0.0.dp
                                )
                            )
                        ) {
                            Rectangle48()
                            Group72(
                                modifier = Modifier.boxAlign(
                                    alignment = Alignment.TopStart,
                                    offset = DpOffset(
                                        x = 10.444854736328125.dp,
                                        y = 63.0.dp
                                    )
                                )
                            ) {
                                Class24(
                                    modifier = Modifier.boxAlign(
                                        alignment = Alignment.TopStart,
                                        offset = DpOffset(
                                            x = 2.7664794921875.dp,
                                            y = 0.0.dp
                                        )
                                    )
                                )
                                THU(
                                    modifier = Modifier.boxAlign(
                                        alignment = Alignment.TopStart,
                                        offset = DpOffset(
                                            x = 0.0.dp,
                                            y = 30.0.dp
                                        )
                                    )
                                )
                            }
                        }
                        Rectangle47()
                        Group70(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 13.21026611328125.dp,
                                    y = 63.0.dp
                                )
                            )
                        ) {
                            Class22()
                            TUE(
                                modifier = Modifier.boxAlign(
                                    alignment = Alignment.TopStart,
                                    offset = DpOffset(
                                        x = 0.0.dp,
                                        y = 30.0.dp
                                    )
                                )
                            )
                        }
                        Group71(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 74.54249572753906.dp,
                                    y = 0.0.dp
                                )
                            )
                        ) {
                            Rectangle49()
                            Group73(
                                modifier = Modifier.boxAlign(
                                    alignment = Alignment.TopStart,
                                    offset = DpOffset(
                                        x = 13.211273193359375.dp,
                                        y = 63.0.dp
                                    )
                                )
                            ) {
                                Class23()
                                WED(
                                    modifier = Modifier.boxAlign(
                                        alignment = Alignment.TopStart,
                                        offset = DpOffset(
                                            x = 0.0.dp,
                                            y = 30.0.dp
                                        )
                                    )
                                )
                            }
                        }
                    }
                    Group68(
                        modifier = Modifier.boxAlign(
                            alignment = Alignment.TopStart,
                            offset = DpOffset(
                                x = 13.570632934570312.dp,
                                y = 63.0.dp
                            )
                        )
                    ) {
                        Class21(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopCenter,
                                offset = DpOffset(
                                    x = -1.0.dp,
                                    y = 0.0.dp
                                )
                            )
                        )
                        MON(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopCenter,
                                offset = DpOffset(
                                    x = 0.0.dp,
                                    y = 30.0.dp
                                )
                            )
                        )
                    }
                }
            }
            Excited(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 87.0.dp,
                        y = 14.0.dp
                    )
                )
            ) {
                Ellipse10()
                Vector74(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 10.439208984375.dp,
                            y = 26.78546142578125.dp
                        )
                    )
                )
                Ellipse13(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 10.439208984375.dp,
                            y = 27.28546142578125.dp
                        )
                    )
                )
                Ellipse11(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 10.939208984375.dp,
                            y = 13.64306640625.dp
                        )
                    )
                )
                Ellipse12(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 28.439208984375.dp,
                            y = 13.64306640625.dp
                        )
                    )
                )
            }
            Excited1(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 162.0.dp,
                        y = 14.0.dp
                    )
                )
            ) {
                Ellipse14()
                Vector75(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 10.5.dp,
                            y = 29.5.dp
                        )
                    )
                )
                Ellipse15(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 10.939208984375.dp,
                            y = 13.64306640625.dp
                        )
                    )
                )
                Ellipse16(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 28.439208984375.dp,
                            y = 13.64306640625.dp
                        )
                    )
                )
            }
            (
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 239.0.dp,
                        y = 13.0.dp
                    )
                )
            )
            Class1(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 14.0.dp,
                        y = 8.0.dp
                    )
                )
            )
            Class2(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 319.0.dp,
                        y = 12.0.dp
                    )
                )
            )
        }
        FlechaDetails(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 110.5.dp,
                    y = 793.5.dp
                )
            )
        )
        Details(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 30.0.dp,
                    y = 788.0.dp
                )
            )
        )
        MyWeek(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 30.0.dp,
                    y = 540.0.dp
                )
            )
        )
        Class24Jan2024(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 30.0.dp,
                    y = 518.0.dp
                )
            )
        )
        Barra(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopCenter,
                offset = DpOffset(
                    x = -5.5.dp,
                    y = 488.5.dp
                )
            )
        )
        Calendario(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopCenter,
                offset = DpOffset(
                    x = 0.0.dp,
                    y = 105.0.dp
                )
            )
        ) {
            Group32 {
                Rectangle35()
                JANUARY(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopCenter,
                        offset = DpOffset(
                            x = 0.5.dp,
                            y = 20.360107421875.dp
                        )
                    )
                )
                Group74(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 20.0.dp,
                            y = 61.0.dp
                        )
                    )
                ) {
                    M()
                    S(
                        modifier = Modifier.boxAlign(
                            alignment = Alignment.TopStart,
                            offset = DpOffset(
                                x = 243.0.dp,
                                y = 0.0.dp
                            )
                        )
                    )
                    S1(
                        modifier = Modifier.boxAlign(
                            alignment = Alignment.TopStart,
                            offset = DpOffset(
                                x = 292.0.dp,
                                y = 0.0.dp
                            )
                        )
                    )
                    T(
                        modifier = Modifier.boxAlign(
                            alignment = Alignment.TopStart,
                            offset = DpOffset(
                                x = 147.0.dp,
                                y = 0.0.dp
                            )
                        )
                    )
                    F(
                        modifier = Modifier.boxAlign(
                            alignment = Alignment.TopStart,
                            offset = DpOffset(
                                x = 195.0.dp,
                                y = 0.0.dp
                            )
                        )
                    )
                    T1(
                        modifier = Modifier.boxAlign(
                            alignment = Alignment.TopStart,
                            offset = DpOffset(
                                x = 52.0.dp,
                                y = 0.0.dp
                            )
                        )
                    )
                    W(
                        modifier = Modifier.boxAlign(
                            alignment = Alignment.TopStart,
                            offset = DpOffset(
                                x = 96.0.dp,
                                y = 0.0.dp
                            )
                        )
                    )
                }
                Frame67(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 75.0.dp,
                            y = 100.0.dp
                        )
                    )
                ) {
                    Class1()
                    Class2()
                    Class3()
                    Class4()
                    Class5()
                    Class6()
                }
                Frame68(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 30.0.dp,
                            y = 134.31982421875.dp
                        )
                    )
                ) {
                    Frame1(modifier = Modifier.rowWeight(1.0f)) {
                        Class7()
                        Class8(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 45.0.dp,
                                    y = 0.0.dp
                                )
                            )
                        )
                        Class9(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 90.0.dp,
                                    y = 0.0.dp
                                )
                            )
                        )
                        Class10(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 134.0.dp,
                                    y = 0.0.dp
                                )
                            )
                        )
                        Class11(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 183.0.dp,
                                    y = 0.0.dp
                                )
                            )
                        )
                        Class12(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 229.0.dp,
                                    y = 0.0.dp
                                )
                            )
                        )
                        Class13(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 277.0.dp,
                                    y = 0.0.dp
                                )
                            )
                        )
                    }
                }
                Group75(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 18.0.dp,
                            y = 168.6396484375.dp
                        )
                    )
                ) {
                    Frame69 {
                        Class14(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 6.0.dp,
                                    y = 0.0.dp
                                )
                            )
                        )
                        Class15(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 54.0.dp,
                                    y = 0.0.dp
                                )
                            )
                        )
                        Class16(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 99.0.dp,
                                    y = 0.0.dp
                                )
                            )
                        )
                        Class17(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 146.0.dp,
                                    y = 0.0.dp
                                )
                            )
                        )
                        Class18(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 193.0.dp,
                                    y = 0.0.dp
                                )
                            )
                        )
                        Class19(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 241.0.dp,
                                    y = 0.0.dp
                                )
                            )
                        )
                        Class201(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 287.0.dp,
                                    y = 0.0.dp
                                )
                            )
                        )
                    }
                }
                Frame70(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 21.0.dp,
                            y = 202.6396484375.dp
                        )
                    )
                ) {
                    Class211(
                        modifier = Modifier.boxAlign(
                            alignment = Alignment.TopStart,
                            offset = DpOffset(
                                x = 3.0.dp,
                                y = 3.5.dp
                            )
                        )
                    )
                    Class221(
                        modifier = Modifier.boxAlign(
                            alignment = Alignment.TopStart,
                            offset = DpOffset(
                                x = 50.0.dp,
                                y = 3.5.dp
                            )
                        )
                    )
                    Class231(
                        modifier = Modifier.boxAlign(
                            alignment = Alignment.TopStart,
                            offset = DpOffset(
                                x = 95.0.dp,
                                y = 3.5.dp
                            )
                        )
                    )
                    Group50(
                        modifier = Modifier.boxAlign(
                            alignment = Alignment.TopStart,
                            offset = DpOffset(
                                x = 137.0.dp,
                                y = 0.0.dp
                            )
                        )
                    ) {
                        Ellipse22()
                        Class241(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 4.0.dp,
                                    y = 4.0.dp
                                )
                            )
                        )
                    }
                    Class25(
                        modifier = Modifier.boxAlign(
                            alignment = Alignment.TopStart,
                            offset = DpOffset(
                                x = 188.0.dp,
                                y = 3.5.dp
                            )
                        )
                    )
                    Class26(
                        modifier = Modifier.boxAlign(
                            alignment = Alignment.TopStart,
                            offset = DpOffset(
                                x = 236.0.dp,
                                y = 3.5.dp
                            )
                        )
                    )
                    Class27(
                        modifier = Modifier.boxAlign(
                            alignment = Alignment.TopStart,
                            offset = DpOffset(
                                x = 284.0.dp,
                                y = 3.5.dp
                            )
                        )
                    )
                }
                Vector160(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 67.5.dp,
                            y = 224.5.dp
                        )
                    )
                )
                Vector161(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 21.5.dp,
                            y = 224.5.dp
                        )
                    )
                )
                Group76(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 18.0.dp,
                            y = 243.6396484375.dp
                        )
                    )
                ) {
                    Frame71 {
                        Class28(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 6.0.dp,
                                    y = 0.0.dp
                                )
                            )
                        )
                        Class29(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 52.0.dp,
                                    y = 0.0.dp
                                )
                            )
                        )
                        Class30(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 98.0.dp,
                                    y = 0.0.dp
                                )
                            )
                        )
                        Class31(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 146.0.dp,
                                    y = 0.0.dp
                                )
                            )
                        )
                    }
                }
                Vector162(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 303.5.dp,
                            y = 187.5.dp
                        )
                    )
                )
                Vector163(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 207.5.dp,
                            y = 187.5.dp
                        )
                    )
                )
                Vector164(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 159.5.dp,
                            y = 187.5.dp
                        )
                    )
                )
                Vector165(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 21.5.dp,
                            y = 187.5.dp
                        )
                    )
                )
            }
        }
    }
}

@Preview(widthDp = 428, heightDp = 926)
@Composable
private fun Moodtrack11Preview() {
    MaterialTheme {
        RelayContainer {
            Moodtrack11(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
        }
    }
}

@Composable
fun TarjetaMoodtrack(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_tarjeta_moodtrack),
        modifier = modifier.requiredWidth(428.0.dp).requiredHeight(502.0.dp)
    )
}

@Composable
fun Perfil(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_perfil),
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
        vector = painterResource(R.drawable.moodtrack_1_1_moodtrack),
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
        vector = painterResource(R.drawable.moodtrack_1_1_jardin),
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
        vector = painterResource(R.drawable.moodtrack_1_1_linea_destacada),
        modifier = modifier.requiredWidth(12.0.dp).requiredHeight(0.0.dp)
    )
}

@Composable
fun Home(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_home),
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
fun BotNCalendario(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_bot_n_calendario),
        modifier = modifier.requiredWidth(348.0.dp).requiredHeight(45.0.dp)
    )
}

@Composable
fun Class2024(modifier: Modifier = Modifier) {
    RelayText(
        content = "2024",
        fontSize = 20.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 255,
            green = 255,
            blue = 255
        ),
        height = 1.2350000381469726.em,
        maxLines = -1,
        modifier = modifier.requiredWidth(155.0.dp).requiredHeight(26.0.dp)
    )
}

@Composable
fun CambioMesIzquierda(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_cambio_mes_izquierda),
        modifier = modifier.requiredWidth(10.0.dp).requiredHeight(20.0.dp)
    )
}

@Composable
fun CambioMesDerecha(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_cambio_mes_derecha),
        modifier = modifier.requiredWidth(10.0.dp).requiredHeight(20.0.dp)
    )
}

@Composable
fun Rectangle45(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_rectangle_45),
        modifier = modifier.requiredWidth(65.1076889038086.dp).requiredHeight(140.0.dp)
    )
}

@Composable
fun Class20(modifier: Modifier = Modifier) {
    RelayText(
        content = "20",
        fontSize = 24.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2349999745686848.em,
        maxLines = -1,
        modifier = modifier.requiredWidth(38.68717956542969.dp).requiredHeight(43.0.dp)
    )
}

@Composable
fun SUN(modifier: Modifier = Modifier) {
    RelayText(
        content = "SUN",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        maxLines = -1,
        modifier = modifier.requiredWidth(38.68717956542969.dp).requiredHeight(27.0.dp)
    )
}

@Composable
fun Group64(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(38.68717956542969.dp).requiredHeight(57.0.dp)
    )
}

@Composable
fun Rectangle46(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_rectangle_46),
        modifier = modifier.requiredWidth(65.1076889038086.dp).requiredHeight(140.0.dp)
    )
}

@Composable
fun Rectangle48(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_rectangle_48),
        modifier = modifier.requiredWidth(65.1076889038086.dp).requiredHeight(140.0.dp)
    )
}

@Composable
fun Class24(modifier: Modifier = Modifier) {
    RelayText(
        content = "24",
        fontSize = 24.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2349999745686848.em,
        maxLines = -1,
        modifier = modifier.requiredWidth(38.68717956542969.dp).requiredHeight(43.0.dp)
    )
}

@Composable
fun THU(modifier: Modifier = Modifier) {
    RelayText(
        content = "THU",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        maxLines = -1,
        modifier = modifier.requiredWidth(44.90185546875.dp).requiredHeight(27.0.dp)
    )
}

@Composable
fun Group72(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(44.90185546875.dp).requiredHeight(57.0.dp)
    )
}

@Composable
fun Group69(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        radius = 50.0,
        content = content,
        modifier = modifier.requiredWidth(65.1076889038086.dp).requiredHeight(140.0.dp)
    )
}

@Composable
fun Rectangle47(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_rectangle_47),
        modifier = modifier.requiredWidth(65.1076889038086.dp).requiredHeight(140.0.dp)
    )
}

@Composable
fun Class22(modifier: Modifier = Modifier) {
    RelayText(
        content = "22",
        fontSize = 24.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2349999745686848.em,
        maxLines = -1,
        modifier = modifier.requiredWidth(38.68717956542969.dp).requiredHeight(43.0.dp)
    )
}

@Composable
fun TUE(modifier: Modifier = Modifier) {
    RelayText(
        content = "TUE",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        maxLines = -1,
        modifier = modifier.requiredWidth(38.68717956542969.dp).requiredHeight(27.0.dp)
    )
}

@Composable
fun Group70(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(38.68717956542969.dp).requiredHeight(57.0.dp)
    )
}

@Composable
fun Rectangle49(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_rectangle_49),
        modifier = modifier.requiredWidth(65.1076889038086.dp).requiredHeight(140.0.dp)
    )
}

@Composable
fun Class23(modifier: Modifier = Modifier) {
    RelayText(
        content = "23",
        fontSize = 24.0.sp,
        fontFamily = aventaPersonalUseOnly,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2100000381469727.em,
        fontWeight = FontWeight(300.0.toInt()),
        maxLines = -1,
        modifier = modifier.requiredWidth(38.68717956542969.dp).requiredHeight(43.0.dp)
    )
}

@Composable
fun WED(modifier: Modifier = Modifier) {
    RelayText(
        content = "WED",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        maxLines = -1,
        modifier = modifier.requiredWidth(38.68717956542969.dp).requiredHeight(27.0.dp)
    )
}

@Composable
fun Group73(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(38.68717956542969.dp).requiredHeight(57.0.dp)
    )
}

@Composable
fun Group71(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        radius = 50.0,
        content = content,
        modifier = modifier.requiredWidth(65.1076889038086.dp).requiredHeight(140.0.dp)
    )
}

@Composable
fun Group67(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        radius = 50.0,
        content = content,
        modifier = modifier.requiredWidth(217.025634765625.dp).requiredHeight(140.0.dp)
    )
}

@Composable
fun Class21(modifier: Modifier = Modifier) {
    RelayText(
        content = "21",
        fontSize = 24.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2349999745686848.em,
        modifier = modifier
    )
}

@Composable
fun MON(modifier: Modifier = Modifier) {
    RelayText(
        content = "MON",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Group68(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(39.0.dp).requiredHeight(50.0.dp)
    )
}

@Composable
fun Group65(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        radius = 50.0,
        content = content,
        modifier = modifier.requiredWidth(291.5692443847656.dp).requiredHeight(140.0.dp)
    )
}

@Composable
fun Group66(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        radius = 50.0,
        content = content,
        modifier = modifier.requiredWidth(367.9993896484375.dp).requiredHeight(140.0.dp)
    )
}

@Composable
fun Ellipse10(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_ellipse_10),
        modifier = modifier.requiredWidth(43.753204345703125.dp).requiredHeight(45.47576904296875.dp)
    )
}

@Composable
fun Vector74(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_vector_74),
        modifier = modifier.requiredWidth(21.875873565673828.dp).requiredHeight(0.0.dp)
    )
}

@Composable
fun Ellipse13(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_ellipse_13),
        modifier = modifier.requiredWidth(21.875873565673828.dp).requiredHeight(9.094852447509766.dp)
    )
}

@Composable
fun Ellipse11(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_ellipse_11),
        modifier = modifier.requiredWidth(4.374982833862305.dp).requiredHeight(4.547226428985596.dp)
    )
}

@Composable
fun Ellipse12(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_ellipse_12),
        modifier = modifier.requiredWidth(4.374982833862305.dp).requiredHeight(4.547226428985596.dp)
    )
}

@Composable
fun Excited(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(43.753204345703125.dp).requiredHeight(45.47576904296875.dp)
    )
}

@Composable
fun Ellipse14(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_ellipse_14),
        modifier = modifier.requiredWidth(43.753204345703125.dp).requiredHeight(45.47576904296875.dp)
    )
}

@Composable
fun Vector75(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_vector_75),
        modifier = modifier.requiredWidth(21.875873565673828.dp).requiredHeight(0.0.dp)
    )
}

@Composable
fun Ellipse15(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_ellipse_15),
        modifier = modifier.requiredWidth(4.374982833862305.dp).requiredHeight(4.547226428985596.dp)
    )
}

@Composable
fun Ellipse16(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_ellipse_16),
        modifier = modifier.requiredWidth(4.374982833862305.dp).requiredHeight(4.547226428985596.dp)
    )
}

@Composable
fun Excited1(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(43.753204345703125.dp).requiredHeight(45.47576904296875.dp)
    )
}

@Composable
fun (modifier: Modifier = Modifier) {
    RelayText(
        content = "+",
        fontSize = 32.0.sp,
        fontFamily = kumbhSans,
        color = Color(
            alpha = 255,
            red = 52,
            green = 208,
            blue = 142
        ),
        height = 1.240234375.em,
        fontWeight = FontWeight(600.0.toInt()),
        maxLines = -1,
        modifier = modifier.requiredWidth(38.0.dp).requiredHeight(28.0.dp)
    )
}

@Composable
fun Class1(modifier: Modifier = Modifier) {
    RelayText(
        content = "+",
        fontSize = 32.0.sp,
        fontFamily = kumbhSans,
        color = Color(
            alpha = 255,
            red = 52,
            green = 208,
            blue = 142
        ),
        height = 1.240234375.em,
        fontWeight = FontWeight(600.0.toInt()),
        maxLines = -1,
        modifier = modifier.requiredWidth(38.0.dp).requiredHeight(28.0.dp)
    )
}

@Composable
fun Class2(modifier: Modifier = Modifier) {
    RelayText(
        content = "+",
        fontSize = 32.0.sp,
        fontFamily = kumbhSans,
        color = Color(
            alpha = 255,
            red = 52,
            green = 208,
            blue = 142
        ),
        height = 1.240234375.em,
        fontWeight = FontWeight(600.0.toInt()),
        maxLines = -1,
        modifier = modifier.requiredWidth(38.0.dp).requiredHeight(28.0.dp)
    )
}

@Composable
fun MenuMoodtrackBeans(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(367.9993896484375.dp).requiredHeight(140.0.dp)
    )
}

@Composable
fun FlechaDetails(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_flecha_details),
        modifier = modifier.requiredWidth(18.0.dp).requiredHeight(9.0.dp)
    )
}

@Composable
fun Details(modifier: Modifier = Modifier) {
    RelayText(
        content = "Details\n",
        fontSize = 18.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 52,
            green = 208,
            blue = 142
        ),
        height = 1.2349999745686848.em,
        textAlign = TextAlign.Left,
        maxLines = -1,
        modifier = modifier.requiredWidth(62.0.dp).requiredHeight(22.0.dp)
    )
}

@Composable
fun MyWeek(modifier: Modifier = Modifier) {
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
                    fontSize = 24.0.sp,
                    fontWeight = FontWeight(400.0.toInt())
                )
            ) {
                append("My")
            }
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
                append(" Week")
            }
        },
        fontSize = 24.0.sp,
        fontFamily = kumbhSans,
        color = Color(
            alpha = 255,
            red = 52,
            green = 208,
            blue = 142
        ),
        height = 1.240234375.em,
        textAlign = TextAlign.Left,
        fontWeight = FontWeight(600.0.toInt()),
        maxLines = -1,
        modifier = modifier.requiredWidth(188.54220581054688.dp).requiredHeight(54.8470573425293.dp)
    )
}

@Composable
fun Class24Jan2024(modifier: Modifier = Modifier) {
    RelayText(
        content = "24 Jan 2024",
        fontSize = 12.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2349999745686848.em,
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}

@Composable
fun Barra(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_barra),
        modifier = modifier.requiredWidth(100.0.dp).requiredHeight(0.0.dp)
    )
}

@Composable
fun Rectangle35(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_rectangle_35),
        modifier = modifier.requiredWidth(348.0.dp).requiredHeight(297.0.dp)
    )
}

@Composable
fun JANUARY(modifier: Modifier = Modifier) {
    RelayText(
        content = "JANUARY",
        fontSize = 18.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2349999745686848.em,
        modifier = modifier
    )
}

@Composable
fun M(modifier: Modifier = Modifier) {
    RelayText(
        content = "M",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 118,
            green = 207,
            blue = 165
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun S(modifier: Modifier = Modifier) {
    RelayText(
        content = "S",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 118,
            green = 207,
            blue = 165
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun S1(modifier: Modifier = Modifier) {
    RelayText(
        content = "S",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 118,
            green = 207,
            blue = 165
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun T(modifier: Modifier = Modifier) {
    RelayText(
        content = "T",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 118,
            green = 207,
            blue = 165
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun F(modifier: Modifier = Modifier) {
    RelayText(
        content = "F",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 118,
            green = 207,
            blue = 165
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun T1(modifier: Modifier = Modifier) {
    RelayText(
        content = "T",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 118,
            green = 207,
            blue = 165
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun W(modifier: Modifier = Modifier) {
    RelayText(
        content = "W ",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 118,
            green = 207,
            blue = 165
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Group74(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(302.0.dp).requiredHeight(20.0.dp)
    )
}

@Composable
fun Class1(modifier: Modifier = Modifier) {
    RelayText(
        content = "1",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class2(modifier: Modifier = Modifier) {
    RelayText(
        content = "2",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class3(modifier: Modifier = Modifier) {
    RelayText(
        content = "3",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class4(modifier: Modifier = Modifier) {
    RelayText(
        content = "4",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class5(modifier: Modifier = Modifier) {
    RelayText(
        content = "5",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class6(modifier: Modifier = Modifier) {
    RelayText(
        content = "6",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Frame67(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        mainAxisAlignment = MainAxisAlignment.Start,
        arrangement = RelayContainerArrangement.Row,
        itemSpacing = 38.0,
        clipToParent = false,
        content = content,
        modifier = modifier.height(IntrinsicSize.Min)
    )
}

@Composable
fun Class7(modifier: Modifier = Modifier) {
    RelayText(
        content = "7",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class8(modifier: Modifier = Modifier) {
    RelayText(
        content = "8",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class9(modifier: Modifier = Modifier) {
    RelayText(
        content = "9",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class10(modifier: Modifier = Modifier) {
    RelayText(
        content = "10",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class11(modifier: Modifier = Modifier) {
    RelayText(
        content = "11",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class12(modifier: Modifier = Modifier) {
    RelayText(
        content = "12",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class13(modifier: Modifier = Modifier) {
    RelayText(
        content = "13",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Frame1(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.fillMaxWidth(1.0f).requiredHeight(20.0.dp)
    )
}

@Composable
fun Frame68(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        mainAxisAlignment = MainAxisAlignment.Start,
        crossAxisAlignment = CrossAxisAlignment.Start,
        itemSpacing = 10.0,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(295.0.dp)
    )
}

@Composable
fun Class14(modifier: Modifier = Modifier) {
    RelayText(
        content = "14",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class15(modifier: Modifier = Modifier) {
    RelayText(
        content = "15",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class16(modifier: Modifier = Modifier) {
    RelayText(
        content = "16",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class17(modifier: Modifier = Modifier) {
    RelayText(
        content = "17",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class18(modifier: Modifier = Modifier) {
    RelayText(
        content = "18",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class19(modifier: Modifier = Modifier) {
    RelayText(
        content = "19",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class201(modifier: Modifier = Modifier) {
    RelayText(
        content = "20",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Frame69(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(308.0.dp).requiredHeight(20.0.dp)
    )
}

@Composable
fun Group75(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(308.0.dp).requiredHeight(20.0.dp)
    )
}

@Composable
fun Class211(modifier: Modifier = Modifier) {
    RelayText(
        content = "21",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class221(modifier: Modifier = Modifier) {
    RelayText(
        content = "22",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class231(modifier: Modifier = Modifier) {
    RelayText(
        content = "23",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Ellipse22(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_ellipse_22),
        modifier = modifier.requiredWidth(27.0.dp).requiredHeight(27.0.dp)
    )
}

@Composable
fun Class241(modifier: Modifier = Modifier) {
    RelayText(
        content = "24",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 255,
            green = 255,
            blue = 255
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Group50(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(27.0.dp).requiredHeight(27.0.dp)
    )
}

@Composable
fun Class25(modifier: Modifier = Modifier) {
    RelayText(
        content = "25",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class26(modifier: Modifier = Modifier) {
    RelayText(
        content = "26",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class27(modifier: Modifier = Modifier) {
    RelayText(
        content = "27",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Frame70(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(304.0.dp).requiredHeight(27.0.dp)
    )
}

@Composable
fun Vector160(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_vector_160),
        modifier = modifier.requiredWidth(21.0.dp).requiredHeight(0.0.dp)
    )
}

@Composable
fun Vector161(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_vector_161),
        modifier = modifier.requiredWidth(21.0.dp).requiredHeight(0.0.dp)
    )
}

@Composable
fun Class28(modifier: Modifier = Modifier) {
    RelayText(
        content = "28",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class29(modifier: Modifier = Modifier) {
    RelayText(
        content = "29",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class30(modifier: Modifier = Modifier) {
    RelayText(
        content = "30",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Class31(modifier: Modifier = Modifier) {
    RelayText(
        content = "31",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000143051147.em,
        modifier = modifier
    )
}

@Composable
fun Frame71(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(161.0.dp).requiredHeight(20.0.dp)
    )
}

@Composable
fun Group76(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(161.0.dp).requiredHeight(20.0.dp)
    )
}

@Composable
fun Vector162(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_vector_162),
        modifier = modifier.requiredWidth(21.0.dp).requiredHeight(0.0.dp)
    )
}

@Composable
fun Vector163(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_vector_163),
        modifier = modifier.requiredWidth(21.0.dp).requiredHeight(0.0.dp)
    )
}

@Composable
fun Vector164(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_vector_164),
        modifier = modifier.requiredWidth(21.0.dp).requiredHeight(0.0.dp)
    )
}

@Composable
fun Vector165(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_1_vector_165),
        modifier = modifier.requiredWidth(21.0.dp).requiredHeight(0.0.dp)
    )
}

@Composable
fun Group32(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(348.0.dp).requiredHeight(297.0.dp)
    )
}

@Composable
fun Calendario(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        isStructured = false,
        clipToParent = false,
        content = content,
        modifier = modifier.requiredWidth(348.0.dp).requiredHeight(297.0.dp)
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
