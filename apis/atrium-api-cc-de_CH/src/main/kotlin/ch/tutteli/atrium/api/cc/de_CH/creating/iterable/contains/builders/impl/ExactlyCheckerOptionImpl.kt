package ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.impl

import ch.tutteli.atrium.api.cc.de_CH.creating.iterable.contains.builders.ExactlyCheckerOption
import ch.tutteli.atrium.api.cc.de_CH.genau
import ch.tutteli.atrium.domain.builders.creating.iterable.contains.builders.ExactlyCheckerOptionBase
import ch.tutteli.atrium.domain.creating.iterable.contains.IterableContains
import ch.tutteli.atrium.domain.creating.iterable.contains.searchbehaviours.InAnyOrderSearchBehaviour

/**
 * Represents the builder of a `contains exactly` check within the fluent API of a sophisticated
 * `contains` assertion for [Iterable].
 *
 * @param T The input type of the search.
 *
 * @constructor Represents the builder of a `contains exactly` check within the fluent API of a sophisticated
 *   `contains` assertion for [Iterable].
 * @param times The number which the check will compare against the actual number of times an expected entry is
 *   found in the [Iterable].
 * @param containsBuilder The previously used [IterableContains.Builder].
 */
@Deprecated("Do not rely on this type, will be made internal with 1.0.0", ReplaceWith("ExactlyCheckerBuilder"))
open class ExactlyCheckerOptionImpl<out E, out T : Iterable<E>, out S : InAnyOrderSearchBehaviour>(
    times: Int,
    containsBuilder: IterableContains.Builder<E, T, S>
) : ExactlyCheckerOptionBase<E, T, S>(
    times,
    containsBuilder,
    nameContainsNotValuesFun(),
    { "${containsBuilder::genau.name}($it)" }
), ExactlyCheckerOption<E, T, S>
