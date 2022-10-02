package com.example.timetvr.ui.theme

import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.timetvr.R


val Montserrat = FontFamily(
    Font(R.font.montserrat_extrabold)
)
// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        color = Color.White,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = Montserrat,
        color = Color.White,
        fontSize = 40.sp
    ),
    h2 = TextStyle(
        fontFamily = Montserrat,
        color = Color.White,
        fontSize = 30.sp
    ),
    h3 = TextStyle(
        fontFamily = Montserrat,
        color = Color.White,
        fontSize = 18.sp
    ),
    h4 = TextStyle(
        fontFamily = Montserrat,
        color = Color.Black,
        fontSize = 40.sp
    ),
    h5 = TextStyle(
        fontFamily = Montserrat,
        color = Color.Black,
        fontSize = 30.sp
    ),
    h6 = TextStyle(
        fontFamily = Montserrat,
        color = Color.Black,
        fontSize = 18.sp
    )

    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)