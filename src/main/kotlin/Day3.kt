import java.io.File

fun main() {
    PartOne()
}


private fun PartOne(){
    val data = File("data/day_3.txt")
        .readText()
        .split("\n")

    val mapped = mutableListOf<MutableList<String>>()


    data.forEachIndexed { index, value ->
        val indexList = mutableListOf<String>()

        value.forEachIndexed { index, char ->
            val row = mapped.getOrElse(index){ mutableListOf()}
            row.add(char.toString())
        }
    }
    print(data)
}