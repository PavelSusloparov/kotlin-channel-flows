import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

const val THREE_SECONDS_IN_MILLISECONDS = 3000L
const val ENDPOINT = "http://0.0.0.0:8080/"

fun main() {
    println("Started outside of runBlocking")
    runBlocking {
        println("Started inside in runBlocking")
        val characterChannel = pollForCharacters()
        repeat(2) {
            println(characterChannel.receive())
        }
        characterChannel.cancel(CancellationException("Not more characters are needed"))
        if (!characterChannel.isClosedForReceive) {
            repeat(2) {
                println(characterChannel.receive())
            }
        }

        println("Finished inside in runBlocking")
    }
    print("Finished outside of runBlocking")
}

@ExperimentalCoroutinesApi
suspend fun CoroutineScope.pollForCharacters(): ReceiveChannel<String> =
    produce {
        while (true) {
            val character = fetchCharacter()
            send(character)
            delay(THREE_SECONDS_IN_MILLISECONDS)
        }
    }

suspend fun fetchCharacter(): String = withContext(Dispatchers.IO) {
    khttp.get(ENDPOINT).text
}