package ch.tutteli.atrium.translations

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.reporting.translating.StringBasedTranslatable

/**
 * Contains the [DescriptiveAssertion.description]s of the assertion functions which are applicable to [Iterable].
 */
enum class DescriptionIterableAssertion(override val value: String) : StringBasedTranslatable {
    AN_ENTRY_WHICH("ein Eintrag, welcher"),
    AN_ENTRY_WHICH_IS("Eintrag"),
    AT_LEAST("ist zumindest"),
    AT_MOST("ist höchstens"),
    CONTAINS("enthält"),
    CONTAINS_NOT("enthält nicht"),
    ENTRY_WITH_INDEX("Eintrag %s"),
    EXACTLY("ist genau"),
    IN_ANY_ORDER("%s, in beliebiger Reihenfolge"),
    IN_ANY_ORDER_ONLY("%s ausschliesslich, in beliebiger Reihenfolge"),
    IN_ORDER("%, in gegebener Reihenfolge"),
    IN_ORDER_ONLY("%s ausschliesslich, in gegebener Reihenfolge"),
    IN_ORDER_ONLY_GROUPED("%s ausschliesslich, in gegebener Reihenfolge, in Gruppen"),
    INDEX("index %s"),
    INDEX_FROM_TO("index %s..%s"),
    NUMBER_OF_OCCURRENCES("Anzahl Treffer"),
    SIZE_EXCEEDED("❗❗ hasNext() hat `false` zurückgegeben"),
    CANNOT_EVALUATE_SUBJECT_EMPTY_ITERABLE("$COULD_NOT_EVALUATE_DEFINED_ASSERTIONS -- `Iterable` gibt keinen nächsten Eintrag zurück.\n$VISIT_COULD_NOT_EVALUATE_ASSERTIONS"),
    CANNOT_EVALUATE_SUBJECT_ONLY_NULL("$COULD_NOT_EVALUATE_DEFINED_ASSERTIONS -- `Iterable` gibt nur `null` zurück.\n$VISIT_COULD_NOT_EVALUATE_ASSERTIONS"),
    WARNING_ADDITIONAL_ENTRIES("zusätzliche Einträge entdeckt"),
    WARNING_MISMATCHES("folgende Einträge erfüllten keine Aussage (Diskrepanzen)"),
    WARNING_MISMATCHES_ADDITIONAL_ENTRIES("Diskrepanzen und zusätzliche Einträge entdeckt"),
}

internal const val COULD_NOT_EVALUATE_DEFINED_ASSERTIONS = "Konnte die zusätzlichen Aussagen (Assertions) nicht auswerten"
internal const val VISIT_COULD_NOT_EVALUATE_ASSERTIONS = "Besuchen Sie die folgende Website für weiterführende Informationen (in Englisch): https://robstoll.github.io/atrium/could-not-evaluate-assertions"
