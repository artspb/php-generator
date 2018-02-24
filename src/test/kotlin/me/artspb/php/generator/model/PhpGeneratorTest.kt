package me.artspb.php.generator.model

import me.artspb.php.generator.dir
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.io.File
import java.io.FileInputStream

class PhpGeneratorTest {

    @Suppress("MemberVisibilityCanBePrivate")
    @get:Rule
    val root = TemporaryFolder()

    @Test
    fun hierarchy() {
        val dir = root.newFolder().path
        dir(dir) {
            file("foo.php") {
                php {
                    function("foo") {

                    }
                }
            }
            dir("temp") {
                file("temp.php") {
                    php {
                        _class("TestClass") {

                        }
                    }
                }
            }
        }.create()
        assertEquals(
                """<?php
function foo() {
}
""",
                String(FileInputStream(File(dir, "foo.php")).readBytes())
        )
        assertEquals(
                """<?php
class TestClass {
}
""",
                String(FileInputStream(File(dir, "temp/temp.php")).readBytes())
        )
    }

    @Test
    fun creatingFilesRecursively() {
        val dir = root.newFolder().path
        dir(dir) {
            file("foo.php") {
                php {
                    this@dir.file("bar.php") {
                        php {}
                    }
                }
            }
        }.create()
        assertTrue(File(dir, "foo.php").exists())
        assertTrue(File(dir, "bar.php").exists())
    }
}
