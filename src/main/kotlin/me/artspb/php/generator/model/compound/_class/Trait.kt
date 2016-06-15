package me.artspb.php.generator.model.compound._class

import me.artspb.php.generator.model.Element
import me.artspb.php.generator.model.ElementWithChildren
import me.artspb.php.generator.model.INDENT

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