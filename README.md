# php-generator
Simple PHP code generator on Kotlin builders

[![Build Status](https://travis-ci.org/artspb/php-generator.svg?branch=master)](https://travis-ci.org/artspb/php-generator)

This project is a work in progress.

# Input

```kotlin
import me.artspb.php.generator.model.php

fun main(args: Array<String>) = dir("/path/to/file/") {
    file("file.php") {
        php {

            comment { +"normal comment" }
            comment {
                +"normal"
                +"multiline"
                +"comment"
            }
            comment(symbol = "#") { +"sharp comment" }
            comment(symbol = "#") {
                +"sharp"
                +"multiline"
                +"comment"
            }
            comment(true) { +"delimited comment" }
            comment(true) {
                +"delimited"
                +"multiline"
                +"comment"
            }

            namespace("NS1\\NS2") {
                _class("NamespaceClass") {

                }
                function("namespaceFoo") {

                }
                const("CONSTANT1") {
                    """"1""""
                }
                +"""define("NS1\\NS2\\CONSTANT2", "2");"""
            }

            namespace("NS") {

                use { _as("""NS1\NS2\NamespaceClass""", "Clazz") }
                use("NS1\\", "function") { +"NS2\\namespaceFoo" }
                use("NS1\\", "const") {
                    +"NS2\\CONSTANT1" 
                    _as("NS2\\CONSTANT2", "CNST") 
                }

                function("foo") {
                    returnType("int")
                    parameter("bar", "string")
                    parameter("baz") {
                        "0"
                    }
                }

                _for("\$i = 0", "\$i < 10", "\$i++") {
                    +"echo \$i;"
                }

                foreach("array()", value = "\$value") {
                    +"echo \$value;"
                }

                foreach("""["key" => "value"]""", key = "\$key", value = "\$value") {
                    +"""echo "${'$'}key = ${'$'}value";"""
                }

                _interface("QuxInterface") {
                    method("foo") {
                        returnType("string")
                        parameter("bar", "string")
                        parameter("baz") {
                            "0"
                        }
                    }
                    method("bar") {}
                    const("CONSTANT") {
                        """"VALUE""""
                    }
                }
                
                trait("FooTrait") {
                    method("foo") {
                        
                    }
                }

                trait("BarTrait") {
                    method("foo") {
                        
                    }
                }

                _class("FooClass", "abstract") {
                    implements("QuxInterface")
                    use("FooTrait") {
                        _as("foo", "private")
                        _as("foo", "bar")
                        _as("foo", "private", "bar")
                    }
                    use("FooTrait", "BarTrait") {
                        insteadof("FooTrait::foo", "BarTrait")
                    }
                    const("CNST", "private") {
                        "0"
                    }
                }

                _class("BarClass") {
                    extends("FooClass")

                    phpdoc {
                        param("string", "bar")
                        param("int", "baz")
                        _return("string")
                        throws("\\Exception")
                    }
                    method("foo", "public") {
                        returnType("string")
                        parameter("bar", "string")
                        parameter("baz") {
                            "0"
                        }
                    }

                    method("bar") {}

                    property("qux");

                    const("TMP_CNST") {
                        "100500"
                    }
                }

            }

        }

    }
}.create()
```

# Output

> /path/to/file/file.php

```php
<?php
// normal comment
// normal
// multiline
// comment
# sharp comment
# sharp
# multiline
# comment
/* delimited comment */
/* delimited
 multiline
 comment */
namespace NS1\NS2 {
    class NamespaceClass {
    }
    function namespaceFoo() {
    }
    const CONSTANT1 = "1";
    define("NS1\\NS2\\CONSTANT2", "2");
}
namespace NS {
    use NS1\NS2\NamespaceClass as Clazz;
    use function NS1\{
        NS2\namespaceFoo
    };
    use const NS1\{
        NS2\CONSTANT1,
        NS2\CONSTANT2 as CNST
    };
    function foo(string $bar, $baz = 0): int {
    }
    for ($i = 0; $i < 10; $i++) {
        echo $i;
    }
    foreach (array() as $value) {
        echo $value;
    }
    foreach (["key" => "value"] as $key => $value) {
        echo "$key = $value";
    }
    interface QuxInterface {
        function foo(string $bar, $baz = 0): string;
        function bar();
        const CONSTANT = "VALUE";
    }
    trait FooTrait {
        function foo() {
        }
    }
    trait BarTrait {
        function foo() {
        }
    }
    abstract class FooClass implements QuxInterface {
        use FooTrait {
            foo as private;
            foo as bar;
            foo as private bar;
        }
        use FooTrait, BarTrait {
            FooTrait::foo insteadof BarTrait;
        }
        private const CNST = 0;
    }
    class BarClass extends FooClass {
        /**
         * @param string $bar
         * @param int $baz
         * @return string
         * @throws \Exception
         */
        public function foo(string $bar, $baz = 0): string {
        }
        function bar() {
        }
        var $qux;
        const TMP_CNST = 100500;
    }
}
```
