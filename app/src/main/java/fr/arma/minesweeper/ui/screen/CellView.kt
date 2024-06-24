package fr.arma.minesweeper.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.arma.minesweeper.domain.Cell

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CellView(cell: Cell, onClick: () -> Unit, onLongClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .padding(1.5.dp)
            .background(
                color = when {
                    cell.isRevealed && cell.hasMine -> Color.LightGray
                    cell.isRevealed -> Color.LightGray
                    cell.isFlagged -> Color(0xFFA7A7A7)
                    else -> Color(0xFFA7A7A7)
                },
                shape = RoundedCornerShape(4.dp)
            )
            .combinedClickable(enabled = !cell.isOpened, onClick = { onClick() }, onLongClick = { onLongClick() }),
        contentAlignment = Alignment.Center
    ) {
        when {
            cell.isRevealed && cell.hasMine -> Text(
                text = "\uD83D\uDCA3",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            cell.isRevealed && cell.adjacentMines > 0 -> Text(
                text = cell.adjacentMines.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            cell.isFlagged -> Text(
                text = "🚩",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}