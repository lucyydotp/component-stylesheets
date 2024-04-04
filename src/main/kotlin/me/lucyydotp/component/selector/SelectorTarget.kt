package me.lucyydotp.component.selector

import me.lucyydotp.component.dsl.StyleSheetDsl
import me.lucyydotp.component.util.Getter

@StyleSheetDsl
public interface SelectorTarget {
    public val withPrefix: Getter<String, Selector>
    public val withTag: Getter<String, Selector>

    public infix fun then(childSelector: Selector): Selector
}

internal abstract class SelectorTargetImpl : SelectorTarget {

    abstract val selector: Selector?

    private fun addToChain(nextSelector: Selector) = selector?.let { it and nextSelector } ?: nextSelector

    override val withPrefix = Getter<String, Selector> { addToChain(PrefixSelector(it, false)) }
    override val withTag = Getter<String, Selector> { addToChain(TagSelector(it)) }

    override fun then(childSelector: Selector): Selector = MatchParentSelector(
        requireNotNull(this.selector) { "Cannot chain a null selector" },
        childSelector,
    )
}

public val Selectors: SelectorTarget = object : SelectorTargetImpl() {
    override val selector: Selector? = null
}
