package me.artspb.php.generator.model.compound.namespace

import me.artspb.php.generator.model.php
import org.junit.Assert.assertEquals
import org.junit.Test

class UseTest {

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
    fun useClassAlias() {
        assertEquals(
                """<?php
namespace N {
    use NN\FooClass as BarClass;
}
""",
                php {
                    namespace("N") {
                        use { _as("NN\\FooClass", "BarClass") }
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
    fun useTowClassesAliases() {
        assertEquals(
                """<?php
namespace N {
    use NN\FooClass as BarClass, NN\NN\FooClass as BazClass;
}
""",
                php {
                    namespace("N") {
                        use {
                            _as("NN\\FooClass", "BarClass")
                            _as("NN\\NN\\FooClass", "BazClass")
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
    fun useGroupClassAlias() {
        assertEquals(
                """<?php
namespace N {
    use NN\{
        NN\FooClass as BarClass
    };
}
""",
                php {
                    namespace("N") {
                        use("NN\\") { _as("NN\\FooClass", "BarClass") }
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
    fun useGroupTwoClassesAliases() {
        assertEquals(
                """<?php
namespace N {
    use NN\{
        NN\FooClass as BarClass,
        NN\NN\FooClass as BazClass
    };
}
""",
                php {
                    namespace("N") {
                        use("NN\\") {
                            _as("NN\\FooClass", "BarClass")
                            _as("NN\\NN\\FooClass", "BazClass")
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
    fun useFunctionAlias() {
        assertEquals(
                """<?php
namespace N {
    use function NN\foo as bar;
}
""",
                php {
                    namespace("N") {
                        use(modifier = "function") { _as("NN\\foo", "bar") }
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
    fun useTowFunctionsAliases() {
        assertEquals(
                """<?php
namespace N {
    use function NN\foo as bar, NN\NN\foo as baz;
}
""",
                php {
                    namespace("N") {
                        use(modifier = "function") {
                            _as("NN\\foo", "bar")
                            _as("NN\\NN\\foo", "baz")
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
    fun useGroupFunctionAlias() {
        assertEquals(
                """<?php
namespace N {
    use function NN\{
        NN\foo as bar
    };
}
""",
                php {
                    namespace("N") {
                        use("NN\\", "function") { _as("NN\\foo", "bar") }
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

    @Test
    fun useGroupTwoFunctionsAliases() {
        assertEquals(
                """<?php
namespace N {
    use function NN\{
        NN\foo as bar,
        NN\NN\foo as baz
    };
}
""",
                php {
                    namespace("N") {
                        use("NN\\", "function") {
                            _as("NN\\foo", "bar")
                            _as("NN\\NN\\foo", "baz")
                        }
                    }
                }.toString())
    }

    @Test
    fun useConstant() {
        assertEquals(
                """<?php
namespace N {
    use const NN\foo;
}
""",
                php {
                    namespace("N") {
                        use(modifier = "const") { +"NN\\foo" }
                    }
                }.toString())
    }

    @Test
    fun useConstantAlias() {
        assertEquals(
                """<?php
namespace N {
    use const NN\foo as bar;
}
""",
                php {
                    namespace("N") {
                        use(modifier = "const") { _as("NN\\foo", "bar") }
                    }
                }.toString())
    }

    @Test
    fun useTowConstants() {
        assertEquals(
                """<?php
namespace N {
    use const NN\foo, NN\NN\foo;
}
""",
                php {
                    namespace("N") {
                        use(modifier = "const") {
                            +"NN\\foo"
                            +"NN\\NN\\foo"
                        }
                    }
                }.toString())
    }

    @Test
    fun useTowConstantsAliases() {
        assertEquals(
                """<?php
namespace N {
    use const NN\foo as bar, NN\NN\foo as baz;
}
""",
                php {
                    namespace("N") {
                        use(modifier = "const") {
                            _as("NN\\foo", "bar")
                            _as("NN\\NN\\foo", "baz")
                        }
                    }
                }.toString())
    }

    @Test
    fun useGroupConstant() {
        assertEquals(
                """<?php
namespace N {
    use const NN\{
        NN\foo
    };
}
""",
                php {
                    namespace("N") {
                        use("NN\\", "const") { +"NN\\foo" }
                    }
                }.toString())
    }

    @Test
    fun useGroupConstantAlias() {
        assertEquals(
                """<?php
namespace N {
    use const NN\{
        NN\foo as bar
    };
}
""",
                php {
                    namespace("N") {
                        use("NN\\", "const") { _as("NN\\foo", "bar") }
                    }
                }.toString())
    }

    @Test
    fun useGroupTwoConstants() {
        assertEquals(
                """<?php
namespace N {
    use const NN\{
        NN\foo,
        NN\NN\foo
    };
}
""",
                php {
                    namespace("N") {
                        use("NN\\", "const") {
                            +"NN\\foo"
                            +"NN\\NN\\foo"
                        }
                    }
                }.toString())
    }

    @Test
    fun useGroupTwoConstantsAliases() {
        assertEquals(
                """<?php
namespace N {
    use const NN\{
        NN\foo as bar,
        NN\NN\foo as baz
    };
}
""",
                php {
                    namespace("N") {
                        use("NN\\", "const") {
                            _as("NN\\foo", "bar")
                            _as("NN\\NN\\foo", "baz")
                        }
                    }
                }.toString())
    }
}