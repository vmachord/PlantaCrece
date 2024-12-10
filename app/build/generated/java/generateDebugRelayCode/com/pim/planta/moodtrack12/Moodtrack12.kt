package com.pim.planta.moodtrack12

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
 * despliegue de vista semanal en calendario
 *
 * This composable was generated from the UI Package 'moodtrack_1_2'.
 * Generated code; do not edit directly
 */
@Composable
fun Moodtrack12(modifier: Modifier = Modifier) {
    TopLevel(modifier = modifier) {
        Rectangle47(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 0.0.dp,
                    y = 50.0.dp
                )
            )
        )
        MyWeek(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 29.0.dp,
                    y = 140.0.dp
                )
            )
        )
        (
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 343.0.dp,
                    y = 580.0.dp
                )
            )
        )
        Barra(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopCenter,
                offset = DpOffset(
                    x = -1.5.dp,
                    y = 87.5.dp
                )
            )
        )
        Details(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 29.0.dp,
                    y = 389.0.dp
                )
            )
        )
        HighlightOfTheDay(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 60.0.dp,
                    y = 445.0.dp
                )
            )
        )
        TellUsMore(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 60.0.dp,
                    y = 566.0.dp
                )
            )
        )
        Vector158(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 100.5.dp,
                    y = 393.5.dp
                )
            )
        )
        Contenedor(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 29.0.dp,
                    y = 479.0.dp
                )
            )
        ) {
            Text()
        }
        Contenedor1(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 29.0.dp,
                    y = 601.0.dp
                )
            )
        ) {
            Text1()
        }
        Ellipse30(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 38.0.dp,
                    y = 447.0.dp
                )
            )
        )
        Ellipse31(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 38.0.dp,
                    y = 566.0.dp
                )
            )
        )
        Menu(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 57.0.dp,
                    y = 850.0.dp
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
        MenuMoodtrackBeans(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 30.0.dp,
                    y = 199.0.dp
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
                Group65(
                    modifier = Modifier.boxAlign(
                        alignment = Alignment.TopStart,
                        offset = DpOffset(
                            x = 76.43017578125.dp,
                            y = 0.0.dp
                        )
                    )
                ) {
                    Rectangle46()
                    Group67(
                        modifier = Modifier.boxAlign(
                            alignment = Alignment.TopStart,
                            offset = DpOffset(
                                x = 74.54345703125.dp,
                                y = 0.0.dp
                            )
                        )
                    ) {
                        Group69(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 151.9180908203125.dp,
                                    y = 0.0.dp
                                )
                            )
                        ) {
                            Rectangle49()
                            Group72(
                                modifier = Modifier.boxAlign(
                                    alignment = Alignment.TopStart,
                                    offset = DpOffset(
                                        x = 10.44482421875.dp,
                                        y = 63.0.dp
                                    )
                                )
                            ) {
                                Class26(
                                    modifier = Modifier.boxAlign(
                                        alignment = Alignment.TopStart,
                                        offset = DpOffset(
                                            x = 2.7664794921875.dp,
                                            y = 0.0.dp
                                        )
                                    )
                                )
                                SAT(
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
                        Rectangle48()
                        Group70(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 13.21026611328125.dp,
                                    y = 63.0.dp
                                )
                            )
                        ) {
                            Class24()
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
                        Group71(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 74.54248046875.dp,
                                    y = 0.0.dp
                                )
                            )
                        ) {
                            Rectangle50()
                            Group73(
                                modifier = Modifier.boxAlign(
                                    alignment = Alignment.TopStart,
                                    offset = DpOffset(
                                        x = 13.21124267578125.dp,
                                        y = 63.0.dp
                                    )
                                )
                            ) {
                                Class25()
                                FRI(
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
                                x = 13.21124267578125.dp,
                                y = 63.0.dp
                            )
                        )
                    ) {
                        Class23()
                        WED(
                            modifier = Modifier.boxAlign(
                                alignment = Alignment.TopStart,
                                offset = DpOffset(
                                    x = 0.359375.dp,
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
            Class1(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 239.0.dp,
                        y = 13.0.dp
                    )
                )
            )
            Class2(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 14.0.dp,
                        y = 8.0.dp
                    )
                )
            )
            Class3(
                modifier = Modifier.boxAlign(
                    alignment = Alignment.TopStart,
                    offset = DpOffset(
                        x = 319.0.dp,
                        y = 12.0.dp
                    )
                )
            )
        }
        Class24Jan2024(
            modifier = Modifier.boxAlign(
                alignment = Alignment.TopStart,
                offset = DpOffset(
                    x = 29.0.dp,
                    y = 120.0.dp
                )
            )
        )
    }
}

