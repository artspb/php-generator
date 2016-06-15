package me.artspb.php.generator.model

class Comment(val delimited: Boolean = false, val symbol: String = "//") : ElementWithChildren() {

    operator fun String.unaryPlus() {
        children.add(CommentLine(this, if (delimited) "" else symbol))
    }

    override fun generate(builder: StringBuilder, indent: String) {
        builder.append(if (delimited) "/*" else "")
        for ((i, c) in children.withIndex()) {
            c.generate(builder, indent)
            builder.append(if (i < children.size - 1) "\n" else "")
        }
        builder.append(if (delimited) " */" else "")
        builder.append("\n")
    }
}

class CommentLine(val line: String, val symbol: String = "//") : Element {
    override fun generate(builder: StringBuilder, indent: String) {
        builder.append("$indent$symbol $line")
    }
}