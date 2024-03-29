module ch.tutteli.atrium.domain.robstoll {

    requires transitive ch.tutteli.atrium.domain.api;
    requires ch.tutteli.atrium.domain.robstoll.lib;

    exports ch.tutteli.atrium.domain.robstoll.assertions.composers;
    exports ch.tutteli.atrium.domain.robstoll.creating;
    exports ch.tutteli.atrium.domain.robstoll.creating.any.typetransformation.creators;
    exports ch.tutteli.atrium.domain.robstoll.creating.any.typetransformation.failurehandlers;
    exports ch.tutteli.atrium.domain.robstoll.creating.charsequence.contains.checkers;
    exports ch.tutteli.atrium.domain.robstoll.creating.charsequence.contains.creators;
    exports ch.tutteli.atrium.domain.robstoll.creating.charsequence.contains.searchbehaviours;
    exports ch.tutteli.atrium.domain.robstoll.creating.collectors;
    exports ch.tutteli.atrium.domain.robstoll.creating.iterable.contains.checkers;
    exports ch.tutteli.atrium.domain.robstoll.creating.iterable.contains.creators;
    exports ch.tutteli.atrium.domain.robstoll.creating.iterable.contains.searchbehaviours;
    exports ch.tutteli.atrium.domain.robstoll.creating.throwable.thrown.creators;
    exports ch.tutteli.atrium.domain.robstoll.creating.throwable.thrown.providers;
}