@Preview(widthDp = 428, heightDp = 926)
@Composable
private fun Moodtrack12Preview() {
    MaterialTheme {
        RelayContainer {
            Moodtrack12(modifier = Modifier.rowWeight(1.0f).columnWeight(1.0f))
        }
    }
}

@Composable
fun Rectangle47(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_2_rectangle_47),
        modifier = modifier.requiredWidth(428.0.dp).requiredHeight(876.0.dp)
    )
}

@Composable
fun MyWeek(modifier: Modifier = Modifier) {
    RelayText(
        content = "My week",
        fontSize = 24.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 52,
            green = 208,
            blue = 142
        ),
        height = 1.2349999745686848.em,
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}

@Composable
fun (modifier: Modifier = Modifier) {
    RelayText(
        content = "+",
        fontSize = 24.0.sp,
        fontFamily = kumbhSans,
        color = Color(
            alpha = 255,
            red = 255,
            green = 255,
            blue = 255
        ),
        height = 1.240234375.em,
        fontWeight = FontWeight(600.0.toInt()),
        maxLines = -1,
        modifier = modifier.requiredWidth(27.0.dp).requiredHeight(42.0.dp)
    )
}

@Composable
fun Barra(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_2_barra),
        modifier = modifier.requiredWidth(100.0.dp).requiredHeight(0.0.dp)
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
fun HighlightOfTheDay(modifier: Modifier = Modifier) {
    RelayText(
        content = "Highlight of the day",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 21,
            green = 129,
            blue = 78
        ),
        height = 1.2350000143051147.em,
        textAlign = TextAlign.Left,
        maxLines = -1,
        modifier = modifier.requiredWidth(171.0.dp).requiredHeight(22.0.dp)
    )
}

@Composable
fun TellUsMore(modifier: Modifier = Modifier) {
    RelayText(
        content = "Tell us more",
        fontSize = 16.0.sp,
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 21,
            green = 129,
            blue = 78
        ),
        height = 1.2350000143051147.em,
        textAlign = TextAlign.Left,
        maxLines = -1,
        modifier = modifier.requiredWidth(171.0.dp).requiredHeight(22.0.dp)
    )
}

@Composable
fun Vector158(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_2_vector_158),
        modifier = modifier.requiredWidth(18.0.dp).requiredHeight(9.0.dp)
    )
}

@Composable
fun Text(modifier: Modifier = Modifier) {
    RelayText(
        content = "Lorem ipsum dolor sit amet, consectetur \nadipiscing elit.",
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000653948103.em,
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}

@Composable
fun Contenedor(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        backgroundColor = Color(
            alpha = 255,
            red = 231,
            green = 241,
            blue = 237
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
        content = "Duis aute irure dolor in reprehenderit in voluptate\n velit esse cillum dolore eu fugiat nulla pariatur. \nExcepteur sint occaecat cupidatat non proident, \nsunt in culpa qui officia deserunt mollit anim id est \nlaborum.\n\nLorem ipsum dolor sit amet, consectetur adipiscing\n elit, sed do eiusmod tempor incididunt ut labore et\n dolore magna aliqua.",
        fontFamily = aventa,
        color = Color(
            alpha = 255,
            red = 7,
            green = 62,
            blue = 36
        ),
        height = 1.2350000653948103.em,
        textAlign = TextAlign.Left,
        modifier = modifier
    )
}

@Composable
fun Contenedor1(
    modifier: Modifier = Modifier,
    content: @Composable RelayContainerScope.() -> Unit
) {
    RelayContainer(
        backgroundColor = Color(
            alpha = 255,
            red = 231,
            green = 241,
            blue = 237
        ),
        mainAxisAlignment = MainAxisAlignment.Start,
        crossAxisAlignment = CrossAxisAlignment.Start,
        arrangement = RelayContainerArrangement.Row,
        padding = PaddingValues(all = 16.0.dp),
        itemSpacing = 10.0,
        clipToParent = false,
        radius = 10.0,
        content = content,
        modifier = modifier.requiredWidth(370.0.dp).requiredHeight(209.0.dp)
    )
}

@Composable
fun Ellipse30(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_2_ellipse_30),
        modifier = modifier.requiredWidth(14.0.dp).requiredHeight(14.0.dp)
    )
}

