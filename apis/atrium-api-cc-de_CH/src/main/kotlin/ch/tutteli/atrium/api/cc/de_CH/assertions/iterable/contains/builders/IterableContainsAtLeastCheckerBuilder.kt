package ch.tutteli.atrium.api.cc.de_CH.assertions.iterable.contains.builders

import ch.tutteli.atrium.assertions.iterable.contains.builders.IterableContainsCheckerBuilder
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour

/**
 *  Represents the *deprecated* builder of a `contains at least` check within the fluent API of a sophisticated
 * `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 *
 * @constructor Represents the builder of a `contains at least` check within the fluent API of a sophisticated
 *   `contains` assertion for [Iterable].
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 * @param containsBuilder The previously used [IterableContains.Builder].
 */
@Deprecated(
    "use the builder from the package creating, will be removed with 1.0.0",
    ReplaceWith(
        "AtLeastCheckerOption",
        "ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.AtLeastCheckerOption"
    )
)
open class IterableContainsAtLeastCheckerBuilder<out E, out T : Iterable<E>>(
    times: Int,
    containsBuilder: IterableContains.Builder<E, T, InAnyOrderSearchBehaviour>
) : ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.impl.AtLeastCheckerOptionImpl<E, T, InAnyOrderSearchBehaviour>(
    times, containsBuilder
), IterableContainsCheckerBuilder<E, T, InAnyOrderSearchBehaviour>
