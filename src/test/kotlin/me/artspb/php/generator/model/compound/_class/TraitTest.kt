package me.artspb.php.generator.model.compound._class

import me.artspb.php.generator.model.php
import org.junit.Assert.assertEquals
import org.junit.Test

class TraitTest {

    @Test
    fun traitWithMethod() {
        assertEquals(
                """<?php
trait FooTrait {
    function barMethod() {
    }
}
""",
                php {
                    trait("FooTrait") {
                        method("barMethod") {}
                    }
                }.toString()
        )
    }

    @Test
    fun traitWithMethodPhpDoc() {
        assertEquals(
                """<?php
trait FooTrait {
    /**
     * @throws Exception
     */
    function barMethod() {
    }
}
""",
                php {
                    trait("FooTrait") {
                        phpdoc {
                            throws("Exception")
                        }
                        method("barMethod") {}
                    }
                }.toString()
        )
    }

    @Test
    fun traitWithConstant() {
        assertEquals(
                """<?php
trait FooTrait {
    const CONSTANT = 0;
}
""",
                php {
                    trait("FooTrait") {
                        const("CONSTANT") {
                            "0"
                        }
                    }
                }.toString()
        )
    }

    @Test
    fun traitWithVar() {
        assertEquals(
                """<?php
trait FooTrait {
    var ${'$'}var;
}
""",
                php {
                    trait("FooTrait") {
                        property("var")
                    }
                }.toString()
        )
    }

    @Test
    fun traitWithVarInitializer() {
        assertEquals(
                """<?php
trait FooTrait {
    var ${'$'}var = 0;
}
""",
                php {
                    trait("FooTrait") {
                        property("var") {
                            "0"
                        }
                    }
                }.toString()
        )
    }

    @Test
    fun traitWithProperty() {
        assertEquals(
                """<?php
trait FooTrait {
    public static ${'$'}var;
}
""",
                php {
                    trait("FooTrait") {
                        property("var", "public", "static")
                    }
                }.toString()
        )
    }

    @Test
    fun traitWithPropertyInitializer() {
        assertEquals(
                """<?php
trait FooTrait {
    public static ${'$'}var = 0;
}
""",
                php {
                    trait("FooTrait") {
                        property("var", "public", "static") {
                            "0"
                        }
                    }
                }.toString()
        )
    }

    @Test
    fun traitWithUse() {
        assertEquals(
                """<?php
trait FooTrait {
    use BarTrait;
}
""",
                php {
                    trait("FooTrait") {
                        use("BarTrait")
                    }
                }.toString()
        )
    }
}