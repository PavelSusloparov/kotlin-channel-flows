import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.html.*
import kotlinx.coroutines.delay
import kotlinx.html.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module() {
    routing {
        get("/") {
            delay(2000)
            call.respondText(
                Character().toString(),
                contentType = ContentType.Text.Plain
            )
        }

        get("/html-dsl") {
            call.respondHtml {
                body {
                    h1 { +"Characters" }
                    ul {
                        for (n in 1..10) {
                            li { +"Character $n: ${Character()}" }
                        }
                    }
                }
            }
        }
    }
}
