import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val inputNumbers = mutableListOf<Int>()

    println("Enter integers, one per line. To finish, enter an empty line:")

    while (true) {
        val input = scanner.nextLine()
        if (input.isEmpty()) {
            break
        }
        inputNumbers.add(input.toInt())
    }

    val doubledNumbers = doubleNumbers(inputNumbers)

    println("List with doubled numbers:")
    for (number in doubledNumbers) {
        println(number)
    }
}

fun doubleNumbers(numbers: List<Int>): MutableList<Int> {
    val doubledNumbers = mutableListOf<Int>()
    for (number in numbers) {
        doubledNumbers.add(number * 2)
    }
    return doubledNumbers
}