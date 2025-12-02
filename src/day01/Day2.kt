package day01

import datastructures.Input

fun part1(text: String): Long{

    val rangesAsPairs = parseAsPairsList(text)
    var totalSum: Long = 0
    for(pair in rangesAsPairs){
        for (number in pair.first..pair.second){
            val stringNum = number.toString()
            val strLen = stringNum.length

            if(strLen.mod(2) == 0){
                var valid = true
                for (i in 0..<stringNum.length.floorDiv(2)){
                    if(stringNum[i] != stringNum[strLen.floorDiv(2) + i ]){
                        valid = false
                        break;
                    }
                }
                if(valid){
                    totalSum+=number
                }
            }

        }
    }
    return totalSum

}

fun parseAsPairsList(text: String): List<Pair<Long, Long>> {
    val pairRegex = Regex("([0-9]+)-([0-9]+)")
    val pairsList = pairRegex.findAll(text)

    return pairsList.toList().map {
        val (startStr, endStr) = it.destructured
        Pair(startStr.toLong(), endStr.toLong())
    }
}

fun main() {
    val text = Input.text("puzzle2A.txt")

        require(text.matches(Regex("([0-9]+-[0-9]+)(,[0-9]+-[0-9]+)*"))) {
            "Invalid format: '$text' (expected e.g. 22-30)"
    }

    println(part1(text))


}