package me.lucyydotp.component.dsl

import me.lucyydotp.component.rule.Rule
import me.lucyydotp.component.rule.RuleImpl
import me.lucyydotp.component.selector.Selector
import me.lucyydotp.component.selector.SelectorTarget
import me.lucyydotp.component.selector.Selectors
import me.lucyydotp.component.sheet.StyleSheet
import net.kyori.adventure.text.format.Style

@DslMarker
public annotation class StyleSheetDsl

@StyleSheetDsl
public data class RuleBuilder @PublishedApi internal constructor(
    val styleSheetBuilder: StyleSheetBuilder,
    val selector: Selector,
    val style: Style.Builder
) : SelectorTarget by Selectors, Style.Builder by style {

    public inline operator fun Selector.invoke(styleBuilder: RuleBuilder.() -> Unit) {
        with(this@RuleBuilder.styleSheetBuilder) {
            (this@RuleBuilder.selector then this@invoke)(styleBuilder)
        }
    }
}

@StyleSheetDsl
public class StyleSheetBuilder internal constructor(
    @PublishedApi
    internal val rules: MutableList<Rule> = mutableListOf()
) : SelectorTarget by Selectors, List<Rule> by rules {

    public inline operator fun Selector.invoke(styleBuilder: RuleBuilder.() -> Unit) {

        val builder = RuleBuilder(this@StyleSheetBuilder, this, Style.style()).apply(styleBuilder)

        builder.style.build().takeUnless { it.isEmpty }?.let {
            this@StyleSheetBuilder.rules += RuleImpl(this, it)
        }
    }

    public fun build(): StyleSheet = StyleSheet(rules)
}

public fun stylesheet(builder: StyleSheetBuilder.() -> Unit): StyleSheet = StyleSheetBuilder().apply(builder).build()
