package me.lucyydotp.component.selector

import net.kyori.adventure.text.Component

public interface Selector : SelectorTarget {
    public fun matches(component: List<Component>): Boolean

    public infix fun or(other: Selector): Selector = UnionSelector(listOf(this, other))

    public infix fun and(other: Selector): Selector = IntersectionSelector(listOf(this, other))
}
