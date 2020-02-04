import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun printNumbersCoroutineScopeV3() {
    try {
        coroutineScope {
            for (number in 0..9) {
                println(number)
                if (number == 3)
                    throw Exception("printNumbersV3: Coroutine #3")
                }
        }
    } catch (e: Exception) {
        println("Exception in the main(): $e")
    }
}

fun main() {
    runBlocking {
        launch {
            printNumbersCoroutineScopeV3()
        }
    }
}