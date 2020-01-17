import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        primesFlowV5
            .take(1000)
            .filterIfBelowFreezingV5(TemperatureUnit.CELSIUS)
            .collect { println(it) }
    }
}

enum class TemperatureUnit(val temperature: Double) {
    KELVIN(273.15),
    CELSIUS(0.00)
}

fun Flow<Int>.filterIfBelowFreezingV5(temperatureUnit: TemperatureUnit) =
    transform { temperature ->
        if (temperature <= temperatureUnit.temperature) {
            emit(temperature)
        }
    }

val primesFlowV5: Flow<Int> = flow {
    var number = -100
    while (true) {
        emit(number)
        number++
    }
}

// Extension to Int that determines whether a number is prime
fun Int.isPrimeV5(): Boolean {
    (2 until this).map {
        if (this % it == 0) {
            return false // Not a prime!
        }
    }
    return true
}