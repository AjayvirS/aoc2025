package day06

import datastructures.Input


fun part1(matrix: Array<IntArray>, ops: List<String>): Long {
    var totalSum: Long = 0
    ops.forEachIndexed { index, s ->
        var colSum: Long = when (s) {
            "+" -> matrix.sumOf { it[index].toLong() }
            "*" -> matrix.fold(1) { acc, row ->
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
            "+" -> {
                if (intsTransposed.isEmpty()) {
                    0.toLong()
                } else {
                    intsTransposed.sum().toLong()
                }
            }
            "*" -> {
                if (intsTransposed.isEmpty()) {
                    0.toLong()
                } else {
                    intsTransposed.fold(1.toLong()) { acc, curr -> acc * curr }
                }
            }
            else -> error("Operation not supported")
        }
        totalSum += colSum
    }

    return totalSum

}

private fun parseColumnInts(matrix: Array<Array<String>>, col: Int): List<Int> {
    val intsBySubCol = linkedMapOf<Int, Int>()

    matrix.forEach { line ->
        val cell = line.getOrNull(col) ?: ""
        cell.forEachIndexed { index, ch ->
            if (ch.isDigit()) {
                val existing = intsBySubCol[index] ?: 0
                intsBySubCol[index] = existing * 10 + ch.digitToInt()
            }
        }
    }

    return intsBySubCol.values.toList()
}


// 123, 45, 6 -> 356*24*1
// 64, 23, 314 ->


fun main() {

    val text = Input.lines("puzzle6A.txt")
    val allLines = text.dropLast(1)
    val firstLine = allLines.first()
    val matrix = allLines.map { it -> it.split(Regex("\\s+")).map { it.toInt() }.toIntArray() }.toTypedArray()
    val regexNumWithPadding = Regex("\\s*\\d+\\s")

    val ranges = regexNumWithPadding
        .findAll("$firstLine ")
        .map { it.range }
        .toList()
    val matrixPartB = allLines
        .map { line ->
            val padded = line.padEnd(firstLine.length + 1, ' ')
            ranges
                .map { r -> padded.substring(r.first, r.last + 1) }
                .toTypedArray()
        }
        .toTypedArray()


    println(part2(matrixPartB, text.last().trim().split(Regex("\\s+"))))
    //println(part1(matrix, text.last().trim().split(Regex("\\s+"))))

}
