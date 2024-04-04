package me.lucyydotp.component.sheet

import me.lucyydotp.component.dsl.stylesheet
import me.lucyydotp.component.tag.tagged
import net.kyori.adventure.text.Component.text
import net.kyori.adventure.text.format.NamedTextColor
import net.kyori.adventure.text.format.TextDecoration
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

val sheet = stylesheet {
    withTag["subtitle"] {
        decorate(TextDecoration.BOLD)
    }

    withTag["title"] {
        color(NamedTextColor.LIGHT_PURPLE)

        withTag["subtitle"] {
            color(NamedTextColor.DARK_PURPLE)
        }
    }
}

class TaggedStylingTest {
    @Test
    fun `tagged selectors work`() {
        val text = text {
            it.append(text("outer subtitle") tagged "subtitle")
            it.append(text {
                it.append(text("plain"))
                it.append(text("subtitle") tagged "subtitle")
            } tagged "title")
        }

        val appliedText = sheet.apply(text)

        assert(appliedText.style().isEmpty)

        // Check the outer subtitle is bolded but not coloured
        assert(appliedText.children()[0].decoration(TextDecoration.BOLD) == TextDecoration.State.TRUE)
        assertNotEquals(NamedTextColor.DARK_PURPLE, appliedText.children()[0].color())

        // Check the inner text is styled properly
        assertEquals(NamedTextColor.LIGHT_PURPLE, appliedText.children()[1].color())
        assertEquals(NamedTextColor.DARK_PURPLE, appliedText.children()[1].children()[0].color())
    }
}
