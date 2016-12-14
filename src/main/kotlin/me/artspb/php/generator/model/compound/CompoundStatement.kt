package me.artspb.php.generator.model.compound

import me.artspb.php.generator.model.*
import me.artspb.php.generator.model.compound._class.Class
import me.artspb.php.generator.model.compound._class.Interface
import me.artspb.php.generator.model.compound._class.PropertyDeclaration
import me.artspb.php.generator.model.compound._class.Trait
import me.artspb.php.generator.model.compound.namespace.NamespaceDefinition
import me.artspb.php.generator.model.phpdoc.PhpDoc

abstract class CompoundStatementElement(val braces: Boolean = true) : ElementWithChildren() {

    operator fun String.unaryPlus() {
        children.add(ArbitraryStatement(this))
    }

    fun comment(delimited: Boolean = false, symbol: String = "//", init: Comment.() -> Unit) =
            initElement(Comment(delimited, symbol), init)

    fun function(name: String, init: FunctionDefinition.() -> Unit) =
            initElement(FunctionDefinition(name), init)

    fun _for(expr1: String = "", expr2: String = "", expr3: String = "", init: ForStatement.() -> Unit) = 
            initElement(ForStatement(expr1, expr2, expr3), init)

    fun foreach(array: String, key: String = "", value: String, init: ForeachStatement.() -> Unit) = 
            initElement(ForeachStatement(array, key, value), init)

    fun const(name: String, initializer: () -> String) =
            initElement(ConstantDeclaration(name, initializer = initializer), {})

    fun _interface(name: String, init: Interface.() -> Unit) =
            initElement(Interface(name), init)

    fun _class(name: String, vararg modifiers: String, init: Class.() -> Unit) =
            initElement(Class(name, *modifiers), init)

    fun trait(name: String, init: Class.() -> Unit) = initElement(Trait(name), init)

    fun phpdoc(init: PhpDoc.() -> Unit) = initElement(PhpDoc(), init)

    override fun generate(builder: StringBuilder, indent: String) {
        builder.appendAndTrim(indent + generateHeader() + " " + (if (braces) "{" else "")).append(afterLBrace())
        for (c in children) {
            c.generate(builder, indent + if (braces) INDENT else "")
        }
        builder.appendAndTrim(indent + (if (braces) "}" else "")).append(afterRBrace())
    }

    abstract protected fun generateHeader(): String
    
    open protected fun afterLBrace() = "\n"
    
    open protected fun afterRBrace() = "\n"
}

class Php : CompoundStatementElement(false) {

    fun namespace(name: String = "", braces: Boolean = true, init: NamespaceDefinition.() -> Unit) =
            initElement(NamespaceDefinition(name, braces), init)

    override fun generateHeader() = "<?php"
    
    override fun afterRBrace() = ""
}

open class FunctionDefinition(val name: String, braces: Boolean = true) : CompoundStatementElement(braces) {

    private var returnType = ""
    private val parameters = arrayListOf<Triple<String, String, () -> String>>()

    fun returnType(returnType: String) {
        this.returnType = returnType
    }

    fun parameter(name: String, type: String = "", defaultValue: () -> String = {""}) = parameters.add(Triple(name, type, defaultValue))

    override fun generateHeader() = "function $name(${generateParameters()})" + (if (returnType.isNotEmpty()) ": $returnType" else "")

    private fun generateParameters() = parameters.map {
        (if (it.second.isNotEmpty()) "${it.second} " else "") + "\$" + it.first + if (it.third().isNotEmpty()) " = ${it.third()}" else ""
    }.joinToString(", ")
}

class ForStatement(val expr1: String, val expr2: String, val expr3: String) : CompoundStatementElement() {
    override fun generateHeader() = "for ($expr1; $expr2; $expr3)"
}

class ForeachStatement(val array: String, val key: String, val value: String) : CompoundStatementElement() {
    override fun generateHeader() = "foreach ($array as ${if (key.isNotEmpty()) "$key => " else ""}$value)"
}

class ConstantDeclaration(name: String, vararg modifiers: String, initializer: () -> String) : PropertyDeclaration(name, *modifiers, "const", initializer = initializer) {}
