package fr.arma.minesweeper.domain

object MineGenerator {
    fun generateMines(size: Int, mineCount: Int): Set<Pair<Int, Int>> {
        val mines = mutableSetOf<Pair<Int, Int>>()
        while (mines.size < mineCount) {
            val row = (0 until size).random()
            val col = (0 until size).random()
            mines.add(Pair(row, col))
        }
        return mines
    }
}