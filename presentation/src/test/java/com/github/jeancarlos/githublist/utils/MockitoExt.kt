package com.github.jeancarlos.githublist.utils

/**
 * This class represents a extension for testing with Kotlin classes
 * Reference: <a>https://goo.gl/eqw2MJ</a>
 */

import org.mockito.Mockito

/**
 * Similar to mockito any matcher, but it is not nullable.
 */
fun <T> kAny(): T {
    Mockito.any<T>()
    return uninitialized()
}

/**
 * Similar to mockito any matcher, but it is not nullable.
 */
fun <T> kEq(value: T): T {
    Mockito.eq(value)
    return value
}

/**
 * Forces a nullable to be an instance.
 */
fun <T> uninitialized(): T = null as T