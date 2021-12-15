package com.geekbrains.tests.kaspresso

import androidx.test.core.app.ActivityScenario
import com.geekbrains.tests.TEST_NUMBER_OF_RESULTS_MINUS_1
import com.geekbrains.tests.TEST_NUMBER_OF_RESULTS_PLUS_1
import com.geekbrains.tests.TEST_NUMBER_OF_RESULTS_ZERO
import com.geekbrains.tests.screens.DetailsScreen
import com.geekbrains.tests.view.details.DetailsActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DetailsScreenTest : TestCase() {

    private lateinit var scenario: ActivityScenario<DetailsActivity>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(DetailsActivity::class.java)
    }

    @Test
    fun detailsScreenTest() {
        run {
            step("Screen not null") {
                Assert.assertNotNull(DetailsScreen)
            }
            step("Has text") {
                DetailsScreen {
                    totalCountTextView.hasText(TEST_NUMBER_OF_RESULTS_ZERO)
                }
            }
            step("Increment") {
                DetailsScreen {
                    incrementButton.click()
                    totalCountTextView.hasText(TEST_NUMBER_OF_RESULTS_PLUS_1)
                }
            }
            step("Decrement") {
                DetailsScreen {
                    decrementButton.click()
                    totalCountTextView.hasText(TEST_NUMBER_OF_RESULTS_ZERO)
                    decrementButton.click()
                    totalCountTextView.hasText(TEST_NUMBER_OF_RESULTS_MINUS_1)
                }
            }
        }
    }

    @After
    fun close() {
        scenario.close()
    }
}
