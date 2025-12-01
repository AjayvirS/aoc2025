package day01

import datastructures.Input
import kotlin.math.absoluteValue

fun part1(input: List<String>): Int {
    var absolutePos = 50
    var counts = 0
    for (line: String in input){
        val instructions = Pair(line[0], line.drop(1))
        val currStep = instructions.second.toInt()
        if (instructions.first.equals('L')) {
            absolutePos = (absolutePos - currStep).mod(100)
        } else absolutePos= (absolutePos + currStep).mod(100)
        if(absolutePos.mod (100) == 0){
            counts+=1
        }
    }

    return counts
}

fun part2(input: List<String>): Int {
    var absolutePos = 50
    var counts = 0
    for (line: String in input){
        val instructions = Pair(line[0], line.drop(1))
        val currStep = instructions.second.toInt()
        if (instructions.first.equals('L')) {
            val diff = absolutePos - currStep
            var currRotations=0
            if (absolutePos == 0) {
                currRotations = currStep / 100
            } else {
                if(diff < 0){
                    currRotations = diff.floorDiv(100).absoluteValue
                }
                if(diff.mod(100) == 0){
                    currRotations +=1
                }
            }


            counts+=currRotations

            absolutePos = diff.mod(100)
            println("Value: $instructions, Current value: $absolutePos, Touched Zero: $currRotations")

        } else {
            val diff = absolutePos + currStep
            val currRotations = diff.floorDiv(100).absoluteValue
            counts+=currRotations
            absolutePos = diff.mod(100)
            println("Value: $instructions, Current value: $absolutePos, TouchedZero: $currRotations")

        }

    }

    return counts
}


fun main() {
    val lines = Input.lines("puzzle1A.txt")

    lines.forEach { line ->
        require(line.matches(Regex("[LR][0-9]+"))) {
            "Invalid Step: '$line' (expected e.g. L10, R3, L2123, ...) "
        }
    }


    println(part1(lines))
    println(part2(lines))
}