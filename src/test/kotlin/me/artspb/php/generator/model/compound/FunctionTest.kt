package me.artspb.php.generator.model.compound

import me.artspb.php.generator.model.php
import org.junit.Assert.assertEquals
import org.junit.Test

class FunctionTest {

    @Test
    fun functionReturnType() {
        assertEquals(
                """<?php
function foo(): int {
}
""",
                php {
                    function("foo") {
                        returnType("int")
                    }
                }.toString()
        )
    }

    @Test
    fun functionParameter() {
        assertEquals(
                """<?php
function foo(${'$'}bar) {
}
""",
                php {
                    function("foo") {
                        parameter("bar")
                    }
                }.toString()
        )
    }

    @Test
    fun functionParameterWithDefaultValue() {
        assertEquals(
                """<?php
function foo(${'$'}bar = 0) {
}
""",
                php {
                    function("foo") {
                        parameter("bar") {
                            "0"
                        }
                    }
                }.toString()
        )
    }

    @Test
    fun functionParameterWithType() {
        assertEquals(
                """<?php
function foo(int ${'$'}bar) {
}
""",
                php {
                    function("foo") {
                        parameter("bar", "int")
                    }
                }.toString()
        )
    }

    @Test
    fun functionTwoParameters() {
        assertEquals(
                """<?php
function foo(${'$'}bar, ${'$'}baz) {
}
""",
                php {
                    function("foo") {
                        parameter("bar")
                        parameter("baz")
                    }
                }.toString()
        )
    }
}