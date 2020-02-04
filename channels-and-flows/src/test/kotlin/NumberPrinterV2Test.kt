import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


@kotlinx.coroutines.ExperimentalCoroutinesApi
class NumberPrinterV2Test {

    @Test
    fun `verify that one of the coroutines send an exception and channel still have numbers to process`() {
        val numberChannel = Channel<Int>()

        Assertions.assertThrows(Exception::class.java) {
            runBlockingTest {
                launch { sendNumbersV2(numberChannel) }
                launch { printNumbersV2(numberChannel) }
            }
        }
    }
}