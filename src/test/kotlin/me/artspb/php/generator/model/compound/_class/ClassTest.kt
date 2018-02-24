package me.artspb.php.generator.model.compound._class

import me.artspb.php.generator.model.php
import org.junit.Assert.assertEquals
import org.junit.Test

class ClassTest {

    @Test
    fun classExtends() {
        assertEquals(
                """<?php
class Foo extends Bar {
}
""",
                php {
                    _class("Foo") {
                        extends("Bar")
                    }
                }.toString()
        )
    }

    @Test
    fun classImplements() {
        assertEquals(
                """<?php
class Foo implements Baz {
}
""",
                php {
                    _class("Foo") {
                        implements("Baz")
                    }
                }.toString()
        )
    }

    @Test
    fun classExtendsImplements() {
        assertEquals(
                """<?php
class Foo extends Bar implements Baz {
}
""",
                php {
                    _class("Foo") {
                        extends("Bar")
                        implements("Baz")
                    }
                }.toString()
        )
    }

    @Test
    fun classAbstract() {
        assertEquals(
                """<?php
abstract class Foo {
}
""",
                php {
                    _class("Foo", "abstract") {}
                }.toString()
        )
    }

    @Test
    fun classFinal() {
        assertEquals(
                """<?php
final class Foo {
}
""",
                php {
                    _class("Foo", "final") {}
                }.toString()
        )
    }

    @Test
    fun classFinalAbstract() {
        assertEquals(
                """<?php
final abstract class Foo {
}
""",
                php {
                    _class("Foo", "final", "abstract") {}
                }.toString()
        )
    }

    @Test
    fun classWithMethod() {
        assertEquals(
                """<?php
class Foo {
    function barMethod() {
    }
}
""",
                php {
                    _class("Foo") {
                        method("barMethod") {}
                    }
                }.toString()
        )
    }

    @Test
    fun classWithMethodPhpDoc() {
        assertEquals(
                """<?php
class Foo {
    /**
     * @throws Exception
     */
    function barMethod() {
    }
}
""",
                php {
                    _class("Foo") {
                        phpdoc {
                            throws("Exception")
                        }
                        method("barMethod") {}
                    }
                }.toString()
        )
    }

    @Test
    fun classWithConstant() {
        assertEquals(
                """<?php
class Foo {
    const CONSTANT = 0;
}
""",
                php {
                    _class("Foo") {
                        const("CONSTANT") {
                            "0"
                        }
                    }
                }.toString()
        )
    }

    @Test
    fun classWithPublicConstant() {
        assertEquals(
                """<?php
class Foo {
    public const CONSTANT = 0;
}
""",
                php {
                    _class("Foo") {
                        const("CONSTANT", "public") {
                            "0"
                        }
                    }
                }.toString()
        )
    }

    @Test
    fun classWithProtectedConstant() {
        assertEquals(
                """<?php
class Foo {
    protected const CONSTANT = 0;
}
""",
                php {
                    _class("Foo") {
                        const("CONSTANT", "protected") {
                            "0"
                        }
                    }
                }.toString()
        )
    }

    @Test
    fun classWithPrivateConstant() {
        assertEquals(
                """<?php
class Foo {
    private const CONSTANT = 0;
}
""",
                php {
                    _class("Foo") {
                        const("CONSTANT", "private") {
                            "0"
                        }
                    }
                }.toString()
        )
    }

    @Test
    fun classWithVar() {
        assertEquals(
                """<?php
class Foo {
    var ${'$'}var;
}
""",
                php {
                    _class("Foo") {
                        property("var")
                    }
                }.toString()
        )
    }

    @Test
    fun classWithVarInitializer() {
        assertEquals(
                """<?php
class Foo {
    var ${'$'}var = 0;
}
""",
                php {
                    _class("Foo") {
                        property("var") {
                            "0"
                        }
                    }
                }.toString()
        )
    }

    @Test
    fun classWithProperty() {
        assertEquals(
                """<?php
class Foo {
    public static ${'$'}var;
}
""",
                php {
                    _class("Foo") {
                        property("var", "public", "static")
                    }
                }.toString()
        )
    }

    @Test
    fun classWithPropertyInitializer() {
        assertEquals(
                """<?php
class Foo {
    public static ${'$'}var = 0;
}
""",
                php {
                    _class("Foo") {
                        property("var", "public", "static") {
                            "0"
                        }
                    }
                }.toString()
        )
    }

    @Test
    fun classWithUse() {
        assertEquals(
                """<?php
class Foo {
    use FooTrait;
}
""",
                php {
                    _class("Foo") {
                        use("FooTrait")
                    }
                }.toString()
        )
    }
}