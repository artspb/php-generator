package me.artspb.php.generator.model

import me.artspb.php.generator.model.compound.Php

val INDENT = "    " // TODO make property

interface Element {
    fun generate(builder: StringBuilder, indent: String)
}

abstract class ElementWithChildren : Element {

    val children = arrayListOf<Element>()

    protected fun <E : Element> initElement(element: E, init: E.() -> Unit): E {
        element.init()
        children.add(element)
        return element
    }

    override fun toString(): String {
        val builder = StringBuilder()
        generate(builder, "")
        return builder.toString()
    }
}

fun StringBuilder.appendAndTrim(str: String) = this.append(str.trimEnd(' '))

fun php(init: Php.() -> Unit): Php {
    val php = Php()
    php.init()
    return php
}
