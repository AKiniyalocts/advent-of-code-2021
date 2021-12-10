import java.io.File

fun main(){
    val data = File("data/day_1.txt")
        .readText()
        .split("\n")

    var previous : Int? = null
    var current: Int
    var increased = 0
    data.forEachIndexed { _, string ->

        if(previous == null){
            previous = string.toInt()
        }else {
            current = string.toInt()
            if(current > previous!!){
                increased++
            }
            previous = current
        }
    }
    print("increased: $increased")
}


fun Part2(){
    val data = File("data/day_1.txt")
        .readText()
        .split("\n")
        .mapNotNull { it.toIntOrNull() }

    val windowSums = mutableListOf<Int>()
    data.forEachIndexed { index, _ ->
        val secondItem = data.getOrElse(index + 3){
            return@forEachIndexed
        }
        windowSums.add(data.subList(index, index + 3).sumOf { it })
    }

    println(windowSums)

    var previous : Int? = null
    var current: Int
    var increased = 0
    windowSums.forEachIndexed { _, sum ->

        if(previous == null){
            previous = sum
        }else {
            current = sum
            if(current > previous!!){
                increased++
            }
            previous = current
        }
    }
    println("increased: $increased")

}
