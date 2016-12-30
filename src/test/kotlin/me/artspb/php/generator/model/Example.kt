package me.artspb.php.generator.model

import me.artspb.php.generator.dir

fun main(args: Array<String>) = dir(args[0]) {
    file(args[1]) {
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

                _try {
                    +"throw new \\Exception();"
                }
                catch("\\Exception", "\$e") {
                    +"var_dump(\$e);"
                }
                finally {
                    +"""echo "finally";"""
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