package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.creating.AssertMarker
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.api.cc.de_CH.assertions.iterable.contains.builders.IterableContainsNotCheckerBuilder as DeprecatedNotCheckerBuilder
import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsBuilder as DeprecatedBuilder

/**
 * Creates an [IterableContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains` assertions.
 *
 * @return The newly created builder.
 */
@AssertMarker
val <E, T : Iterable<E>> Assert<T>.enthaelt: IterableContains.Builder<E, T, NoOpSearchBehaviour>
    get() = AssertImpl.iterable.containsBuilder(this)

@Deprecated("Use the extension fun `enthaelt` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("plant.enthaelt"))
fun <E, T : Iterable<E>> getEnthaelt(plant: Assert<T>): DeprecatedBuilder<E, T, NoOpSearchBehaviour>
    = DeprecatedBuilder(plant, plant.enthaelt.searchBehaviour)


/**
 * Creates an [IterableContains.Builder] based on this [AssertionPlant] which allows to define
 * more sophisticated `contains not` assertions.
 *
 * @return The newly created builder.
 */
@AssertMarker
val <E, T : Iterable<E>> Assert<T>.enthaeltNicht: NotCheckerOption<E, T, InAnyOrderSearchBehaviour>
    get() = NotCheckerOptionImpl(AssertImpl.iterable.containsNotBuilder(this))

@Deprecated("Use the extension fun `enthaeltNicht` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("plant.enthaeltNicht"))
fun <E, T : Iterable<E>> getEnthaeltNicht(plant: Assert<T>): DeprecatedNotCheckerBuilder<E, T>
    = DeprecatedNotCheckerBuilder(AssertImpl.iterable.containsNotBuilder(plant))


/**
 * Makes the assertion that [AssertionPlant.subject] contains [expected] and the [otherExpected] (if defined).
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest(1).werte(expected, *otherExpected)`
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and [expected] is
 * defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same entry. Use an option such as [zumindest], [hoechstens] and [genau] to control
 * the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `enthaelt.inBeliebigerReihenfolge.genau(2).wert('a')`
 * instead of:
 *   `enthaelt('a', 'a')`
 *
 * This function will be renamed on a JVM level from `enthaeltNonNullable` to `enthaelt` with 1.0.0
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
@JvmName("enthaeltNonNullable")
fun <E : Any, T : Iterable<E>> Assert<T>.enthaelt(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = enthaelt.inBeliebigerReihenfolge.zumindest(1).werte(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] contains the [expected] nullable value.
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest(1).nullableWert(expected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <E: Any?, T: Iterable<E>> Assert<T>.enthaeltNullableWert(expected: E): AssertionPlant<T>
    = enthaelt.inBeliebigerReihenfolge.zumindest(1).nullableWert(expected)

/**
 * Makes the assertion that [AssertionPlant.subject] contains the [expected] nullable value
 * and the [otherExpected] nullable values (if defined).
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest(1).nullableWerte(expected, *otherExpected)`
 *
 * Notice, that it does not search for unique matches. Meaning, if the iterable is `setOf('a', 'b')` and [expected] is
 * defined as `'a'` and one [otherExpected] is defined as `'a'` as well, then both match, even though they match the
 * same entry. Use an option such as [atLeast], [atMost] and [exactly] to control the number of occurrences you expect.
 *
 * Meaning you might want to use:
 *   `enthaelt.inBeliebigerReihenfolge.genau(2).nullableWert('a')`
 * instead of:
 *   `enthaelt.nullableWerte('a', 'a')`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <E: Any?, T: Iterable<E>> Assert<T>.enthaeltNullableWerte(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = enthaelt.inBeliebigerReihenfolge.zumindest(1).nullableWerte(expected, *otherExpected)

@Deprecated("Use `enthaeltNullableWert/enthaeltNullableWerte` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("enthaeltNullableWerte(expected, *otherExpected)"))
fun  <E: Any?, T : Iterable<E>> Assert<T>.enthaelt(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = enthaeltNullableWerte(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator] -- likewise an entry (can be the same) is searched for each
 * of the [otherAssertionCreators].
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest(1).eintraege(assertionCreator, *otherAssertionCreators)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <E : Any, T : Iterable<E>> Assert<T>.enthaelt(assertionCreator: Assert<E>.() -> Unit, vararg otherAssertionCreators: Assert<E>.() -> Unit): AssertionPlant<T>
    = enthaelt.inBeliebigerReihenfolge.zumindest(1).eintraege(assertionCreator, *otherAssertionCreators)

/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator] or an entry which is `null` in case [assertionCreator] is `null` as well.
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest(1).nullableEintrag(assertionCreator)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <E: Any, T: Iterable<E?>> Assert<T>.enthaeltNullableEintrag(assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaelt.inBeliebigerReihenfolge.zumindest(1).nullableEintrag(assertionCreator)

/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator] or an entry which is `null` in case [assertionCreator] is `null`
 * as well -- likewise an entry (can be the same) is searched for each of the [otherAssertionCreators].
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest(1).nullableEintraege(assertionCreator, *otherAssertionCreators)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <E: Any, T: Iterable<E?>> Assert<T>.enthaeltNullableEintraege(assertionCreator: (Assert<E>.() -> Unit)?, vararg otherAssertionCreators: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaelt.inBeliebigerReihenfolge.zumindest(1).nullableEintraege(assertionCreator, *otherAssertionCreators)

/**
 * Makes the assertion that [AssertionPlant.subject] contains an entry holding the assertions created by the
 * [assertionCreator] or an entry which is `null` in case [assertionCreator] is `null`
 * as well -- likewise an entry (can be the same) is searched for each
 * of the [otherAssertionCreators].
 *
 * It is a shortcut for `enthaelt.inBeliebigerReihenfolge.zumindest(1).eintraege(assertionCreator, *otherAssertionCreators)`
 *
 * This function will be renamed on a JVM level from `enthaelt?` to `enthaeltNullable` with 1.0.0
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@JvmName("enthaelt?")
@Deprecated("Use `enthaeltNullableEintrag/enthaeltNullableEintraege` instead.", ReplaceWith("enthaeltNullableEintraege(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E?>> Assert<T>.enthaelt(assertionCreator: (Assert<E>.() -> Unit)?, vararg otherAssertionCreators: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaeltNullableEintraege(assertionCreator, *otherAssertionCreators)

@Deprecated("Use `enthaeltNullableEintraege`, will be removd with 1.0.0", ReplaceWith("enthaeltNullableEintraege(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E?>> enthaeltNullable(plant: Assert<T>, assertionCreator: (Assert<E>.() -> Unit)?, vararg otherAssertionCreators: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = plant.enthaeltNullableEintraege(assertionCreator, *otherAssertionCreators)


/**
 * Makes the assertion that [AssertionPlant.subject] contains only [expected] and the [otherExpected] (if defined) in
 * the defined order.
 *
 * It is a shortcut for `enthaelt.inGegebenerReihenfolge.nur.werte(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <E : Any, T : Iterable<E>> Assert<T>.enthaeltStrikt(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = enthaelt.inGegebenerReihenfolge.nur.werte(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] contains only the [expected] nullable value.
 *
 * It is a shortcut for `enthaelt.inGegebenerReihenfolge.nur.nullableWert(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <E : Any?, T : Iterable<E>> Assert<T>.enthaeltStriktNullableWert(expected: E): AssertionPlant<T>
    = enthaelt.inGegebenerReihenfolge.nur.nullableWert(expected)

/**
 * Makes the assertion that [AssertionPlant.subject] contains only the [expected] nullable value
 * and the [otherExpected] nullable values (if defined) in the defined order.
 *
 * It is a shortcut for `enthaelt.inGegebenerReihenfolge.nur.nullableWerte(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <E : Any?, T : Iterable<E>> Assert<T>.enthaeltStriktNullableWerte(expected: E, vararg otherExpected: E): AssertionPlant<T>
    = enthaelt.inGegebenerReihenfolge.nur.nullableWerte(expected, *otherExpected)

/**
 * Makes the assertion that [AssertionPlant.subject] contains only an entry holding the assertions created by the
 * [assertionCreator] and an additional entry for each [otherAssertionCreators] (if defined) in the defined order
 * holding the assertions created by them.
 *
 * It is a shortcut for `enthaelt.inGegebenerReihenfolge.nur.eintraege(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <E : Any, T : Iterable<E>> Assert<T>.enthaeltStrikt(assertionCreator: Assert<E>.() -> Unit, vararg otherAssertionCreators: Assert<E>.() -> Unit): AssertionPlant<T>
    = enthaelt.inGegebenerReihenfolge.nur.eintraege(assertionCreator, *otherAssertionCreators)

/**
 * Makes the assertion that [AssertionPlant.subject] contains only an entry holding the assertions created by the
 * [assertionCreator] where the entry needs to be `null` in case [assertionCreator]
 * is `null`.
 *
 * It is a shortcut for `enthaelt.inGegebenerReihenfolge.nur.eintrag(expected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <E : Any, T : Iterable<E?>> Assert<T>.enthaeltStriktNullableEintrag(assertionCreator: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaelt.inGegebenerReihenfolge.nur.nullableEintrag(assertionCreator)

/**
 * Makes the assertion that [AssertionPlant.subject] contains only an entry holding the assertions created by the
 * [assertionCreator] where the entry needs to be `null` in case [assertionCreator]
 * is `null` and an additional entry for each [otherAssertionCreators] (if defined) whereas the entries
 * have to appear in the defined order.
 *
 * It is a shortcut for `enthaelt.inGegebenerReihenfolge.nur.eintraege(expected, *otherExpected)`
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
fun <E : Any, T : Iterable<E?>> Assert<T>.enthaeltStriktNullableEintraege(assertionCreator: (Assert<E>.() -> Unit)?, vararg otherAssertionCreators: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaelt.inGegebenerReihenfolge.nur.nullableEintraege(assertionCreator, *otherAssertionCreators)

@JvmName("enthaeltStrikt?")
@Deprecated("Use `enthaeltStriktNullableEintrag/enthaeltStriktNullableEintraege` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("enthaeltStriktNullableEintraege(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E?>> Assert<T>.enthaeltStrikt(assertionCreator: (Assert<E>.() -> Unit)?, vararg otherAssertionCreators: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = enthaeltStriktNullableEintraege(assertionCreator, *otherAssertionCreators)

@Deprecated("Use the extension fun `enthaeltStrikt` instead, will be removed with 1.0.0", ReplaceWith("plant.enthaeltStrikt(assertionCreator, *otherAssertionCreators)"))
fun <E : Any, T : Iterable<E?>> enthaeltStriktNullable(plant: Assert<T>, assertionCreator: (Assert<E>.() -> Unit)?, vararg otherAssertionCreators: (Assert<E>.() -> Unit)?): AssertionPlant<T>
    = plant.enthaeltStrikt(assertionCreator, *otherAssertionCreators)


/**
 * Makes the assertion that [AssertionPlant.subject] does not contain the [expected] value
 * and neither one of the [otherExpected] values (if defined).
 *
 * It is a shortcut for `enthaeltNicht.werte(expected, *otherExpected)`
 *
 * This function will be renamed on a JVM level from `enthaeltNichtNonNullable` to `enthaeltNicht` with 1.0.0
 *
 * @return This plant to support a fluent API.
 * @throws AssertionError Might throw an [AssertionError] if the assertion made is not correct.
 */
@AssertMarker
@JvmName("enthaeltNichtNonNullable")
fun <E: Any, T : Iterable<E>> Assert<T>.enthaeltNicht(expected: E, vararg otherExpected: E)
    = enthaeltNicht.werte(expected, *otherExpected)

@Deprecated("Use `enthaeltNicht.nullableValues` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0", ReplaceWith("enthaeltNicht.nullableValues(expected, *otherExpected)"))
fun <E, T : Iterable<E>> Assert<T>.enthaeltNicht(expected: E, vararg otherExpected: E)
    = enthaeltNicht.werte(expected, *otherExpected)
