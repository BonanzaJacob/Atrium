package ch.tutteli.atrium.api.cc.en_GB

import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.domain.builders.utils.GroupWithNullableEntries
import ch.tutteli.atrium.domain.builders.utils.GroupWithoutNullableEntries
import ch.tutteli.kbox.glue

/**
 * Parameter object to express a [GroupWithoutNullableEntries] with a single identification lambda.
 */
class Entry<in T : Any>(
    val assertionCreator: Assert<T>.() -> Unit
) : GroupWithoutNullableEntries<Assert<T>.() -> Unit> {
    override fun toList(): List<Assert<T>.() -> Unit> = listOf(assertionCreator)
}

/**
 * Parameter object to express a [GroupWithNullableEntries] with a single nullable identification lambda.
 *
 * In case `null` is used for the identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 */
class NullableEntry<in T : Any>(
    val assertionCreator: (Assert<T>.() -> Unit)?
) : GroupWithNullableEntries<(Assert<T>.() -> Unit)?> {
    override fun toList(): List<(Assert<T>.() -> Unit)?> = listOf(assertionCreator)
}

/**
 * Parameter object to express a [GroupWithoutNullableEntries] of identification lambdas.
 */
class Entries<in T : Any>(
    val assertionCreator: Assert<T>.() -> Unit,
    vararg val otherAssertionCreators: Assert<T>.() -> Unit
) : GroupWithoutNullableEntries<Assert<T>.() -> Unit> {
    override fun toList(): List<Assert<T>.() -> Unit> = assertionCreator glue otherAssertionCreators
}

/**
 * Parameter object to express a [GroupWithNullableEntries] of nullable identification lambdas.
 *
 * In case `null` is used for an identification lambda then it is expected that the corresponding entry
 * is `null` as well.
 */
class NullableEntries<in T : Any>(
    val assertionCreator: (Assert<T>.() -> Unit)?,
    vararg val otherAssertionCreators: (Assert<T>.() -> Unit)?
) : GroupWithNullableEntries<(Assert<T>.() -> Unit)?> {
    override fun toList(): List<(Assert<T>.() -> Unit)?> = assertionCreator glue otherAssertionCreators
}

/**
 * Represents a [GroupWithoutNullableEntries] with a single value.
 */
data class Value<T : Any>(val expected: T) : GroupWithoutNullableEntries<T> {
    override fun toList() = listOf(expected)
}

/**
 * Represents a [GroupWithNullableEntries] with a single nullable value.
 */
data class NullableValue<T : Any?>(val expected: T) : GroupWithNullableEntries<T> {
    override fun toList() = listOf(expected)
}


/**
 * Represents a [GroupWithoutNullableEntries] of multiple values.
 */
class Values<T : Any>(private val expected: T, vararg val otherExpected: T) : GroupWithoutNullableEntries<T> {
    override fun toList() = listOf(expected, *otherExpected)
}


/**
 * Represents a [GroupWithNullableEntries] of multiple nullable values.
 */
class NullableValues<T : Any?>(private val expected: T, vararg val otherExpected: T) : GroupWithNullableEntries<T> {
    override fun toList() = listOf(expected, *otherExpected)
}
