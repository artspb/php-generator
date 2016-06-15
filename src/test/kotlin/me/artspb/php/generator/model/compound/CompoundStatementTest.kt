package me.artspb.php.generator.model.compound

import me.artspb.php.generator.model.php
import org.junit.Assert.assertEquals
import org.junit.Test

class CompoundStatementTest {

    @Test
    fun php() {
        assertEquals(
                """<?php
""",
                php {}.toString()
        )
    }

    @Test
    fun comment() {
        assertEquals(
                """<?php
// comment
""",
                php {
                    comment { +"comment" }
                }.toString()
        )
    }

    @Test
    fun namespace() {
        assertEquals(
                """<?php
namespace N {
}
""",
                php {
                    namespace("N") {}
                }.toString()
        )
    }

    @Test
    fun function() {
        assertEquals(
                """<?php
function foo() {
}
""",
                php {
                    function("foo") {}
                }.toString()
        )
    }

    @Test
    fun _interface() {
        assertEquals(
                """<?php
interface Foo {
}
""",
                php {
                    _interface("Foo") {}
                }.toString()
        )
    }

    @Test
    fun _class() {
        assertEquals(
                """<?php
class Foo {
}
""",
                php {
                    _class("Foo") {}
                }.toString()
        )
    }

}