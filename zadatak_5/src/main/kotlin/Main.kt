import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val inputStrings = mutableListOf<String>()

    println("Enter strings, one per line. To finish, enter an empty line:")

    while (true) {
        val input = scanner.nextLine()
        if (input.isEmpty()) {
            break
        }
        inputStrings.add(input)
    }

    val upperCaseStrings = convertStringsToUpperCase(inputStrings)

    println("List with uppercase strings:")
    for (string in upperCaseStrings) {
        println(string)
    }
}

fun convertStringsToUpperCase(strings: List<String>): MutableList<String> {
    val upperCaseStrings = mutableListOf<String>()
    for (string in strings) {
        upperCaseStrings.add(string.uppercase())
    }
    return upperCaseStrings
}