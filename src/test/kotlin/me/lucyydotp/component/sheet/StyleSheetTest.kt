package me.lucyydotp.component.sheet

import me.lucyydotp.component.dsl.stylesheet
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import kotlin.test.Test
import kotlin.test.assertEquals

val stylesheet = stylesheet {
    withPrefix["green"] {
        color(NamedTextColor.GREEN)
    }

    withPrefix["red"] {
        color(NamedTextColor.RED)

        withPrefix["green"] {
            color(NamedTextColor.DARK_AQUA)
        }
    }
}

class StyleSheetTest {
    @Test
    fun `selectors apply at root level`() {
        val appliedText = stylesheet.apply(text("red"))
        assertEquals(NamedTextColor.RED, appliedText.color())
    }

    @Test
    fun `selectors apply to children`() {
        val appliedText = stylesheet.apply(
            text()
                .append(text("red"))
                .append(text("green"))
        )

        // Check it hasn't applied to the root component
        assertEquals(null, appliedText.color())

        // Check each component is styled correctly
        assertEquals(NamedTextColor.RED, appliedText.children()[0].color())
        assertEquals(NamedTextColor.GREEN, appliedText.children()[1].color())
    }

    @Test
    fun `selectors specified later are applied last`() {
        val appliedText = stylesheet.apply(
            text()
                .append(text("red").append(text("green")))
                .append(text("green"))
        )

        assertEquals(NamedTextColor.RED, appliedText.children()[0].color())
        assertEquals(NamedTextColor.GREEN, appliedText.children()[1].color())
        assertEquals(NamedTextColor.DARK_AQUA, appliedText.children()[0].children()[0].color())
    }
}
