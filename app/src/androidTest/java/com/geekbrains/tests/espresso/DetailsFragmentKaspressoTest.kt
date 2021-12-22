package com.geekbrains.tests.espresso

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import com.geekbrains.tests.DetailsScreenFragment
import com.geekbrains.tests.TEST_NUMBER_OF_RESULTS_PLUS_1
import com.geekbrains.tests.view.details.DetailsFragment
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DetailsFragmentKaspressoTest {

    private lateinit var scenario: FragmentScenario<DetailsFragment>

    @Before
    fun setUp() {
        scenario = launchFragmentInContainer()
    }

    @Test
    fun fragment_testBundle() {
        val args = bundleOf("TOTAL_COUNT_EXTRA" to 10)
        val scenario = launchFragmentInContainer<DetailsFragment>(args)
        scenario.moveToState(Lifecycle.State.RESUMED)
        DetailsScreenFragment.totalCountTextView.hasText("Number of results: 10")
    }

    @Test
    fun fragment_testSetCountMethod() {
        scenario.onFragment { fragment ->
            fragment.setCount(10)
        }
        DetailsScreenFragment.totalCountTextView.hasText("Number of results: 10")
    }

    @Test
    fun fragment_testIncrementButton() {
        DetailsScreenFragment {
            incrementButton.click()
            totalCountTextView.hasText(TEST_NUMBER_OF_RESULTS_PLUS_1)
        }
    }



    @After
    fun close() {
        scenario.close()
    }
}
