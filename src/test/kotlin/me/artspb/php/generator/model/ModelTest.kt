package me.artspb.php.generator.model

import me.artspb.php.generator.model.compound.Php
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestName
import kotlin.reflect.KClass

class ModelTest {

    @get:Rule var name = TestName()

    @Test
    fun example() {
        doTest(php {

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
            }
            
            namespace("NS") {

                use { +"""NS1\NS2\NamespaceClass""" }
                use("NS1\\", "function") { +"NS2\\namespaceFoo" }

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

        })
    }

    private fun doTest(php: Php) {
        assertEquals(ModelTest::class.resourceToString("${name.methodName}.php"), php.toString())
    }

    fun <T : Any> KClass<T>.resourceToString(resource: String) = String(this.java.getResourceAsStream(resource).readBytes())
}
