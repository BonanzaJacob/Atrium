package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertMarker
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable
import ch.tutteli.atrium.api.cc.de_CH.assertions.charsequence.contains.builders.CharSequenceContainsNotCheckerBuilder as DeprecatedNotCheckerBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsBuilder as DeprecatedBuilder
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNoOpSearchBehaviour as DeprecatedNoOpSearchBehaviour
import ch.tutteli.atrium.assertions.charsequence.contains.searchbehaviours.CharSequenceContainsNotSearchBehaviour as DeprecatedNotSearchBehaviour

/**
 * Creates a [CharSequenceContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder.
 */
@AssertMarker
val <T : CharSequence> Assert<T>.enthaelt: CharSequenceContains.Builder<T, NoOpSearchBehaviour>
    get() = AssertImpl.charSequence.containsBuilder(this)

@Deprecated("Use the extension fun `enthaelt` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("plant.enthaelt"))
fun <T : CharSequence> getEnthaelt(plant: Assert<T>): DeprecatedBuilder<T, NoOpSearchBehaviour>
    = DeprecatedBuilder(plant, plant.enthaelt.searchBehaviour)


/**
 * Creates a [CharSequenceContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @return The newly created builder.
 */
@AssertMarker
val <T : CharSequence> Assert<T>.enthaeltNicht: NotCheckerOption<T, NotSearchBehaviour>
    get() = NotCheckerOptionImpl(
        AssertImpl.charSequence.containsNotBuilder(
            this
        )
    )

@Deprecated("Use the extension fun `enthaeltNicht` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("plant.enthaeltNicht"))
fun <T : CharSequence> getEnthaeltNicht(plant:  Assert<T>): DeprecatedNotCheckerBuilder<T, NotSearchBehaviour>
    = DeprecatedNotCheckerBuilder(AssertImpl.charSequence.containsNotBuilder(plant))


/**
 * Makes the assertion that [AssertionPlant.subject] contains [expected]'s [toString] representation
 * and the [toString] representation of the [otherExpected] (if defined), using a non disjoint search.
 *
 * It is a shortcut for `enthaelt.zumindest(1).werte(expected, *otherExpected)`.
 *
 * Notice that a runtime check applies which assures that only [CharSequence], [Number] and [Char] are passed (this
 * function expects `Any` for your convenience, so that you can mix [String] and [Int] for instance).
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and [expected]
 * is defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use the property `enthaelt` to create a more sophisticated `contains`
 * assertion where you can use options such as [zumindest], [hoechstens] and [genau] to control the number of
 * occurrences you expect.
 *
 * Meaning you might want to use:
 *   `enthaelt.genau(2).wert('a')`
 * instead of:
 *   `enthaelt('a', 'a')`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 * @throws IllegalArgumentException in case [expected] or one of the [otherExpected] is not a
 *   [CharSequence], [Number] or [Char].
 */
@AssertMarker
fun <T : CharSequence> Assert<T>.enthaelt(expected: Any, vararg otherExpected: Any): AssertionPlant<T>
    = enthaelt.zumindest(1).werte(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] does not contain [expected]'s [toString] representation
 * and neither one of the [otherExpected]'s [toString] representation (if defined).
 *
 * It is a shortcut for `enthaeltNicht.werte(expected, *otherExpected)`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <T : CharSequence> Assert<T>.enthaeltNicht(expected: Any, vararg otherExpected: Any)
    = enthaeltNicht.werte(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] contains [expected]'s [getDefault][Translatable.getDefault]
 * representation and the [getDefault][Translatable.getDefault] representations of the [otherExpected] (if defined),
 * using a non disjoint search.
 *
 * It is a shortcut for `enthaelt.zumindest(1).standardUebersetzungVon(expected, *otherExpected)`.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'a'` and the
 * default translation of [expected] is defined as `'a'` and one default translation of the
 * [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same sequence in the input of the search. Use an option such as [zumindest], [hoechstens] and [genau] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `enthaelt.genau(2).standardUebersetzungVon(IS)`
 * instead of:
 *   `enthaeltStandardUebersetzungVon(IS, IS)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <T : CharSequence> Assert<T>.enthaeltStandardUebersetzungVon(expected: Translatable, vararg otherExpected: Translatable): AssertionPlant<T>
    = enthaelt.zumindest(1).standardUebersetzungVon(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] does  not contain [expected]'s
 * [getDefault][Translatable.getDefault] representation and neither one of the [otherExpected]'s
 * [getDefault][Translatable.getDefault] representation (if defined).
 *
 * It is a shortcut for `enthaeltNicht.standardUebersetzungVon(expected, *otherExpected)`.
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <T : CharSequence> Assert<T>.enthaeltNichtDieStandardUebersetzungVon(expected: Translatable, vararg otherExpected: Translatable)
    = enthaeltNicht.standardUebersetzungVon(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] contains a sequence which matches the given regular expression
 * [pattern] as well as the [otherPatterns] (if defined), using a non disjoint search.
 *
 * It is a shortcut for `enthaelt.zumindest(1).regex(pattern, *otherPatterns)`.
 *
 * By non disjoint is meant that `'aa'` in `'aaaa'` is found three times and not only two times.
 * Also notice, that it does not search for unique matches. Meaning, if the input of the search is `'ab'` and [pattern]
 * is defined as `'a(b)?'` and one of the [otherPatterns] is defined as `'a(b)?'` as well, then both match, even though
 * they match the same sequence in the input of the search. Use an option such as [zumindest], [hoechstens] and [genau]
 * to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `enthaelt.genau(2).regex('a(b)?')`
 * instead of:
 *   `enthaelt.zumindest(1).regex('a(b)?', 'a(b)?')`
 *
 * @param pattern The pattern which is expected to have a match against the input of the search.
 * @param otherPatterns Additional patterns which are expected to have a match against the input of the search.
 *
 * @return The [AssertionPlant] for which the assertion was built to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <T : CharSequence> Assert<T>.enthaeltRegex(pattern: String, vararg otherPatterns: String): AssertionPlant<T>
    = enthaelt.zumindest(1).regex(pattern, *otherPatterns)

/**
 * Makes the assertion that [AssertionPlant.subject] starts with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <T : CharSequence> Assert<T>.beginntMit(expected: CharSequence)
    = addAssertion(AssertImpl.charSequence.startsWith(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] does not start with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <T : CharSequence> Assert<T>.beginntNichtMit(expected: CharSequence)
    = addAssertion(AssertImpl.charSequence.startsNotWith(this, expected))


/**
 * Makes the assertion that [AssertionPlant.subject] ends with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <T : CharSequence> Assert<T>.endetMit(expected: CharSequence)
    = addAssertion(AssertImpl.charSequence.endsWith(this, expected))

/**
 * Makes the assertion that [AssertionPlant.subject] does not end with [expected].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <T : CharSequence> Assert<T>.endetNichtMit(expected: CharSequence)
    = addAssertion(AssertImpl.charSequence.endsNotWith(this, expected))


/**
 * Makes the assertion that [AssertionPlant.subject] [CharSequence].[kotlin.text.isEmpty].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <T : CharSequence> Assert<T>.istLeer()
    = addAssertion(AssertImpl.charSequence.isEmpty(this))

/**
 * Makes the assertion that [AssertionPlant.subject] [CharSequence].[kotlin.text.isNotEmpty].
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <T : CharSequence> Assert<T>.istNichtLeer()
    = addAssertion(AssertImpl.charSequence.isNotEmpty(this))
