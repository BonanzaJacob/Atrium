package ch.tutteli.atrium.spec.reporting

import ch.tutteli.atrium.api.cc.en_GB.isEmpty
import ch.tutteli.atrium.api.cc.en_GB.toBe
import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.core.coreFactory
import ch.tutteli.atrium.domain.builders.AssertImpl
import ch.tutteli.atrium.reporting.AssertionFormatter
import ch.tutteli.atrium.reporting.AssertionFormatterController
import ch.tutteli.atrium.reporting.translating.Untranslatable
import ch.tutteli.atrium.spec.AssertionVerb
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

abstract class TextSummaryAssertionGroupFormatterSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (Map<Class<out BulletPointIdentifier>, String>, AssertionFormatterController) -> AssertionFormatter,
    describePrefix: String = "[Atrium] "
) : AssertionFormatterSpecBase({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    val successBulletPoint = "(/)"
    val indentSuccessBulletPoint = " ".repeat(successBulletPoint.length + 1)
    val failingBulletPoint = "(x)"
    val indentFailingBulletPoint = " ".repeat(failingBulletPoint.length + 1)

    val facade = createFacade(mapOf(
        PrefixSuccessfulSummaryAssertion::class.java to "$successBulletPoint ",
        PrefixFailingSummaryAssertion::class.java to "$failingBulletPoint "
    )) { bulletPoints, controller, _, _ ->
        testeeFactory(bulletPoints, controller)
    }

    val onlyFailingAssertionFilter: (Assertion) -> Boolean = { !it.holds() }

    describeFun(AssertionFormatter::canFormat.name) {
        val testee = testeeFactory(bulletPoints, coreFactory.newAssertionFormatterController())
        it("returns true for an ${AssertionGroup::class.simpleName} with type object: ${SummaryAssertionGroupType::class.simpleName}") {
            val result = testee.canFormat(AssertImpl.builder.withType(object : SummaryAssertionGroupType {},  Untranslatable.EMPTY, 1).create(listOf()))
            verbs.checkImmediately(result).toBe(true)
        }
    }

    describeFun(AssertionFormatter::formatGroup.name) {
        context("${AssertionGroup::class.simpleName} of ${DefaultSummaryAssertionGroupType::class.simpleName} and does not hold") {
            val assertions = listOf(
                AssertImpl.builder.descriptive.create(AssertionVerb.ASSERT, 1, true),
                AssertImpl.builder.descriptive.create(AssertionVerb.EXPECT_THROWN, 2, false)
            )
            val summaryAssertionGroup = AssertImpl.builder
                .withType(DefaultSummaryAssertionGroupType, AssertionVerb.ASSERT, 22)
                .create(assertions)

            context("format directly the group (no prefix given)") {
                it("puts the assertions one under the other, does not filter out successful ones and indicates whether they hold or not") {
                    facade.format(summaryAssertionGroup, sb, onlyFailingAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(separator
                        + "${AssertionVerb.ASSERT.getDefault()}: 22$separator"
                        + "$successBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                        + "$failingBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2")
                }
            }

            context("in an ${AssertionGroup::class.simpleName} of type ${FeatureAssertionGroupType::class.simpleName}") {
                it("puts the assertions one under the other and indents the second one including a prefix") {
                    val featureAssertions = listOf(summaryAssertionGroup,
                        AssertImpl.builder.descriptive.create(AssertionVerb.ASSERT, 20, false)
                    )
                    val featureAssertionGroup = AssertImpl.builder.feature(AssertionVerb.ASSERT, 10).create(featureAssertions)
                    facade.format(featureAssertionGroup, sb, onlyFailingAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(separator
                        + "$arrow ${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                        + "$indentArrow$featureBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 22$separator"
                        + "$indentArrow$indentFeatureBulletPoint$successBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                        + "$indentArrow$indentFeatureBulletPoint$failingBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                        + "$indentArrow$featureBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20")
                }
            }

            context("in an ${AssertionGroup::class.simpleName} of type ${ListAssertionGroupType::class.simpleName}") {
                val listAssertions = listOf(summaryAssertionGroup,
                    AssertImpl.builder.descriptive.create(AssertionVerb.ASSERT, 20, false)
                )
                val listAssertionGroup = AssertImpl.builder.list(AssertionVerb.ASSERT, 10).create(listAssertions)

                it("puts the assertions one under the other and indents the second one including a prefix") {
                    facade.format(listAssertionGroup, sb, onlyFailingAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(separator
                        + "${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                        + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 22$separator"
                        + "$indentListBulletPoint$successBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                        + "$indentListBulletPoint$failingBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                        + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20")
                }

                context("in another ${AssertionGroup::class.simpleName} of type ${ListAssertionGroupType::class.simpleName}") {
                    it("puts the assertions one under the other and indents as the other assertions but adds an extra indent to the second assertion including a prefix") {
                        val listAssertions2 = listOf(listAssertionGroup,
                            AssertImpl.builder.descriptive.create(AssertionVerb.EXPECT_THROWN, 30, false)
                        )
                        val listAssertionGroup2 = AssertImpl.builder.list(AssertionVerb.ASSERT, 5).create(listAssertions2)
                        facade.format(listAssertionGroup2, sb, onlyFailingAssertionFilter)
                        verbs.checkImmediately(sb.toString()).toBe(separator
                            + "${AssertionVerb.ASSERT.getDefault()}: 5$separator"
                            + "$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                            + "$indentListBulletPoint$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 22$separator"
                            + "$indentListBulletPoint$indentListBulletPoint$successBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                            + "$indentListBulletPoint$indentListBulletPoint$failingBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                            + "$indentListBulletPoint$listBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 20$separator"
                            + "$listBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 30")
                    }
                }
            }

            context("in another ${AssertionGroup::class.simpleName} of type object: ${SummaryAssertionGroupType::class.simpleName}") {
                val summaryAssertions = listOf(
                    AssertImpl.builder.descriptive.create(AssertionVerb.ASSERT, 21, false),
                    summaryAssertionGroup,
                    AssertImpl.builder.summary(AssertionVerb.EXPECT_THROWN).create(
                        AssertImpl.builder.descriptive.create(AssertionVerb.ASSERT, 30, true),
                        AssertImpl.builder.descriptive.create(AssertionVerb.ASSERT, 31, true)
                    )
                )
                val summaryAssertionGroup2 = AssertImpl.builder
                    .withType(object : SummaryAssertionGroupType {}, AssertionVerb.ASSERT, 10)
                    .create(summaryAssertions)

                it("puts the assertions one under the other and adds an extra indent to the second one") {
                    facade.format(summaryAssertionGroup2, sb, onlyFailingAssertionFilter)
                    verbs.checkImmediately(sb.toString()).toBe(separator
                        + "${AssertionVerb.ASSERT.getDefault()}: 10$separator"
                        + "$failingBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 21$separator"
                        + "$failingBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 22$separator"
                        + "$indentFailingBulletPoint$successBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 1$separator"
                        + "$indentFailingBulletPoint$failingBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}: 2$separator"
                        + "$successBulletPoint ${AssertionVerb.EXPECT_THROWN.getDefault()}:  (RawString)$separator"
                        + "$indentSuccessBulletPoint$successBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 30$separator"
                        + "$indentSuccessBulletPoint$successBulletPoint ${AssertionVerb.ASSERT.getDefault()}: 31"
                    )
                }
            }
        }
        context("${AssertionGroup::class.simpleName} of ${DefaultSummaryAssertionGroupType::class.simpleName} and group holds") {
            test("The group is not formatted since it is filtered out") {
                val assertions = listOf(
                    AssertImpl.builder.descriptive.create(AssertionVerb.ASSERT, 1, true)
                )
                val summaryAssertionGroup = AssertImpl.builder.summary(AssertionVerb.ASSERT).create(assertions)
                facade.format(summaryAssertionGroup, sb, onlyFailingAssertionFilter)
                verbs.checkImmediately(sb.toString()).isEmpty()
            }
        }
    }
})
