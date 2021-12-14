import java.io.File
import java.lang.StringBuilder

fun main() {
    PartTwo()
}


private fun PartOne(){
    val data = File("data/day_3.txt")
        .readText()
        .split("\n")

    val positionCounts: MutableList<PositionCount> = data.first().mapIndexed { index, value ->
        PositionCount(
            position = index,
            numOfOnes = 0,
            numOfZeros = 0
        )
    }.toMutableList()


    data.forEach {
        it.forEachIndexed { index, value ->
            val positionCount = positionCounts[index]
            if(value.toString() == "1"){
                positionCount.numOfOnes++
            }else{
                positionCount.numOfZeros++
            }
        }
    }
    println(positionCounts)

    val gammaRateBuilder = StringBuilder()
    positionCounts.forEach {

        if(it.numOfOnes > it.numOfZeros){
            gammaRateBuilder.append("1")
        }else if(it.numOfZeros > it.numOfOnes){
            gammaRateBuilder.append("0")
        }else {
            println("there was a tie!!")
        }
    }
    println("gamma rate: $gammaRateBuilder")

    val epsilonRateBuilder = StringBuilder()
    positionCounts.forEach {
        if(it.numOfOnes < it.numOfZeros){
            epsilonRateBuilder.append("1")
        }else{
            epsilonRateBuilder.append("0")
        }
    }

    println("epsilon rate: $epsilonRateBuilder")

    println("${gammaRateBuilder.toString().toInt(2).times(epsilonRateBuilder.toString().toInt(2))} ")
}

data class PositionCount(val position: Int, var numOfOnes: Int, var numOfZeros: Int){
    val mostCommon: String
    get() {
        return if(numOfOnes == numOfZeros) {
             "1"
        }else{
            if(numOfOnes > numOfZeros) "1" else "0"
        }
    }

    val leastCommon: String
    get() {
        return if(numOfOnes == numOfZeros){
            return "0"
        }else{
            if(numOfOnes < numOfZeros) "1" else "0"
        }
    }
}

private fun PartTwo(){
    val data = File("data/day_3.txt")
        .readText()
        .split("\n")

    var positionCounts: MutableList<PositionCount> = initialPositionCounts(data)

    var currentIndex = 0

    var keptC02ratings: List<String> = data

    var keptOxygenRatings: List<String> = data

    while(keptOxygenRatings.size > 1){
        positionCounts = initialPositionCounts(keptOxygenRatings)
        keptOxygenRatings = filterByPredicate(keptOxygenRatings, currentIndex, positionCounts[currentIndex].mostCommon)

        currentIndex++
    }
    println("oxygen generator rating: ${keptOxygenRatings.first()}")


    currentIndex = 0
    while(keptC02ratings.size > 1){
        positionCounts = initialPositionCounts(keptC02ratings)
        keptC02ratings = filterByPredicate(keptC02ratings, currentIndex, positionCounts[currentIndex].leastCommon)
        currentIndex++
    }


    println("C02 scrubber rating: ${keptC02ratings.first()}")

    println("Life support rating: ${keptOxygenRatings.first().toInt(2) * keptC02ratings.first().toInt(2)}")
}

private fun initialPositionCounts(data: List<String>): MutableList<PositionCount> {
    var positionCounts: MutableList<PositionCount> = data.first().mapIndexed { index, _ ->
        PositionCount(
            position = index,
            numOfOnes = 0,
            numOfZeros = 0
        )
    }.toMutableList()

    data.forEachIndexed { index, fullNumber ->

        fullNumber.forEachIndexed { individualIndex, value ->
            val positionCount = positionCounts[individualIndex]
            if (value.toString() == "1") {
                positionCount.numOfOnes++
            } else {
                positionCount.numOfZeros++
            }
        }
    }
    return positionCounts
}

private fun filterByPredicate(data: List<String>, currentIndex: Int, predicate: String): List<String>{
    return data.filter {
        val charAtIndex = it[currentIndex].toString()
        charAtIndex == predicate
    }
}