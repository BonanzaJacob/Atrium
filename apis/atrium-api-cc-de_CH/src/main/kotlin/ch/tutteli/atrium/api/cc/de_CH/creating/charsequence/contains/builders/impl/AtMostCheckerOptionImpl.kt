package ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders.impl

import ch.tutteli.atrium.api.cc.de_CH.creating.charsequence.contains.builders.AtMostCheckerOption
import ch.tutteli.atrium.api.cc.de_CH.genau
import ch.tutteli.atrium.api.cc.de_CH.hoechstens
import ch.tutteli.atrium.api.cc.de_CH.zumindest
import ch.tutteli.atrium.domain.builders.creating.charsequence.contains.builders.AtMostCheckerOptionBase
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains

/**
 * Represents the builder of a `contains at least once but at most` check within the fluent API of a
 * sophisticated `contains` assertion for [CharSequence].
 *
 * @param T The input type of the search.
 * @param S The search behaviour which should be applied for the input of the search.
 *
 * @constructor Represents the builder of a `contains at least once but at most` check within the fluent API of a
 *   sophisticated `contains` assertion for [CharSequence].
 * @param times The number which the check will compare against the actual number of times an expected object is
 *   found in the input of the search.
 * @param containsBuilder The previously used [CharSequenceContains.Builder].
 */
@Deprecated("Do not rely on this type, will be made internal with 1.0.0", ReplaceWith("AtMostCheckerBuilder"))
open class AtMostCheckerOptionImpl<out T : CharSequence, out S : CharSequenceContains.SearchBehaviour>(
    times: Int,
    containsBuilder: CharSequenceContains.Builder<T, S>
) : AtMostCheckerOptionBase<T, S>(
    times,
    containsBuilder,
    nameContainsNotValuesFun(),
    { "${containsBuilder::hoechstens.name}($it)" },
    { "${containsBuilder::zumindest.name}($it)" },
    { "${containsBuilder::genau.name}($it)" }
), AtMostCheckerOption<T, S>
