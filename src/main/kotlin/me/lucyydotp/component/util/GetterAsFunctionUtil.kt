package me.lucyydotp.component.util

public fun interface Getter<A, T> {
    public operator fun get(arg: A): T
}
