package me.lucyydotp.component.tag

import net.kyori.adventure.text.Component

// TODO: this is really horrid and probably breaks most of adventure
//  use a virtual component when they become a thing
@Suppress("NonExtendableApiUsage")
public data class TaggedComponent(
    val component: Component,
    val tags: List<String>,
) : Component by component

public infix fun Component.tagged(tag: String): Component = if (this is TaggedComponent) {
    TaggedComponent(component, tags + tags)
} else {
    TaggedComponent(this, listOf(tag))
}
