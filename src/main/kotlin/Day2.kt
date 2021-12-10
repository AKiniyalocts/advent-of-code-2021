import java.io.File

fun main(){
    PartTwo()
}

private fun PartOne(){

    val data = File("data/day_2.txt")
        .readText()
        .split("\n")
        .map {
            it.split(" ")
        }.map {
            it.first() to it[1].toInt()
        }

    var horizontalPosition = 0
    var verticalPosition = 0

    data.forEach {

        when(it.first){
            "forward" -> horizontalPosition += it.second
            "down" -> verticalPosition += it.second
            "up" -> verticalPosition -= it.second
        }
    }

    println("horizontal position: $horizontalPosition, vertical position: $verticalPosition")
    println("final: ${horizontalPosition * verticalPosition}")
}

private fun PartTwo(){
    val data = File("data/day_2.txt")
        .readText()
        .split("\n")
        .map {
            it.split(" ")
        }.map {
            it.first() to it[1].toInt()
        }

    var aim = 0
    var horizontalPosition = 0
    var depth = 0

    data.forEach {

        when(it.first){
            "forward" -> {
                horizontalPosition += it.second

                if(aim >= 0){
                    depth += it.second * aim
                }
            }
            "down" -> aim += it.second
            "up" -> aim -= it.second
        }
    }

    println("final pos: ${horizontalPosition * depth}")

}