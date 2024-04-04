package me.lucyydotp.component.selector

import net.kyori.adventure.text.Component

internal data class UnionSelector(
    val selectors: List<Selector>
) : AbstractSelector() {
    override fun matches(component: List<Component>): Boolean = selectors.any { it.matches(component) }

    override fun or(other: Selector): Selector = UnionSelector(this.selectors + other)

}
