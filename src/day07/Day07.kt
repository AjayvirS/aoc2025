package day07

import datastructures.Input


fun part2(array: Array<Array<String>>): Long {

    val currX = 0
    val currY = array[0].indexOfFirst { it == "S" }
    val memo = Array(array.size) { LongArray(array[0].size) { -1 } }
    val reachedBeams = splitBeam(array, currX, currY, memo)
    return reachedBeams

}

fun splitBeam(array: Array<Array<String>>, currX: Int, currY: Int, memo: Array<LongArray>): Long {

    if(currY < 0 || currY > array[0].size - 1){
        return 0
    }
    if(currX==array.size){
        return 1
    }

    val res = if(memo[currX][currY] != -1L){
        memo[currX][currY]
    }
    else if(array[currX][currY] == "^"){
        splitBeam(array, currX+1, currY-1, memo) + splitBeam(array, currX+1, currY + 1, memo)
    } else {
        splitBeam(array, currX + 1, currY, memo)
    }
    memo[currX][currY] = res
    return res
}

fun part1(array: Array<Array<String>>): Long {
    val currY = array[0].indexOfFirst { it == "S" }
    return countSplits(array, currY)
}

fun countSplits(array: Array<Array<String>>, currY: Int): Long {
    val rowSize = array[0].size
    var curr = BooleanArray(rowSize)
    curr[currY] = true
    var splits = 0L

    for(row in array.indices){
        val next = BooleanArray(rowSize)
        for (col in 0..<rowSize){
            val beams = curr[col]
            if(!beams) continue

            when(array[row][col]){
                "^" -> {
                    splits+=1
                    if(col > 0) next[col-1] = true
                    if(col + 1 < rowSize) next[col+1] = true
                }
                else -> {
                    next[col] = true
                }

            }
        }
        curr = next

    }
    return splits
}



fun main(){

    val array: Array<Array<String>> = Input.twoDArray("puzzle7A.txt", "")
    //println(part1(array))
    println(part2(array))

}