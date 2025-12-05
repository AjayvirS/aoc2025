package day05

import datastructures.Input

fun part1(ranges: List<Pair<Long, Long>>, ids: List<Long>): Long {
    var startIndex = 0
    var totalValidIds: Long = 0
    var count = 0

    while ((count < ids.size) && (startIndex < ranges.size)) {

        for (i in startIndex..<ranges.size) {

            val currRange = ranges[i]
            val currId = ids[count]
            if (currId >= currRange.first && currId <= currRange.second) {
                totalValidIds += 1
                count+=1
                break
            }
            if (currId > currRange.second) {
                startIndex = i + 1
                continue
            }
            else {
                count+=1
                break
            }
        }
    }

    return totalValidIds

}


fun main() {
    val text = Input.lines("puzzle5A.txt")

    val rangeRegex = Regex("\\d+-\\d+")
    val numRegex = Regex("\\d+")
    val ranges: MutableList<Pair<Long, Long>> = mutableListOf()
    val ids: MutableList<Long> = mutableListOf()
    text.forEach {
        if (rangeRegex.matches(it)) {
            val nums = it.split("-")
            ranges.add(Pair(nums[0].toLong(), nums[1].toLong()))
        } else if (numRegex.matches(it)) {
            ids.add(it.toLong())

        }
    }
    ids.sort()
    ranges.sortWith(compareBy({ it.first }, { it.second }))
    println(part1(ranges as List<Pair<Long, Long>>, ids as List<Long>))
}