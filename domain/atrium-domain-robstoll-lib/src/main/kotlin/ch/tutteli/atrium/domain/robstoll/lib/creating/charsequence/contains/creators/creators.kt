package ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.creators

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.domain.creating.charsequence.contains.CharSequenceContains
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchers.IgnoringCaseIndexSearcher
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchers.IgnoringCaseRegexSearcher
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchers.IndexSearcher
import ch.tutteli.atrium.domain.robstoll.lib.creating.charsequence.contains.searchers.RegexSearcher
import ch.tutteli.atrium.reporting.translating.Translatable

fun <T : CharSequence> _containsValues(
    checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
    expected: List<Any>
): AssertionGroup
    = checkOnlyAllowedTypeAndCreateAssertionGroup(checkerOption, IndexSearcher(), expected)

fun <T : CharSequence> _containsValuesIgnoringCase(
    checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
    expected: List<Any>
): AssertionGroup
    = checkOnlyAllowedTypeAndCreateAssertionGroup(checkerOption, IgnoringCaseIndexSearcher(), expected)

private fun <T : CharSequence, S : CharSequenceContains.SearchBehaviour> checkOnlyAllowedTypeAndCreateAssertionGroup(
    checkerOption: CharSequenceContains.CheckerOption<T, S>,
    searcher: CharSequenceContains.Searcher<S>,
    expected: List<Any>
): AssertionGroup {
    require(expected.isNotEmpty()) {
        "You have to specify at least one search criterion for a CharSequence contains assertion"
    }
    expected.forEach {
        require(it is CharSequence || it is Number || it is Char) {
            "Only values of type CharSequence, Number and Char are allowed\nGiven: $it\n" +
                "We provide an API with Any for convenience (so that you can mix String and Int for instance).\n" +
                "Use toString() if you really want to search for its toString()-representation."
        }
    }
    return createAssertionGroup(checkerOption, searcher, expected)
}

fun <T : CharSequence> _containsDefaultTranslationOf(
    checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
    expected: List<Translatable>
): AssertionGroup
    = _containsValues(checkerOption, expected.map { it.getDefault() })

fun <T : CharSequence> _containsDefaultTranslationOfIgnoringCase(
    checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
    expected: List<Translatable>
): AssertionGroup
    = _containsValuesIgnoringCase(checkerOption, expected.map { it.getDefault() })

fun <T : CharSequence> _containsRegex(
    checkerOption: CharSequenceContains.CheckerOption<T, NoOpSearchBehaviour>,
    expected: List<String>
): AssertionGroup
    = createAssertionGroup(checkerOption, RegexSearcher(), expected)

fun <T : CharSequence> _containsRegexIgnoringCase(
    checkerOption: CharSequenceContains.CheckerOption<T, IgnoringCaseSearchBehaviour>,
    expected: List<String>
): AssertionGroup
    = createAssertionGroup(checkerOption, IgnoringCaseRegexSearcher(), expected)

private fun <T : CharSequence, SC : Any, S : CharSequenceContains.SearchBehaviour> createAssertionGroup(
    checkerOption: CharSequenceContains.CheckerOption<T, S>,
    searcher: CharSequenceContains.Searcher<S>,
    expected: List<SC>
): AssertionGroup {
    val creator = CharSequenceContainsAssertionCreator<T, SC, S>(
        checkerOption.containsBuilder.searchBehaviour,
        searcher,
        checkerOption.checkers
    )
    return creator.createAssertionGroup(checkerOption.containsBuilder.plant, expected)
}
