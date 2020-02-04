import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun sendNumbersV2(numberChannel: Channel<Int>) {
    coroutineScope {
        for (i in 0..9) {
            numberChannel.send(i)
        }
        numberChannel.close()
    }
}

suspend fun printNumbersV2(numberChannel: Channel<Int>) {
    try {
        coroutineScope {
            for (number in numberChannel) {
                println(number)
                if (number == 3) {
                    throw Exception("printNumbersV3: Coroutine #3")
                }
            }
        }
    } catch (e: Exception) {
        println("Exception in the printNumbersV2: $e")
    }
}

fun main() {
    val numberChannel = Channel<Int>()

    runBlocking {
        launch { sendNumbersV2(numberChannel) }
        launch { printNumbersV2(numberChannel) }
    }
}