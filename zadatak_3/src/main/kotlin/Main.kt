import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val favoriteBooks = mutableListOf<String>()

    println("Enter your favorite books, one per line. To finish, enter an empty line:")

    while (true) {
        val input = scanner.nextLine()
        if (input.isEmpty()) {
            break
        }
        favoriteBooks.add(input)
    }

    println("Your favorite books:")
    for (book in favoriteBooks) {
        println(book)
    }
}