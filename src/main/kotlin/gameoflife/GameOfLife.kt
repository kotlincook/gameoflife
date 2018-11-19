package gameoflife

data class Cell(val x: Int, val y: Int)

data class Board(val cells: Set<Cell>) {

    /**
     * Converts the board given in a String representation into the
     * internal format. This is used in the tests.
     */
    constructor(matrix: String) : this(
            matrix.split(Regex("\\s+")).mapIndexed {
                y, line -> line.mapIndexedNotNull {
                    x, char -> if (char == '*') Cell(x, y) else null
                }
            }.flatMap { it }.toSet())

    /**
     * Returns the number of cells in the square neighbourhood at position (x,y)
     */
    fun noOfNeighboursAt(x: Int, y: Int): Int =
            cells.filter {
                (cx, cy) ->
                    Math.abs(cx - x) <= 1 &&
                    Math.abs(cy - y) <= 1 &&
                    !(cx == x && cy == y)
            }.size

    /**
     * Returns the cell at position (x,y) if it exists, null else
     */
    fun cellAt(x: Int, y: Int): Cell? =
            cells.find { it.x == x && it.y == y }

    /**
     * New cells can only be spawned in a rectangle that is one larger (on each side)
     * than the bounding box.
     */
    fun extendedHorizontalCellRange(): IntRange =
            -1 + (cells.map { it.x }.min() ?: 0) .. (cells.map { it.x }.max() ?: 0) + 1

    /**
     * @see extendedHorizontalCellRange
     */
    fun extendedVerticalCellRange(): IntRange =
            -1 + (cells.map { it.y }.min() ?: 0) .. (cells.map { it.y }.max() ?: 0) + 1
}


/**
 * Calculates the next cell generation for the entire board
 */
fun Board.evolve(): Board {

    /**
     * Determines whether there will be a cell at position (x,y) in
     * the next generation
     */
    fun evolveAt(x: Int, y: Int): Cell? {
        if (cellAt(x, y) == null) {
            return Cell(x, y).takeIf { noOfNeighboursAt(x, y) == 3 }
        }
        return Cell(x, y).takeIf { noOfNeighboursAt(x, y) in 2..3 }
    }

    // calculating the cross product of the two ranges:
    val allBoardCoords = extendedHorizontalCellRange().flatMap {
        x -> extendedVerticalCellRange().map { y -> x to y }
    }

    val newCells = allBoardCoords.mapNotNull {
        (x, y) -> evolveAt(x, y)
    }.toSet()

    return Board(newCells)
}
