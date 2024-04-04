package me.lucyydotp.component.sheet

import me.lucyydotp.component.rule.Rule
import me.lucyydotp.component.tag.TaggedComponent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.ComponentLike
import net.kyori.adventure.text.format.Style

public data class StyleSheet(
    public val rules: List<Rule>
) : List<Rule> by rules {

    private fun matchAndApplyRules(componentChain: List<Component>): Component {
        val rulesToUse = rules.filter { it.selector.matches(componentChain) }
        val last = componentChain.last()

        return last
            .style(rulesToUse.fold(Style.style()) { style, rule -> style.merge(rule.style) }.merge(last.style()))
            .children(last.children().map { matchAndApplyRules(componentChain + it) })
    }

    private fun unwrapTaggedComponent(component: Component) = when (component) {
        is TaggedComponent -> component.component
        else -> component
    }

    private fun Component.removeTheJank(): Component {
        val unwrapped = unwrapTaggedComponent(this)
        if (unwrapped.children().isEmpty()) return unwrapped
        return unwrapped.children(unwrapped.children().map { it.removeTheJank() })
    }

    public fun apply(component: ComponentLike): Component =
        matchAndApplyRules(listOf(component.asComponent()))
            .compact()
            .removeTheJank()
}
