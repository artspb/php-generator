package me.artspb.php.generator.model.compound.namespace

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
}