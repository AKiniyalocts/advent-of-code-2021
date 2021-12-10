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
        val indexList = mapped.getOrNull(index) ?: mutableListOf()

        value.forEach {
            indexList.add(value)
        }
    }
    print(data)
}