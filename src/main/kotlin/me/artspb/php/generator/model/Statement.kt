package me.artspb.php.generator.model

class ArbitraryStatement(private val statement: String) : Element {
    override fun generate(builder: StringBuilder, indent: String) {
        builder.append("$indent$statement\n")
    }
}
