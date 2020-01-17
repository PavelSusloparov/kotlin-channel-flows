import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    val primeFlow: Flow<Int> = oneThousandPrimes.asFlow()
//    runBlocking {
//        primeFlow.collect { primeNumber ->
//            println(primeNumber)
//        }
//    }
}

val oneThousandPrimes = generateSequence(3) { value -> value + 1 }
    .filter { it.isPrime() }
    .take(1000)

// Extension to Int that determines whether a number is prime
fun Int.isPrime(): Boolean {
    (2 until this).map {
        if (this % it == 0) {
            return false // Not a prime!
        }
    }
    return true
}