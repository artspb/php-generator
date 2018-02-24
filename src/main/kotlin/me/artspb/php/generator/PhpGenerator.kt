package me.artspb.php.generator

import me.artspb.php.generator.model.compound.Php
import java.io.File
import java.util.*

fun dir(path: String, nodes: DirNode.() -> Unit): DirNode {
    val dir = DirNode(path)
    dir.nodes()
    return dir
}

interface Node {
    fun create(relativePath: String = "")
}

class FileNode(private val name: String, val php: () -> Php) : Node {
    override fun create(relativePath: String) = File(relativePath + name).printWriter().use { out -> out.print(php().toString()) }
}

class DirNode(path: String) : Node {

    private val path = if (path.endsWith('/') || path.endsWith('\\')) path else path + File.separator
    private val children = ArrayDeque<Node>()

    fun file(name: String, php: () -> Php) = createNode(FileNode(name, php), {})

    fun dir(path: String, nodes: DirNode.() -> Unit) = createNode(DirNode(path), nodes)

    private fun <E : Node> createNode(node: E, nodes: E.() -> Unit): E {
        node.nodes()
        children.add(node)
        return node
    }

    override fun create(relativePath: String) {
        File(relativePath + path).mkdirs()
        while (children.isNotEmpty()) {
            children.poll().create(relativePath + path)
        }
    }
}
