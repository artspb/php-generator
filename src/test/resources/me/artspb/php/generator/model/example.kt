package me.artspb.php.generator.model

fun generate() = php {

    namespace("NS") {

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
