package me.artspb.php.generator.model

class ArbitraryStatement(val statement: String) : Element {
    override fun generate(builder: StringBuilder, indent: String) {
        builder.append("$indent$statement\n")
    }
}
