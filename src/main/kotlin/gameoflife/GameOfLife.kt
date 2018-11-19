package gameoflife

data class Cell(val x: Int, val y: Int)

data class Board(val cells: Set<Cell>) {

    constructor(matrix: String) : this(
            matrix.split(Regex("\\s+")).mapIndexed {
                y, line -> line.mapIndexedNotNull {
                    x, char -> if (char == '*') Cell(x, y) else null
                }
            }.flatMap { it }.toSet())


    private fun extHorizontalCellRange(): IntRange =
            -1 + (cells.map { it.x }.min() ?: 0) .. (cells.map { it.x }.max() ?: 0) + 1

    private fun extVerticalCellRange(): IntRange =
            -1 + (cells.map { it.y }.min() ?: 0) .. (cells.map { it.y }.max() ?: 0) + 1

    fun noOfNeighboursAround(x: Int, y: Int) =
            cells.filter { (cx, cy) ->
                Math.abs(cx - x) <= 1 &&
                Math.abs(cy - y) <= 1 &&
                !(cx == x && cy == y)
            }.size


    fun cellAt(x: Int, y: Int): Cell? =
            cells.find { it.x == x && it.y == y }


    fun evolve(): Board {

        fun evolveAt(x: Int, y: Int): Cell? {
            if (cellAt(x, y) == null) {
                return Cell(x, y).takeIf { noOfNeighboursAround(x, y) == 3 }
            }
            return Cell(x, y).takeIf { noOfNeighboursAround(x, y) in 2..3 }
        }

        val allBoardCoords = extHorizontalCellRange().flatMap {
            x -> extVerticalCellRange().map { y -> x to y }
        }

        val newCells = allBoardCoords.mapNotNull {
            (x, y) -> evolveAt(x, y)
        }.toSet()

        return Board(newCells)
    }
}
