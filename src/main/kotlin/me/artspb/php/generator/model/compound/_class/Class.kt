package me.artspb.php.generator.model.compound._class

import me.artspb.php.generator.model.Element
import me.artspb.php.generator.model.compound.FunctionDefinition

open class Class(name: String, private vararg val modifiers: String) : Interface(name) {

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

open class MethodDeclaration(name: String, private vararg val modifiers: String, braces: Boolean = true) : FunctionDefinition(name, braces) {

    override fun generateHeader() = generateModifiers(*modifiers) + super.generateHeader()
}

open class PropertyDeclaration(private val name: String, private vararg val modifiers: String,
                               val initializer: () -> String) : Element {

    override fun generate(builder: StringBuilder, indent: String) {
        builder.append(indent)
        builder.append(generateModifiers(*modifiers))
        builder.append(if (modifiers.isEmpty()) "var " else "")
        builder.append(if (modifiers.isNotEmpty() && modifiers.last().equals("const", true)) "" else "\$")
        builder.append(name)
        builder.append(if (initializer().isNotEmpty()) " = ${initializer()}" else "")
        builder.append(";\n")
    }
}

fun generateModifiers(vararg modifiers: String) = if (modifiers.isNotEmpty()) "${modifiers.joinToString(" ")} " else ""
