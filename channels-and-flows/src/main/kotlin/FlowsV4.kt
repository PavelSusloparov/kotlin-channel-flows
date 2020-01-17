import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        primesFlowV4
            .filter { it.isPrimeV2() }
            .take(1000)
            .filterIfBelowFreezingV4()
            .collect { println(it) }
    }
}

const val FREEZING_POINT_KELVIN_V4 = 273.15
fun Flow<Int>.filterIfBelowFreezingV4() =
    transform { temperature ->
        if (temperature <= FREEZING_POINT_KELVIN_V4) {
            emit(temperature)
        }
    }

val primesFlowV4: Flow<Int> = flow {
    var number = 3
    while (true) {
        emit(number)
        number++
    }
}

// Extension to Int that determines whether a number is prime
fun Int.isPrimeV4(): Boolean {
    (2 until this).map {
        if (this % it == 0) {
            return false // Not a prime!
        }
    }
    return true
}