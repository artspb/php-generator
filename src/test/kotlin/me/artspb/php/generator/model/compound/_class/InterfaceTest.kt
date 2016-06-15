package me.artspb.php.generator.model.compound._class

import me.artspb.php.generator.model.php
import org.junit.Assert.assertEquals
import org.junit.Test

class InterfaceTest {

    @Test
    fun _interfaceExtends() {
        assertEquals(
                """<?php
interface Foo extends Bar {
}
""",
                php {
                    _interface("Foo") {
                        extends("Bar")
                    }
                }.toString()
        )
    }

    @Test
    fun interfaceWithMethod() {
        assertEquals(
                """<?php
interface Foo {
    function barMethod();
}
""",
                php {
                    _interface("Foo") {
                        method("barMethod") {}
                    }
                }.toString()
        )
    }

    @Test
    fun interfaceWithMethodPublic() {
        assertEquals(
                """<?php
interface Foo {
    public function barMethod();
}
""",
                php {
                    _interface("Foo") {
                        method("barMethod", "public") {}
                    }
                }.toString()
        )
    }

    @Test
    fun interfaceWithMethodPhpDoc() {
        assertEquals(
                """<?php
interface Foo {
    /**
     * @throws Exception
     */
    function barMethod();
}
""",
                php {
                    _interface("Foo") {
                        phpdoc {
                            throws("Exception")
                        }
                        method("barMethod") {}
                    }
                }.toString()
        )
    }

    @Test
    fun interfaceWithConstant() {
        assertEquals(
                """<?php
interface Foo {
    const CONSTANT = 0;
}
""",
                php {
                    _interface("Foo") {
                        const("CONSTANT") {
                            "0"
                        }
                    }
                }.toString()
        )
    }
}