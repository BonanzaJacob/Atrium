package ch.tutteli.atrium.api.cc.infix.en_GB

import ch.tutteli.atrium.api.cc.infix.en_GB.creating.charsequence.contains.builders.NotCheckerOption
import ch.tutteli.atrium.api.cc.infix.en_GB.creating.charsequence.contains.builders.impl.NotCheckerOptionImpl
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.case
import ch.tutteli.atrium.creating.AssertMarker
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NotSearchBehaviour

/**
 * Defines that the decoration behaviour `ignore case` shall be applied to this sophisticated `contains` assertion.
 *
 * @param case Has to be `case`.
 *
 * @return The newly created builder.
 */
@AssertMarker
infix fun <T : CharSequence> CharSequenceContains.Builder<T, NoOpSearchBehaviour>.ignoring(@Suppress("UNUSED_PARAMETER") case: case): CharSequenceContains.Builder<T, IgnoringCaseSearchBehaviour>
    = AssertImpl.charSequence.contains.searchBehaviours.ignoringCase(this)

/**
 * Defines that the decoration behaviour `ignore case` shall be applied to this sophisticated `contains not` assertion.
 *
 * @param case Has to be `case`.
 *
 * @return The newly created builder.
 */
@AssertMarker
infix fun <T : CharSequence> NotCheckerOption<T, NotSearchBehaviour>.ignoring(@Suppress("UNUSED_PARAMETER") case: case): NotCheckerOption<T, IgnoringCaseSearchBehaviour>
    = NotCheckerOptionImpl(containsBuilder ignoring case)
