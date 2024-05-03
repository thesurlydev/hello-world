package dev.surly

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GreeterTest {

    @Test
    fun test() {
        val greeting = Greeter().greet("Shane")
        assertEquals("Hello, Shane!", greeting)
    }
}
