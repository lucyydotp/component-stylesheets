package me.lucyydotp.component.selector

import net.kyori.adventure.text.Component

internal data class IntersectionSelector(
    val selectors: List<Selector>
) : AbstractSelector() {
    override fun matches(component: List<Component>): Boolean = selectors.all { it.matches(component) }

    override fun and(other: Selector): Selector = IntersectionSelector(this.selectors + other)

}
