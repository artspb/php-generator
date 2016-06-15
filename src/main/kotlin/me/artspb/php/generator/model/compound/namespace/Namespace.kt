package me.artspb.php.generator.model.compound.namespace

import me.artspb.php.generator.model.compound.CompoundStatementElement

class NamespaceDefinition(val name: String, braces: Boolean = true) : CompoundStatementElement(braces) {

    override fun generateHeader() = "namespace $name" + (if (braces) "" else ";")

    override fun afterRBrace() = if (braces) super.afterRBrace() else ""
}
