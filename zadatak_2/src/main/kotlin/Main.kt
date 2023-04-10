import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val strings = mutableListOf<String>()

    println("Enter a list of strings, press enter for new string and enter again to finish:")

    while (true) {
        val input = scanner.nextLine()
        if (input.isEmpty()) {
            break
        }
        strings.add(input)
    }

    val longestString = findLongestString(strings)
    println("The longest string in the list is: $longestString")
}

fun findLongestString(strings: List<String>): String {
    return strings.maxByOrNull { it.length } ?: ""
}