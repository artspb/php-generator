package me.artspb.php.generator.model.compound._class

import me.artspb.php.generator.model.php
import org.junit.Assert.assertEquals
import org.junit.Test

class ClassTest {

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

    @Test
    fun _classExtends() {
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
    fun _classImplements() {
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
    fun _classExtendsImplements() {
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
    fun _classAbstract() {
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
    fun _classFinal() {
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
    fun _classFinalAbstract() {
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