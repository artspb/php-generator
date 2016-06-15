package me.artspb.php.generator.model.compound._class

import me.artspb.php.generator.model.php
import org.junit.Assert.assertEquals
import org.junit.Test

class TraitUseTest {

    @Test
    fun useSingle() {
        assertEquals(
                """<?php
trait FooTrait {
}
class Foo {
    use FooTrait;
}
""",
                php {
                    trait("FooTrait") {
                        
                    }
                    _class("Foo") {
                        use("FooTrait")
                    }
                }.toString()
        )
    }

    @Test
    fun useDouble() {
        assertEquals(
                """<?php
trait FooTrait {
}
trait BarTrait {
}
class Foo {
    use FooTrait, BarTrait;
}
""",
                php {
                    trait("FooTrait") {

                    }
                    trait("BarTrait") {
                        
                    }
                    _class("Foo") {
                        use("FooTrait", "BarTrait")
                    }
                }.toString()
        )
    }

    @Test
    fun useModifierResolution() {
        assertEquals(
                """<?php
trait FooTrait {
    function foo() {
    }
}
class Foo {
    use FooTrait {
        foo as private;
    }
}
""",
                php {
                    trait("FooTrait") {
                        function("foo") {}
                    }
                    _class("Foo") {
                        use("FooTrait") {
                            _as("foo", "private")
                        }
                    }
                }.toString()
        )
    }

    @Test
    fun useNameResolution() {
        assertEquals(
                """<?php
trait FooTrait {
    function foo() {
    }
}
class Foo {
    use FooTrait {
        foo as bar;
    }
}
""",
                php {
                    trait("FooTrait") {
                        function("foo") {}
                    }
                    _class("Foo") {
                        use("FooTrait") {
                            _as("foo", "bar")
                        }
                    }
                }.toString()
        )
    }

    @Test
    fun useModifierAndNameResolution() {
        assertEquals(
                """<?php
trait FooTrait {
    function foo() {
    }
}
class Foo {
    use FooTrait {
        foo as private bar;
    }
}
""",
                php {
                    trait("FooTrait") {
                        function("foo") {}
                    }
                    _class("Foo") {
                        use("FooTrait") {
                            _as("foo", "private", "bar")
                        }
                    }
                }.toString()
        )
    }

    @Test
    fun useConflictResolution() {
        assertEquals(
                """<?php
trait FooTrait {
    function foo() {
    }
}
trait BarTrait {
    function foo() {
    }
}
class Foo {
    use FooTrait, BarTrait {
        FooTrait::foo insteadof BarTrait;
    }
}
""",
                php {
                    trait("FooTrait") {
                        function("foo") {}
                    }
                    trait("BarTrait") {
                        function("foo") {}
                    }
                    _class("Foo") {
                        use("FooTrait", "BarTrait") {
                            insteadof("FooTrait::foo", "BarTrait")
                        }
                    }
                }.toString()
        )
    }
}