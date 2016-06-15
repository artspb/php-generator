# php-generator
Simple PHP code generator on Kotlin builders

[![Build Status](https://travis-ci.org/artspb/php-generator.svg?branch=master)](https://travis-ci.org/artspb/php-generator)

This project is a work in progress.

# Input

```kotlin
fun main(args: Array<String>) = dir("/path/to/file/") {
    file("file.php") {
        php {

            function("foo") {
                returnType("int")
                parameter("bar", "string")
                parameter("baz") {
                    "0"
                }
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

            _class("FooClass", "abstract") {
                implements("QuxInterface")
            }

            _class("BarClass") {
                extends("FooClass")

                phpdoc {
                    param("string", "bar")
                    param("int", "baz")
                    _return("string")
                    throws("Exception")
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
}.create()
```

# Output

> /path/to/file/file.php

```php
<?php
function foo(string $bar, $baz = 0): int {
}
interface QuxInterface {
    function foo(string $bar, $baz = 0): string;
    function bar();
    const CONSTANT = "VALUE";
}
abstract class FooClass implements QuxInterface {
}
class BarClass extends FooClass {
    /**
     * @param string $bar
     * @param int $baz
     * @return string
     * @throws Exception
     */
    public function foo(string $bar, $baz = 0): string {
    }
    function bar() {
    }
    var $qux;
    const TMP_CNST = 100500;
}
```
