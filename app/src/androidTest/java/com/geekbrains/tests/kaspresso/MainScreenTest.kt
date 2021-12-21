package com.geekbrains.tests.kaspresso

import androidx.test.core.app.ActivityScenario
import com.geekbrains.tests.BuildConfig
import com.geekbrains.tests.TEST_NUMBER
import com.geekbrains.tests.screens.MainScreen
import com.geekbrains.tests.view.search.MainActivity
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainScreenTest : TestCase() {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun mainScreenTest() {
        run {
            step("Search is working") {
                MainScreen {
                    searchEditText {
                        click()
                        replaceText("algol")
                        this@MainScreen.closeSoftKeyboard()
                        pressImeAction()
                    }
                    totalCountTextView {
                        if (BuildConfig.TYPE == MainActivity.FAKE) hasText("Number of results: $TEST_NUMBER")
                        else hasText("Number of results: 2720")
                    }
                }
            }
        }
    }

    @After
    fun close() {
        scenario.close()
    }
}
