package me.lucyydotp.component.selector

import net.kyori.adventure.text.Component

internal data class MatchParentSelector(
    val parentSelector: Selector,
    val childSelector: Selector,
) : AbstractSelector() {
    override fun matches(component: List<Component>): Boolean {
        if (!childSelector.matches(component)) return false
        if (component.size <= 1) return false
        return parentSelector.matches(component.subList(0, component.size - 1))
    }

}
