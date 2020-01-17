import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

suspend fun sendNumbers(numberChannel: Channel<Int>) {
    for (i in 0..9) {
        numberChannel.send(i)
    }
    numberChannel.close()
}

suspend fun printNumbers(numberChannel: Channel<Int>) {
    for (number in numberChannel) {
        println(number)
    }
}

fun main() {
    val numberChannel = Channel<Int>()
    runBlocking {
        launch { sendNumbers(numberChannel) }
        launch {printNumbers(numberChannel) }
    }
}