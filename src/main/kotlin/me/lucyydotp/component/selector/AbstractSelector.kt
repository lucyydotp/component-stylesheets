package me.lucyydotp.component.selector

internal abstract class AbstractSelector : SelectorTargetImpl(), Selector {
    override val selector: Selector = this
}
