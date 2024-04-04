package me.lucyydotp.component.selector

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent

internal data class PrefixSelector(
    val prefix: String,
    val trim: Boolean = true,
) : AbstractSelector() {
    override fun matches(component: List<Component>): Boolean = (component.last() as? TextComponent)
        ?.content()
        ?.let { if (trim) it.trim() else it }
        ?.startsWith(prefix) == true

}

