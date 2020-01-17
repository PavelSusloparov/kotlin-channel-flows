import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        primesFlowV3
            .filter { it.isPrimeV2() }
            .take(1000)
            .filterIfBelowFreezingV3()
            .collect { println(it) }
    }
}

const val FREEZING_POINT_KELVIN = 273.15
fun Flow<Int>.filterIfBelowFreezingV3() =
    filter { temperature ->
        temperature <= FREEZING_POINT_KELVIN
    }

val primesFlowV3: Flow<Int> = flow {
    var number = 3
    while (true) {
        emit(number)
        number++
    }
}

// Extension to Int that determines whether a number is prime
fun Int.isPrimeV3(): Boolean {
    (2 until this).map {
        if (this % it == 0) {
            return false // Not a prime!
        }
    }
    return true
}