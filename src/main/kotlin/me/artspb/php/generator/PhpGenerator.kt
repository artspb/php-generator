import me.artspb.php.generator.model.compound.Php
import java.io.File

fun dir(path: String, nodes: DirNode.() -> Unit): DirNode {
    val dir = DirNode(path)
    dir.nodes()
    return dir
}

interface Node {
    fun create(relativePath: String = ""): Unit
}

class FileNode(val name: String, val php: () -> Php) : Node {
    override fun create(relativePath: String) = File(relativePath + name).printWriter().use { out -> out.print(php().toString()) }
}

class DirNode(path: String) : Node {

    val path: String

    init {
        this.path = if (path.endsWith('/') || path.endsWith('\\')) path else path + File.separator
    }

    val children = arrayListOf<Node>()

    fun file(name: String, php: () -> Php) = createNode(FileNode(name, php), {})

    fun dir(path: String, nodes: DirNode.() -> Unit) = createNode(DirNode(path), nodes)

    private fun <E : Node> createNode(node: E, nodes: E.() -> Unit): E {
        node.nodes()
        children.add(node)
        return node
    }

    override fun create(relativePath: String) {
        File(relativePath + path).mkdirs()
        for (child in children) {
            child.create(relativePath + path)
        }
    }
}
