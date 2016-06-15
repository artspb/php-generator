package me.artspb.php.generator.model.compound

import me.artspb.php.generator.model.php
import org.junit.Assert.assertEquals
import org.junit.Test

class NamespaceTest {

    @Test
    fun namespaceWithoutBraces() {
        assertEquals(
                """<?php
namespace N;
""",
                php {
                    namespace("N", false) {}
                }.toString()
        )
    }

    @Test
    fun useClass() {
        assertEquals(
                """<?php
namespace N {
    use NN\FooClass;
}
""",
                php {
                    namespace("N") {
                        use { +"NN\\FooClass" }
                    }
                }.toString())
    }

    @Test
    fun useTowClasses() {
        assertEquals(
                """<?php
namespace N {
    use NN\FooClass, NN\NN\FooClass;
}
""",
                php {
                    namespace("N") {
                        use {
                            +"NN\\FooClass"
                            +"NN\\NN\\FooClass"
                        }
                    }
                }.toString())
    }

    @Test
    fun useGroupClass() {
        assertEquals(
                """<?php
namespace N {
    use NN\{
        NN\FooClass
    };
}
""",
                php {
                    namespace("N") {
                        use("NN\\") { +"NN\\FooClass" }
                    }
                }.toString())
    }

    @Test
    fun useGroupTwoClasses() {
        assertEquals(
                """<?php
namespace N {
    use NN\{
        NN\FooClass,
        NN\NN\FooClass
    };
}
""",
                php {
                    namespace("N") {
                        use("NN\\") {
                            +"NN\\FooClass"
                            +"NN\\NN\\FooClass"
                        }
                    }
                }.toString())
    }

    @Test
    fun useFunction() {
        assertEquals(
                """<?php
namespace N {
    use function NN\foo;
}
""",
                php {
                    namespace("N") {
                        use(modifier = "function") { +"NN\\foo" }
                    }
                }.toString())
    }

    @Test
    fun useTowFunctions() {
        assertEquals(
                """<?php
namespace N {
    use function NN\foo, NN\NN\foo;
}
""",
                php {
                    namespace("N") {
                        use(modifier = "function") {
                            +"NN\\foo"
                            +"NN\\NN\\foo"
                        }
                    }
                }.toString())
    }

    @Test
    fun useGroupFunction() {
        assertEquals(
                """<?php
namespace N {
    use function NN\{
        NN\foo
    };
}
""",
                php {
                    namespace("N") {
                        use("NN\\", "function") { +"NN\\foo" }
                    }
                }.toString())
    }

    @Test
    fun useGroupTwoFunctions() {
        assertEquals(
                """<?php
namespace N {
    use function NN\{
        NN\foo,
        NN\NN\foo
    };
}
""",
                php {
                    namespace("N") {
                        use("NN\\", "function") {
                            +"NN\\foo"
                            +"NN\\NN\\foo"
                        }
                    }
                }.toString())
    }

}