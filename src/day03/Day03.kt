package day03

import datastructures.Input
import kotlin.collections.ArrayDeque


fun part1(text: List<String>): Int {
    var totalSum = 0
    for (line in text) {
        var max2 = 0
        val numList = line.map { it.digitToInt() }
        val max = numList.max()
        val maxIndex = numList.indexOf(max)
        if (maxIndex != numList.size - 1) {
            for (i in maxIndex + 1..<numList.size) {
                if (numList[i] > max2) {
                    max2 = numList[i]
                }
            }
            totalSum += (max.toString() + max2.toString()).toInt()
            continue
        }

        for (i in 0..<maxIndex) {
            if (numList[i] > max2) {
                max2 = numList[i]
            }
        }
        totalSum += (max2.toString() + max.toString()).toInt()

    }

    return totalSum
}

fun part2(text: List<String>): Long {
    var totalSum: Long = 0

    for (line in text) {
        val numList = line.map { it.digitToInt().toLong() }
        val stack = ArrayDeque<Long>()

        var toRemove = line.length - 12

        for (num in numList) {
            while (toRemove > 0 && stack.isNotEmpty() && stack.last() < num) {
                stack.removeLast()
                toRemove--
            }
            stack.addLast(num)
        }

        while (stack.size > 12) {
            stack.removeLast()
        }

        var sum: Long = 0
        while (stack.isNotEmpty()) {
            val curr = stack.removeFirst()
            sum = 10L * sum + curr
        }
        totalSum += sum
    }

    return totalSum
}


fun main() {

    val lines = Input.lines("puzzle3A.txt")

    //println(part1(lines))
    println(part2(lines))

}
