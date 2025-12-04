package day04

import datastructures.Input

fun part1(lines: MutableList<CharArray>, repeatable: Boolean = false): Int {
    var canLiftAmount = 0
    val toBeRemoved: MutableList<Pair<Int, Int>> = mutableListOf()
    lines.forEachIndexed { i, s ->
        s.forEachIndexed { j, s2 ->

            if (s2 == '@') {

                var currAmt = 0
                val updateAmount = { amt: Int ->
                    if (amt < 4) {
                        canLiftAmount += 1
                        toBeRemoved.add(Pair(i,j))
                    }

                }
                val len = s.size
                if (j + 1 < len && s[j + 1] == '@') {
                    currAmt += 1
                }

                if (j + 1 < len && i - 1 >= 0 && lines[i - 1][j + 1] == '@') {
                    currAmt += 1

                }

                if (i - 1 >= 0 && lines[i - 1][j] == '@') {
                    currAmt += 1
                }
                if (i - 1 >= 0 && j - 1 >= 0 && lines[i - 1][j - 1] == '@') {
                    currAmt += 1
                }

                if (j - 1 >= 0 && s[j - 1] == '@') {
                    currAmt += 1
                }
                if (i + 1 < lines.size && j - 1 >= 0 && lines[i + 1][j - 1] == '@') {
                    currAmt += 1
                }
                if (i + 1 < lines.size && lines[i + 1][j] == '@') {
                    currAmt += 1
                }
                if (i + 1 < lines.size && j + 1 < len && lines[i + 1][j + 1] == '@') {
                    currAmt += 1
                }


                updateAmount(currAmt)
            }
        }

    }
    if(repeatable) {
        for (pair in toBeRemoved) {
            lines[pair.first][pair.second] = '.'
        }
    }

    return canLiftAmount
}

fun part2(lines: MutableList<CharArray>): Int {
    var totalLifted = 0
    var nextTotalLifted=Int.MIN_VALUE
    while(totalLifted != nextTotalLifted){
        nextTotalLifted = totalLifted
        totalLifted += part1(lines, true)
    }
    return totalLifted
}

fun main() {
    val lines = Input.lines("puzzle4A.txt")
    val grid: MutableList<CharArray> =
        lines.map { it.toCharArray() }.toMutableList()
    //println(part1(grid))
    println(part2(grid))
}