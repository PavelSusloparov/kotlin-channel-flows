import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun handleItem(number: Int, innerNumber: Int) {
    try {
        coroutineScope {
            delay(1000)
            println("handleItem $number-$innerNumber")
            if (number == 1 && innerNumber == 13) {
                throw Exception("number is 1 && innerNumber is 13")
            }
        }
    } catch (e: Exception) {
        println("handleItem exception: $e")
    }
}

suspend fun handlePage(number: Int) {
    try {
        coroutineScope {
            delay(1000)
            if (number == 2) {
                throw Exception("number is 2")
            }
            for (innerNumber in 10..15) {
                launch {
                    handleItem(number, innerNumber)
                }
            }
            println("handlePage $number")
        }
    } catch (e: Exception) {
        println("handlePage exception: $e")
    }
}

fun main() {
    runBlocking {
        for (number in 0..2) {
            launch {
                handlePage(number)
            }
        }
        println("helloWorld after")
    }
}