@Composable
fun Ellipse31(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_2_ellipse_31),
        modifier = modifier.requiredWidth(14.0.dp).requiredHeight(14.0.dp)
    )
}

@Composable
fun Perfil(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_2_perfil),
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
        vector = painterResource(R.drawable.moodtrack_1_2_moodtrack),
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
        vector = painterResource(R.drawable.moodtrack_1_2_jardin),
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
        vector = painterResource(R.drawable.moodtrack_1_2_linea_destacada),
        modifier = modifier.requiredWidth(12.0.dp).requiredHeight(0.0.dp)
    )
}

@Composable
fun Home(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_2_home),
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
fun Rectangle45(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_2_rectangle_45),
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
        vector = painterResource(R.drawable.moodtrack_1_2_rectangle_46),
        modifier = modifier.requiredWidth(65.1076889038086.dp).requiredHeight(140.0.dp)
    )
}

@Composable
fun Rectangle49(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_2_rectangle_49),
        modifier = modifier.requiredWidth(65.1076889038086.dp).requiredHeight(140.0.dp)
    )
}

@Composable
fun Class26(modifier: Modifier = Modifier) {
    RelayText(
        content = "26",
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
fun SAT(modifier: Modifier = Modifier) {
    RelayText(
        content = "SAT",
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
fun Rectangle48(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_2_rectangle_48),
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
fun Rectangle50(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_2_rectangle_50),
        modifier = modifier.requiredWidth(65.1076889038086.dp).requiredHeight(140.0.dp)
    )
}

@Composable
fun Class25(modifier: Modifier = Modifier) {
    RelayText(
        content = "25",
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
fun FRI(modifier: Modifier = Modifier) {
    RelayText(
        content = "FRI",
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
fun Class23(modifier: Modifier = Modifier) {
    RelayText(
        content = "23",
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
        modifier = modifier.requiredWidth(42.0.dp).requiredHeight(27.0.dp)
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
        modifier = modifier.requiredWidth(42.359375.dp).requiredHeight(57.0.dp)
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
        vector = painterResource(R.drawable.moodtrack_1_2_ellipse_10),
        modifier = modifier.requiredWidth(43.753204345703125.dp).requiredHeight(45.47576904296875.dp)
    )
}

@Composable
fun Vector74(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_2_vector_74),
        modifier = modifier.requiredWidth(21.875873565673828.dp).requiredHeight(0.0.dp)
    )
}

@Composable
fun Ellipse13(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_2_ellipse_13),
        modifier = modifier.requiredWidth(21.875873565673828.dp).requiredHeight(9.094852447509766.dp)
    )
}

@Composable
fun Ellipse11(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_2_ellipse_11),
        modifier = modifier.requiredWidth(4.374982833862305.dp).requiredHeight(4.547226428985596.dp)
    )
}

@Composable
fun Ellipse12(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_2_ellipse_12),
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
        vector = painterResource(R.drawable.moodtrack_1_2_ellipse_14),
        modifier = modifier.requiredWidth(43.753204345703125.dp).requiredHeight(45.47576904296875.dp)
    )
}

@Composable
fun Vector75(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_2_vector_75),
        modifier = modifier.requiredWidth(21.875873565673828.dp).requiredHeight(0.0.dp)
    )
}

@Composable
fun Ellipse15(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_2_ellipse_15),
        modifier = modifier.requiredWidth(4.374982833862305.dp).requiredHeight(4.547226428985596.dp)
    )
}

@Composable
fun Ellipse16(modifier: Modifier = Modifier) {
    RelayVector(
        vector = painterResource(R.drawable.moodtrack_1_2_ellipse_16),
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
fun Class3(modifier: Modifier = Modifier) {
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
        maxLines = -1,
        modifier = modifier.requiredWidth(83.0.dp).requiredHeight(18.0.dp)
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
