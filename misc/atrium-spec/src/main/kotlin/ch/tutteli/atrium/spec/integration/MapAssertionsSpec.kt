package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.containsDefaultTranslationOf
import ch.tutteli.atrium.api.cc.en_GB.message
import ch.tutteli.atrium.api.cc.en_GB.messageContains
import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionBasic
import ch.tutteli.atrium.translations.DescriptionMapAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.include

abstract class MapAssertionsSpec(
    verbs: AssertionVerbFactory,
    hasSizePair: Pair<String, Assert<Map<String, Int>>.(Int) -> Assert<Map<String, Int>>>,
    isEmptyPair: Pair<String, Assert<Map<String, Int>>.() -> Assert<Map<String, Int>>>,
    isNotEmptyPair: Pair<String, Assert<Map<String, Int>>.() -> Assert<Map<String, Int>>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(object : SubjectLessAssertionSpec<Map<String, Int>>(describePrefix,
        hasSizePair.first to mapToCreateAssertion { hasSizePair.second(this, 2) },
        isEmptyPair.first to mapToCreateAssertion { isEmptyPair.second(this) },
        isNotEmptyPair.first to mapToCreateAssertion { isNotEmptyPair.second(this) }
    ) {})

    include(object : CheckingAssertionSpec<Map<String, Int>>(verbs, describePrefix,
        checkingTriple(hasSizePair.first, { hasSizePair.second(this, 1) }, mapOf("a" to 1), mapOf("a" to 1, "b" to 2)),
        checkingTriple(isEmptyPair.first, { isEmptyPair.second(this) }, mapOf(), mapOf("a" to 1, "b" to 2)),
        checkingTriple(isNotEmptyPair.first, { isNotEmptyPair.second(this) }, mapOf("b" to 2), mapOf())
    ) {})

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val assert: (Map<String, Int>) -> Assert<Map<String, Int>> = verbs::checkImmediately
    val expect = verbs::checkException
    val fluent = assert(mapOf("a" to 1, "b" to 2))

    val (hasSize, hasSizeFun) = hasSizePair
    val (isEmpty, isEmptyFun) = isEmptyPair
    val (isNotEmpty, isNotEmptyFun) = isNotEmptyPair

    describeFun(hasSize) {
        context("collection with two entries") {
            test("expect 2 does not throw") {
                fluent.hasSizeFun(2)
            }
            test("expect 1 throws an AssertionError") {
                expect {
                    fluent.hasSizeFun(1)
                }.toThrow<AssertionError> {
                    messageContains("size: 2", DescriptionAnyAssertion.TO_BE.getDefault() + ": 1")
                }
            }
            test("expect 3 throws an AssertionError") {
                expect {
                    fluent.hasSizeFun(3)
                }.toThrow<AssertionError> {
                    messageContains("size: 2", DescriptionAnyAssertion.TO_BE.getDefault() + ": 3")
                }
            }
        }
    }

    describeFun(isEmpty) {
        it("does not throw if a collection is empty") {
            assert(mapOf()).isEmptyFun()
        }

        it("throws an AssertionError if a collection is not empty") {
            expect {
                assert(mapOf("a" to 1, "b" to 2)).isEmptyFun()
            }.toThrow<AssertionError> {
                message {
                    containsDefaultTranslationOf(DescriptionBasic.IS, DescriptionMapAssertion.EMPTY)
                }
            }
        }
    }

    describeFun(isNotEmpty) {
        it("does not throw if a collection is not empty") {
            assert(mapOf("a" to 1)).isNotEmptyFun()
        }

        it("throws an AssertionError if a collection is empty") {
            expect {
                assert(mapOf()).isNotEmptyFun()
            }.toThrow<AssertionError> {
                message {
                    containsDefaultTranslationOf(DescriptionBasic.IS_NOT, DescriptionMapAssertion.EMPTY)
                }
            }
        }
    }
})
