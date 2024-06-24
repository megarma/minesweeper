package fr.arma.minesweeper.domain

data class Cell(
    val row: Int,
    val col: Int,
    var hasMine: Boolean,
    var isFlagged: Boolean = false,
    var isRevealed: Boolean = false,
    var adjacentMines: Int = 0
) {
    val isOpened: Boolean
        get() = isRevealed || isFlagged
}