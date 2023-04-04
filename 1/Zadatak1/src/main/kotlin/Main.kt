import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val numbers = mutableListOf<Int>()

    println("Enter numbers separated by spaces (press Enter to finish):")
    val input = scanner.nextLine()
    val numberStrings = input.split(" ")

    for (numberString in numberStrings) {
        if (numberString.isNotEmpty()) {
            val number = numberString.toInt()
            numbers.add(number)
        }
    }

    val sum = sumOfEvenNumbers(numbers)
    println("The sum of even numbers in the list is: $sum")
}

fun sumOfEvenNumbers(numbers: List<Int>): Int {
    return numbers.filter { it % 2 == 0 }.sum()
}
