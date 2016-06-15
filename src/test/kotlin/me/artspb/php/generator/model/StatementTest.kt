package me.artspb.php.generator.model

import org.junit.Assert.assertEquals
import org.junit.Test

class StatementTest {

    @Test
    fun arbitraryStatement() {
        assertEquals(
                """<?php
define("CONSTANT", "0");
""",
                php {
                    +"""define("CONSTANT", "0");"""
                }.toString()
        )
    }
}