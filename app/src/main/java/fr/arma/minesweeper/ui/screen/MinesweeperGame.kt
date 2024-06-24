package fr.arma.minesweeper.ui.screen

import fr.arma.minesweeper.domain.Cell
import fr.arma.minesweeper.domain.MineGenerator

class MinesweeperGame(
    private val size: Int,
    mineCount: Int,
    mineGenerator: MineGenerator
) {
    val cells: MutableList<MutableList<Cell>>
    private val mines: Set<Pair<Int, Int>> = mineGenerator.generateMines(size, mineCount)
    var score = 0
        private set
    private var gameOver = false

    init {
        cells = MutableList(size) { row ->
            MutableList(size) { col ->
                Cell(row, col, mines.contains(Pair(row, col)))
            }
        }
        calculateAdjacentMines()
    }

    private fun calculateAdjacentMines() {
        for (row in 0 until size) {
            for (col in 0 until size) {
                if (!cells[row][col].hasMine) {
                    cells[row][col].adjacentMines = countAdjacentMines(row, col)
                }
            }
        }
    }

    private fun countAdjacentMines(row: Int, col: Int): Int {
        var count = 0
        for (r in (row - 1)..(row + 1)) {
            for (c in (col - 1)..(col + 1)) {
                if (r in 0 until size && c in 0 until size && cells[r][c].hasMine) {
                    count++
                }
            }
        }
        return count
    }

    fun revealCell(row: Int, col: Int) {
        if (gameOver) return

        if (row in 0 until size && col in 0 until size && !cells[row][col].isOpened) {
            cells[row][col].isRevealed = true
            if (cells[row][col].hasMine) {
                gameOver = true
                revealAllBombs()
            } else {
                score += 6
                if (cells[row][col].adjacentMines == 0) {
                    for (r in (row - 1)..(row + 1)) {
                        for (c in (col - 1)..(col + 1)) {
                            if (r in 0 until size && c in 0 until size && !cells[r][c].isOpened) {
                                revealCell(r, c)
                            }
                        }
                    }
                }
            }
        }
    }

    fun toggleFlag(row: Int, col: Int) {
        if (gameOver) return

        if (row in 0 until size && col in 0 until size && !cells[row][col].isRevealed) {
            if (cells[row][col].hasMine) {
                score += 10
            } else {
                score -= 6
            }

            cells[row][col].isFlagged = !cells[row][col].isFlagged
        }
    }

    fun resetGame() {
        score = 0
        gameOver = false
        cells.forEach { row ->
            row.forEach { cell ->
                cell.isRevealed = false
                cell.isFlagged = false
                cell.hasMine = mines.contains(Pair(cell.row, cell.col))
            }
        }
        calculateAdjacentMines()
    }

    private fun revealAllBombs() {
        cells.forEach { row ->
            row.forEach { cell ->
                if (cell.hasMine) {
                    cell.isRevealed = true
                }
            }
        }
    }
}