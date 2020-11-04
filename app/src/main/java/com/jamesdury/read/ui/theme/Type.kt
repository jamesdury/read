package com.jamesdury.read.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.jamesdury.read.R

val inter = FontFamily(
    Font(R.font.inter_regular, weight = FontWeight.Normal)
)
val metropolis = FontFamily(
    Font(R.font.metropolis_bold, weight = FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(

    h1 = TextStyle(
        fontFamily = metropolis,
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp
    ),
    body1 = TextStyle(
        fontFamily = inter,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    )
)
