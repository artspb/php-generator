package me.artspb.php.generator.model

import org.junit.Assert.assertEquals
import org.junit.Test

class CommentTest {

    @Test
    fun normal() {
        assertEquals(
                """<?php
// normal comment
""",
                php {
                    comment { +"normal comment" }
                }.toString()
        )
    }

    @Test
    fun normalMultiline() {
        assertEquals(
                """<?php
// normal
// multiline
// comment
""",
                php {
                    comment {
                        +"normal"
                        +"multiline"
                        +"comment"
                    }
                }.toString()
        )
    }

    @Test
    fun sharp() {
        assertEquals(
                """<?php
# sharp comment
""",
                php {
                    comment(symbol = "#") { +"sharp comment" }
                }.toString()
        )
    }

    @Test
    fun sharpMultiline() {
        assertEquals(
                """<?php
# sharp
# multiline
# comment
""",
                php {
                    comment(symbol = "#") {
                        +"sharp"
                        +"multiline"
                        +"comment"
                    }
                }.toString()
        )
    }

    @Test
    fun delimited() {
        assertEquals(
                """<?php
/* delimited comment */
""",
                php {
                    comment(true) { +"delimited comment" }
                }.toString()
        )
    }

    @Test
    fun delimitedMultiline() {
        assertEquals(
                """<?php
/* delimited
 multiline
 comment */
""",
                php {
                    comment(true) {
                        +"delimited"
                        +"multiline"
                        +"comment"
                    }
                }.toString()
        )
    }
    
}