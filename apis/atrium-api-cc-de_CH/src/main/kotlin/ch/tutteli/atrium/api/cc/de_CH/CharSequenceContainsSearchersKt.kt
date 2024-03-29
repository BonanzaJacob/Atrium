package ch.tutteli.atrium.api.cc.de_CH

import ch.tutteli.atrium.assertions.charsequence.contains.builders.CharSequenceContainsCheckerBuilder
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.IgnoringCaseSearchBehaviour
import ch.tutteli.atrium.domain.creating.charsequence.contains.searchbehaviours.NoOpSearchBehaviour
import ch.tutteli.atrium.reporting.translating.Translatable

/**
 * `Deprecated`, do not use this class, it is only here to retain binary compatibility.
 */
@Deprecated("Do not use this class, it is only here to retain binary compatibility (file was renamed to charSequenceContainsCreators), will be removed with 1.0.0")
object CharSequenceContainsSearchersKt {

    @JvmStatic
    @Deprecated(
        "use the extension fun `value` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0",
        ReplaceWith("checkerBuilder.wert(expected)")
    )
    fun <T : CharSequence> wert(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, NoOpSearchBehaviour>,
        expected: Any
    ): AssertionPlant<T> = werte(checkerBuilder, expected)

    @JvmStatic
    @Deprecated(
        "use the extension fun `the` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0",
        ReplaceWith("checkerBuilder.werte(expected, *otherExpected)")
    )
    fun <T : CharSequence> werte(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, NoOpSearchBehaviour>,
        expected: Any,
        vararg otherExpected: Any
    ): AssertionPlant<T> = checkerBuilder.werte(expected, *otherExpected)


    @JvmStatic
    @Deprecated(
        "use the extension fun `value` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0",
        ReplaceWith("checkerBuilder.value(expected)")
    )
    fun <T : CharSequence> valueIgnoringCase(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, IgnoringCaseSearchBehaviour>,
        expected: Any
    ): AssertionPlant<T> = valuesIgnoringCase(checkerBuilder, expected)

    @JvmStatic
    @Deprecated(
        "use the extension fun `values` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0",
        ReplaceWith("checkerBuilder.values(expected, *otherExpected)")
    )
    fun <T : CharSequence> valuesIgnoringCase(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, IgnoringCaseSearchBehaviour>,
        expected: Any,
        vararg otherExpected: Any
    ): AssertionPlant<T> = checkerBuilder.werte(expected, *otherExpected)


    @JvmStatic
    @Deprecated(
        "use the extension fun `defaultTranslationOf` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0",
        ReplaceWith("checkerBuilder.defaultTranslationOf(expected, *otherExpected)")
    )
    fun <T : CharSequence> standardUebersetzungVon(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, NoOpSearchBehaviour>,
        expected: Translatable,
        vararg otherExpected: Translatable
    ): AssertionPlant<T> = checkerBuilder.standardUebersetzungVon(expected, *otherExpected)

    @JvmStatic
    @Deprecated(
        "use the extension fun `defaultTranslationOf` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0",
        ReplaceWith("checkerBuilder.defaultTranslationOf(expected, *otherExpected)")
    )
    fun <T : CharSequence> defaultTranslationOfIgnoringCase(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, IgnoringCaseSearchBehaviour>,
        expected: Translatable,
        vararg otherExpected: Translatable
    ): AssertionPlant<T> = checkerBuilder.standardUebersetzungVon(expected, *otherExpected)


    @JvmStatic
    @Deprecated(
        "use the extension fun `regex` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0",
        ReplaceWith("checkerBuilder.regex(pattern, *otherPatterns)")
    )
    fun <T : CharSequence> regex(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, NoOpSearchBehaviour>,
        pattern: String,
        vararg otherPatterns: String
    ): AssertionPlant<T> = checkerBuilder.regex(pattern, *otherPatterns)

    @JvmStatic
    @Deprecated(
        "use the extension fun `regex` instead. This fun is only here to retain binary compatibility, will be removed with 1.0.0",
        ReplaceWith("checkerBuilder.regex(pattern, *otherPatterns)")
    )
    fun <T : CharSequence> regexIgnoringCase(
        checkerBuilder: CharSequenceContainsCheckerBuilder<T, IgnoringCaseSearchBehaviour>,
        pattern: String,
        vararg otherPatterns: String
    ): AssertionPlant<T> = checkerBuilder.regex(pattern, *otherPatterns)
}
