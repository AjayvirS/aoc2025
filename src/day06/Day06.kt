package day06

import datastructures.Input

fun part1(matrix: Array<IntArray>, ops: List<String>): Long {
    var totalSum: Long = 0
    ops.forEachIndexed { index, s ->
        val colSum: Long = when (s) {
            "+" -> matrix.sumOf { it[index].toLong() }
            "*" -> matrix.fold(1L) { acc, row ->
                acc * row[index]
            }
            else -> error("Operation not supported")
        }
        totalSum += colSum
    }
    return totalSum
}

/*
123 328  51 64
 45 64  387 23
  6 98  215 314
*/

fun part2(matrix: Array<Array<String>>, ops: List<String>): Long {
    var totalSum: Long = 0

    ops.forEachIndexed { col, op ->
        val intsTransposed = parseColumnInts(matrix, col)
        val colSum: Long = when (op) {
            "+" -> intsTransposed.sum()
            "*" -> if (intsTransposed.isEmpty()) {
                0L
            } else {
                intsTransposed.fold(1L) { acc, curr -> acc * curr }
            }
            else -> error("Operation not supported")
        }
        totalSum += colSum
    }

    return totalSum
}

private fun parseColumnInts(matrix: Array<Array<String>>, col: Int): List<Long> {
    val intsBySubCol = linkedMapOf<Int, Long>()  // keeps insertion order

    matrix.forEach { line ->
        val cell = line.getOrNull(col) ?: ""
        cell.forEachIndexed { index, ch ->
            if (ch.isDigit()) {
                val existing = intsBySubCol[index] ?: 0L
                intsBySubCol[index] = existing * 10 + ch.digitToInt()
            }
        }
    }

    return intsBySubCol.values.toList()
}

// 123, 45, 6 -> 356*24*1
// 64, 23, 314 -> ...

fun main() {
    val text = Input.lines("puzzle6A.txt")
    val allLines = text.dropLast(1)
    val ops = text.last().trim().split(Regex("\\s+"))

    val matrix = allLines
        .map { line -> line.trim().split(Regex("\\s+")).map { it.toInt() }.toIntArray() }
        .toTypedArray()


    val width = allLines.maxOf { it.length }
    val padded = allLines.map { it.padEnd(width, ' ') }

    val isSeparator = BooleanArray(width) { col ->
        padded.all { row -> !row[col].isDigit() }
    }

    val ranges = mutableListOf<IntRange>()
    var c = 0
    while (c < width) {
        while (c < width && isSeparator[c]) {
            c++
        }
        if (c >= width) break

        val start = c
        while (c < width && !isSeparator[c]) {
            c++
        }

        val end = c - 1
        ranges += start..end
    }


    val matrixPartB: Array<Array<String>> = padded
        .map { line ->
            ranges.map { r ->
                line.substring(r.first, r.last + 1)
            }.toTypedArray()
        }
        .toTypedArray()

    println(part2(matrixPartB, ops))
    // println(part1(matrix, ops)) // if you want to see part1 too
}
