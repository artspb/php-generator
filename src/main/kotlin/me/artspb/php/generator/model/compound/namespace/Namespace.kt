package me.artspb.php.generator.model.compound.namespace

import me.artspb.php.generator.model.Element
import me.artspb.php.generator.model.ElementWithChildren
import me.artspb.php.generator.model.INDENT
import me.artspb.php.generator.model.compound.CompoundStatementElement

class NamespaceDefinition(val name: String, braces: Boolean = true) : CompoundStatementElement(braces) {

    fun use(name: String = "", modifier: String = "", init: UseDeclaration.() -> Unit) =
            initElement(UseDeclaration(name, modifier), init)

    override fun generateHeader() = "namespace $name" + (if (braces) "" else ";")

    override fun afterRBrace() = if (braces) super.afterRBrace() else ""
}

class UseDeclaration(val prefix: String = "", val modifier: String = "") : ElementWithChildren() {
    
    operator fun String.unaryPlus() {
        children.add(UseClause(this))
    }

    fun _as(name: String, alias: String) = initElement(UseClause(name, alias), {})

    override fun generate(builder: StringBuilder, indent: String) {
        val groupUse = prefix.isNotEmpty()
        builder.append(indent + "use ")
        builder.append(if (modifier.isNotEmpty()) "$modifier " else "")
        builder.append(if (groupUse) "$prefix{" else "")
        for ((i, c) in children.withIndex()) {
            builder.append(if (groupUse) "\n" else "")
            c.generate(builder, if (groupUse) indent + INDENT else "")
            builder.append(if (i < children.size - 1) (if (groupUse) "," else ", ") else (if (groupUse) "\n" else ""))
        }
        builder.append(if (groupUse) "$indent}" else "").append(";\n")
    }
}

class UseClause(val name: String, val alias: String = "") : Element {

    override fun generate(builder: StringBuilder, indent: String) {
        builder.append(indent).append(if (alias.isNotEmpty()) "$name as $alias" else name)
    }

}