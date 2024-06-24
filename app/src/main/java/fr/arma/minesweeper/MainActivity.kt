package fr.arma.minesweeper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import fr.arma.minesweeper.ui.screen.AppScreen
import fr.arma.minesweeper.ui.theme.MinesweeperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MinesweeperTheme {
                AppScreen(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                        .background(color = Color.White)
                )
            }
        }
    }
}