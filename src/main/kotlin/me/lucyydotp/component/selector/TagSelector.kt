package me.lucyydotp.component.selector

import me.lucyydotp.component.tag.TaggedComponent
import net.kyori.adventure.text.Component

internal data class TagSelector(
    val tag: String
) : AbstractSelector() {
    override fun matches(component: List<Component>): Boolean = component.last().let {
        it is TaggedComponent && tag in it.tags
    }

}
