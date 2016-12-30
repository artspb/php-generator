package me.artspb.php.generator.model

import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import org.junit.rules.TestName
import java.io.File
import kotlin.reflect.KClass

class ModelTest {

    @get:Rule var name = TestName()
    @get:Rule var dir = TemporaryFolder()

    @Test
    fun example() {
        val path = dir.newFolder().path
        val file = "${name.methodName.capitalize()}.php"
        main(arrayOf(path, file))
        assertEquals(ModelTest::class.resourceToString(file), File(path, file).readText())
    }

    fun <T : Any> KClass<T>.resourceToString(resource: String) = String(this.java.getResourceAsStream(resource).readBytes())
}
