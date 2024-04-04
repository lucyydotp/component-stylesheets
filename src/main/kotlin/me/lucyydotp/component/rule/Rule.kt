package me.lucyydotp.component.rule

import me.lucyydotp.component.selector.Selector
import net.kyori.adventure.text.format.Style

public interface Rule {
    public val selector: Selector
    public val style: Style
}
