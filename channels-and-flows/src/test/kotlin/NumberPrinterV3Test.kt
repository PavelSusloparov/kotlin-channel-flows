import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test

@kotlinx.coroutines.ExperimentalCoroutinesApi
class NumberPrinterV3Test {

    @Test
    fun `happy path`() {
        runBlockingTest {
            printNumbersCoroutineScopeV3()
        }
    }
}