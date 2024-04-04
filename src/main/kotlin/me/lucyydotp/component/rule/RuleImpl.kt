package me.lucyydotp.component.rule

import me.lucyydotp.component.selector.Selector
import net.kyori.adventure.text.format.Style

@PublishedApi
internal data class RuleImpl(
    override val selector: Selector,
    override val style: Style
) : Rule
