package me.artspb.php.generator.model.compound._class

import me.artspb.php.generator.model.compound.CompoundStatementElement
import me.artspb.php.generator.model.compound.ConstantDeclaration
import me.artspb.php.generator.model.phpdoc.PhpDoc

open class Interface(val name: String) : CompoundStatementElement() {

    protected val extendsTypes = arrayListOf<String>()

    fun extends(vararg types: String) = extendsTypes.addAll(types)

    open fun method(name: String, vararg modifiers: String, init: MethodDeclaration.() -> Unit): MethodDeclaration =
            initElement(InterfaceMethodDeclaration(name, *modifiers), init)

    fun const(name: String, vararg modifiers: String, initializer: () -> String) = 
            initElement(ConstantDeclaration(name, *modifiers, initializer = initializer), {})

    fun phpdoc(init: PhpDoc.() -> Unit) = initElement(PhpDoc(), init)

    override fun generateHeader() = "interface $name" +
            (if (extendsTypes.isNotEmpty()) " extends ${extendsTypes.joinToString(", ")}" else "")
}

class InterfaceMethodDeclaration(name: String, vararg modifiers: String) : MethodDeclaration(name, *modifiers, braces = false) {

    override fun generateHeader() = super.generateHeader() + ";"

    override fun afterLBrace() = ""
}