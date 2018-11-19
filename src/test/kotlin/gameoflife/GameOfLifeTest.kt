package gameoflife

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.StringSpec

class GameOfLifeTest : StringSpec({
    "test neighbours with empty board" {
        val board = Board("""
                |-
            """.trimMargin())
        board.noOfNeighboursAt(0, 0) shouldBe 0
    }

    "test no neighbours" {
        val board = Board("""
                |---
                |-*-
                |---
            """.trimMargin())
        board.noOfNeighboursAt(1, 1) shouldBe 0
    }

    "test three neighbours (i am present)" {
        val board = Board("""
                |---
                |**-
                |-**
            """.trimMargin())
        board.noOfNeighboursAt(1, 1) shouldBe 3
    }

    "test three neighbours (i am absent)" {
        val board = Board("""
                |---
                |*--
                |-**
            """.trimMargin())
        board.noOfNeighboursAt(1, 1) shouldBe 3
    }

    "test neighbours (are too far away)" {
        val input = """
                |---
                |**-
                |**-
            """.trimMargin()
        val board = Board(input)
        board.noOfNeighboursAt(0, -1) shouldBe 0
    }



    "test empty world" {
        Board("-").evolve() shouldBe Board("")
    }

    "test underpopulation" {
        val input = """
                |---
                |-*-
                |---
            """.trimMargin()
        val expected = """
                |---
                |---
                |---
            """.trimMargin()
        Board(input).evolve() shouldBe Board(expected)
    }

    "test survival" {
        val input = """
                |---
                |**-
                |**-
            """.trimMargin()
        val expected = """
                |---
                |**-
                |**-
            """.trimMargin()
        Board(input).evolve() shouldBe Board(expected)
    }

    "test overcrowding" {
        val input = """
                |-**-
                |-**-
                |-**-
            """.trimMargin()
        val expected = """
                |-**-
                |*--*
                |-**-
            """.trimMargin()
        Board(input).evolve() shouldBe Board(expected)
    }

    "test reproduction" {
        val input = """
                |---
                |*--
                |**-
            """.trimMargin()
        val expected = """
                |---
                |**-
                |**-
            """.trimMargin()
        Board(input).evolve() shouldBe Board(expected)
    }

    "test sample" {
        val input = """
                |-----
                |-***-
                |-***-
                |-***-
                |-----
            """.trimMargin()
        val expected = """
                |--*--
                |-*-*-
                |*---*
                |-*-*-
                |--*--
            """.trimMargin()
        Board(input).evolve() shouldBe Board(expected)
    }
})