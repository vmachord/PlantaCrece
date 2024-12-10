package com.pim.planta.inicio

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import com.pim.planta.R

val fredoka: FontFamily = FontFamily(
    Font(R.font.relay_fredoka_bold, weight = FontWeight.W700, style = FontStyle.Normal),
    Font(R.font.relay_fredoka_light, weight = FontWeight.W300, style = FontStyle.Normal),
    Font(R.font.relay_fredoka_medium, weight = FontWeight.W500, style = FontStyle.Normal),
    Font(R.font.relay_fredoka_regular, weight = FontWeight.W400, style = FontStyle.Normal),
    Font(R.font.relay_fredoka_semibold, weight = FontWeight.W600, style = FontStyle.Normal)
)
val aventa: FontFamily = FontFamily.Default