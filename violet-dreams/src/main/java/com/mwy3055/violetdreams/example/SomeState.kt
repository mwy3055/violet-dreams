package com.mwy3055.violetdreams.example

import androidx.compose.runtime.Stable

/**
 * Example of how to hoist state when developing a Compose library.
 * See https://android.googlesource.com/platform/frameworks/support/+/androidx-main/compose/docs/compose-api-guidelines.md#hoisted-state-types
 */

/**
 * State interface
 */
@Stable
interface SomeState {
    var someValue1: Int
    var someValue2: Int
}

/**
 * Default implementation of State
 */
fun SomeState(): SomeState = SomeStateImpl()

/**
 * Implementation of the State class by the library developer.
 * Library users can define their own implementation if needed.
 */
private class SomeStateImpl(someValue1: Int = 0, someValue2: Int = 0) : SomeState {

    override var someValue1: Int = someValue1
        get() = field
        set(value) {
            field = value + 1
        }

    override var someValue2: Int = someValue2
        get() = field
        set(value) {
            field = value * 2
        }

}