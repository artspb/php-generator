package me.artspb.php.generator.model.phpdoc

import me.artspb.php.generator.model.Element
import me.artspb.php.generator.model.ElementWithChildren

open class PhpDoc : ElementWithChildren() {

    fun param(type: String, name: String, description: String = "") = 
            initElement(TypeNameDescriptionPhpDocTag("param", type, name, description), {})
    
    fun _return(type: String, description: String = "") = 
            initElement(TypeNameDescriptionPhpDocTag("return", type, description), {})
    
    fun throws(type: String, description: String = "") = 
            initElement(TypeNameDescriptionPhpDocTag("throws", type, description), {})

    override fun generate(builder: StringBuilder, indent: String) {
        builder.append(indent + "/**\n")
        for (c in children) {
            builder.append(indent + " * ")
            c.generate(builder, "")
            builder.append("\n")
        }
        builder.append(indent + " */\n")
    }
}

abstract class PhpDocTag(val tag: String) : Element {

    override fun generate(builder: StringBuilder, indent: String) {
        builder.append("@$tag " + generateContents())
    }

    abstract protected fun generateContents(): String
}

class TypeNameDescriptionPhpDocTag(tag: String, val type: String, val name: String = "", val description: String = "") : PhpDocTag(tag) {
    override fun generateContents() = type +
            (if (name.isNotEmpty()) " \$" + name else "") +
            (if (description.isNotEmpty()) " $description" else "")
}