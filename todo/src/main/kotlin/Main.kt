import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.IOException
import java.util.InputMismatchException
import java.util.Scanner

data class Task(val description: String, var completed: Boolean = false)

class ToDoApp {
    private val tasks = mutableListOf<Task>()

    fun addTask(description: String) {
        tasks.add(Task(description))
    }

    fun markTaskAsCompleted(taskDescription: String) {
        tasks.find { it.description == taskDescription }?.completed = true
    }

    fun deleteTask(taskDescription: String) {
        tasks.removeIf { it.description == taskDescription }
    }

    fun displayCurrentTasks() {
        tasks.forEach {
            val prefix = if (it.completed) "-" else ""
            println("$prefix ${it.description}")
        }
    }

    fun saveToJson(): String {
        val jsonArray = JSONArray()
        tasks.forEach {
            val jsonTask = JSONObject()
            jsonTask.put("description", it.description)
            jsonTask.put("completed", it.completed)
            jsonArray.put(jsonTask)
        }
        return jsonArray.toString()
    }

    fun loadFromFile(filePath: String) {
        try {
            val fileContent = File(filePath).readText()
            val jsonArray = JSONArray(fileContent)
            tasks.clear()
            for (i in 0 until jsonArray.length()) {
                val jsonTask = jsonArray.getJSONObject(i)
                val description = jsonTask.getString("description")
                val completed = jsonTask.getBoolean("completed")
                tasks.add(Task(description, completed))
            }
        } catch (e: IOException) {
            println("Error reading file: $e")
        } catch (e: Exception) {
            println("Error parsing JSON: $e")
        }
    }

    fun saveToFile(filePath: String) {
        try {
            val json = saveToJson()
            File(filePath).writeText(json)
        } catch (e: IOException) {
            println("Error writing to file: $e")
        }
    }
}

fun main() {
    val toDoApp = ToDoApp()
    val filePath = "tasks.json"

    // Load tasks from the file when the app starts
    toDoApp.loadFromFile(filePath)

    val scanner = Scanner(System.`in`)

    while (true) {
        println("\nChoose an option:")
        println("1. Add task")
        println("2. Mark task as completed")
        println("3. Delete task")
        println("4. Display current tasks")
        println("5. Save tasks and exit")

        try {
            val option = scanner.nextInt()
            scanner.nextLine() // consume newline

            when (option) {
                1 -> {
                    print("Enter task description: ")
                    val description = scanner.nextLine()
                    toDoApp.addTask(description)
                }
                2 -> {
                    print("Enter task description to mark as completed: ")
                    val description = scanner.nextLine()
                    toDoApp.markTaskAsCompleted(description)
                }
                3 -> {
                    print("Enter task description to delete: ")
                    val description = scanner.nextLine()
                    toDoApp.deleteTask(description)
                }
                4 -> {
                    println("Current tasks:")
                    toDoApp.displayCurrentTasks()
                }
                5 -> {
                    val json = toDoApp.saveToJson()
                    println("JSON representation of tasks: $json")

                    // Save tasks to the file before exiting
                    toDoApp.saveToFile(filePath)
                    break
                }
                else -> {
                    println("Invalid option. Please try again.")
                    Thread.sleep(1000) // pause for 1 second
                }
            }
        } catch (e: InputMismatchException) {
            println("Invalid input. Please try again.")
            scanner.nextLine() // consume invalid input
            Thread.sleep(1000) // pause for 1 second
        }
    }
}