package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
/**
 * Makes the assertion that [AssertionPlant.subject] is less than [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.isLessThan(expected)"))
infix fun <T : Comparable<T>> Assert<T>.isLessThan(expected: T)
    = addAssertion(AssertImpl.comparable.isLessThan(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] is less than or equals [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.isLessOrEquals(expected)"))
infix fun <T : Comparable<T>> Assert<T>.isLessOrEquals(expected: T)
    = addAssertion(AssertImpl.comparable.isLessOrEquals(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] is greater than [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.isGreaterThan(expected)"))
infix fun <T : Comparable<T>> Assert<T>.isGreaterThan(expected: T)
    = addAssertion(AssertImpl.comparable.isGreaterThan(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] is greater than or equals [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@Deprecated("Use pendant from package en_GB, will be removed with 1.0.0", ReplaceWith("ch.tutteli.atrium.api.cc.infix.en_GB.isGreaterOrEquals(expected)"))
infix fun <T : Comparable<T>> Assert<T>.isGreaterOrEquals(expected: T)
    = addAssertion(AssertImpl.comparable.isGreaterOrEquals(this, expected))

