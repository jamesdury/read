package com.jamesdury.read

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jamesdury.read.ui.theme.ReadTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ReadTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ){
                        Heading(
                            getString(R.string.app_name)
                        )
                        Intro(
                            getString(R.string.app_intro)
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun Heading(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h1,
    )
}

@Composable
fun Intro(text: String) {
    Text(
        text = text
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ReadTheme {
        Intro("Heading")
        Intro("Android")
    }
}
