import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        primesFlowV2
            .filter { it.isPrimeV2() }
            .take(1000)
            .collect { println(it) }
    }
}

val primesFlowV2: Flow<Int> = flow {
    var number = 3
    while (true) {
        emit(number)
        number++
    }
}

// Extension to Int that determines whether a number is prime
fun Int.isPrimeV2(): Boolean {
    (2 until this).map {
        if (this % it == 0) {
            return false // Not a prime!
        }
    }
    return true
}