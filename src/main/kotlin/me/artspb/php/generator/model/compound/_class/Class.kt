package me.artspb.php.generator.model.compound._class

import me.artspb.php.generator.model.Element
import me.artspb.php.generator.model.ElementWithChildren
import me.artspb.php.generator.model.INDENT
import me.artspb.php.generator.model.compound.CompoundStatementElement
import me.artspb.php.generator.model.compound.FunctionDefinition
import me.artspb.php.generator.model.phpdoc.PhpDoc

open class Interface(val name: String) : CompoundStatementElement() {

    protected val extendsTypes = arrayListOf<String>()

    fun extends(vararg types: String) = extendsTypes.addAll(types)

    open fun method(name: String, vararg modifiers: String, init: MethodDeclaration.() -> Unit): MethodDeclaration =
            initElement(InterfaceMethodDeclaration(name, *modifiers), init)

    fun const(name: String, initializer: () -> String) = initElement(ConstantDeclaration(name, initializer), {})

    fun phpdoc(init: PhpDoc.() -> Unit) = initElement(PhpDoc(), init)

    override fun generateHeader() = "interface $name" +
            (if (extendsTypes.isNotEmpty()) " extends ${extendsTypes.joinToString(", ")}" else "")
}

class InterfaceMethodDeclaration(name: String, vararg modifiers: String) : MethodDeclaration(name, *modifiers, braces = false) {

    override fun generateHeader() = super.generateHeader() + ";"

    override fun afterLBrace() = ""
}

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

class Trait(name: String) : Class(name) {
    override fun generateHeader() = "trait $name"
}

class TraitUse(vararg val names: String) : ElementWithChildren() {

    fun insteadof(name1: String, name2: String) =
            initElement(TraitUseSpecification(name1, "insteadof", name2 = name2), {})

    fun _as(name1: String, modifier: String = "", name2: String = "") =
            initElement(TraitUseSpecification(name1, "as", modifier, name2), {})

    override fun generate(builder: StringBuilder, indent: String) {
        builder.append(indent + "use ")
        for ((i, n) in names.withIndex()) {
            builder.append(n).append(if (i < names.size - 1) ", " else "")
        }
        if (children.isNotEmpty()) {
            builder.append(" {\n")
            for (c in children) {
                c.generate(builder, indent + INDENT)
            }
            builder.append("$indent}\n")
        } else {
            builder.append(";\n")
        }
    }
}

class TraitUseSpecification(val name1: String, val keyword: String, val modifier: String = "", val name2: String = "") : Element {
    override fun generate(builder: StringBuilder, indent: String) {
        builder.append(indent)
        builder.append("$name1 $keyword")
        builder.append(if (modifier.isNotEmpty()) " $modifier" else "")
        builder.append(if (name2.isNotEmpty()) " $name2" else "")
        builder.append(";\n")
    }
}

fun generateModifiers(vararg modifiers: String) = if (modifiers.isNotEmpty()) "${modifiers.joinToString(" ")} " else ""
