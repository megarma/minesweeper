package fr.arma.minesweeper.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.arma.minesweeper.domain.Cell
import fr.arma.minesweeper.domain.MineGenerator

@Composable
fun AppScreen(modifier: Modifier = Modifier) {
    val game = remember { MinesweeperGame(size = 8, mineCount = 10, MineGenerator) }
    val cells = remember { mutableStateListOf<MutableList<Cell>>() }

    cells.clear()
    cells.addAll(game.cells)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(Modifier.height(30.dp))

        Text(
            text = "Minesweeper",
            style = TextStyle(
                fontSize = 35.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Score: ${game.score}")
            TextButton(onClick = { game.resetGame() }) {
                Text("Restart", color = Color.Black)
            }
        }

        Spacer(Modifier.height(30.dp))

        for (row in cells) {
            Row {
                for (cell in row) {
                    CellView(cell = cell, onClick = {
                        game.revealCell(cell.row, cell.col)
                        cells.clear()
                        cells.addAll(game.cells)
                    }, onLongClick = {
                        game.toggleFlag(cell.row, cell.col)
                        cells.clear()
                        cells.addAll(game.cells)
                    })
                }
            }
        }
    }
}