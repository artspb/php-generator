package me.artspb.php.generator.model.compound._class

import me.artspb.php.generator.model.Element
import me.artspb.php.generator.model.ElementWithChildren
import me.artspb.php.generator.model.INDENT
import me.artspb.php.generator.model.compound.CompoundStatementElement
import me.artspb.php.generator.model.compound.FunctionDefinition
import me.artspb.php.generator.model.phpdoc.PhpDoc

open class Class(name: String, vararg val modifiers: String) : Interface(name) {

    private val implementsTypes = arrayListOf<String>()

    fun implements(vararg types: String) = implementsTypes.addAll(types)

    fun use(vararg names: String, init: TraitUse.() -> Unit = {}) = initElement(TraitUse(*names), init)

    override fun method(name: String, vararg modifiers: String, init: MethodDeclaration.() -> Unit) =
            initElement(MethodDeclaration(name, *modifiers), init)

    fun property(name: String, vararg modifiers: String, initializer: () -> String = { "" }) =
            initElement(PropertyDeclaration(name, *modifiers, initializer = initializer), {})

    override fun generateHeader() = generateModifiers(*modifiers) +
            "class $name" +
            (if (extendsTypes.isNotEmpty()) " extends ${extendsTypes.joinToString(", ")}" else "") +
            (if (implementsTypes.isNotEmpty()) " implements ${implementsTypes.joinToString(", ")}" else "")
}

open class MethodDeclaration(name: String, vararg val modifiers: String, braces: Boolean = true) : FunctionDefinition(name, braces) {

    override fun generateHeader() = generateModifiers(*modifiers) + super.generateHeader()
}

open class PropertyDeclaration(val name: String, vararg val modifiers: String, val initializer: () -> String) : Element {

    override fun generate(builder: StringBuilder, indent: String) {
        builder.append(indent)
        builder.append(generateModifiers(*modifiers))
        builder.append(if (modifiers.isEmpty()) "var " else "")
        builder.append(if (modifiers.size == 1 && modifiers.first().equals("const", true)) "" else "\$")
        builder.append("$name")
        builder.append(if (initializer().isNotEmpty()) " = ${initializer()}" else "")
        builder.append(";\n")
    }
}

open class ConstantDeclaration(name: String, initializer: () -> String) : PropertyDeclaration(name, "const", initializer = initializer) {}

fun generateModifiers(vararg modifiers: String) = if (modifiers.isNotEmpty()) "${modifiers.joinToString(" ")} " else ""
