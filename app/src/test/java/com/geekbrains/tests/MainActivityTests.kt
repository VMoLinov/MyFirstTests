package com.geekbrains.tests

import android.content.Context
import android.os.Build
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.geekbrains.tests.view.details.DetailsActivity
import com.geekbrains.tests.view.search.MainActivity
import junit.framework.TestCase
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLooper
import org.robolectric.shadows.ShadowToast

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
internal class MainActivityTests {
    private lateinit var scenario: ActivityScenario<MainActivity>
    private lateinit var context: Context

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun editText_IsWorking() {
        scenario.onActivity {
            val editText = it.findViewById<EditText>(R.id.searchEditText)
            editText.setText("text")
            assertNotNull(editText.text)
            assertEquals(editText.text.toString(), "text")
        }
    }

    @Test //Проверим верную реакцию на нажатие кнопки поиска
    fun editText_IsSearch() {
        scenario.onActivity {
            val editText = it.findViewById<EditText>(R.id.searchEditText)
            editText.setText("text")
            editText.setOnEditorActionListener { textView, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Toast.makeText(textView.context, textView.text.toString(), Toast.LENGTH_SHORT)
                        .show()
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }
            editText.onEditorAction(EditorInfo.IME_ACTION_SEARCH)
            ShadowLooper.idleMainLooper()
            assertEquals("text", ShadowToast.getTextOfLatestToast().toString())
        }
    }

    @Test
    fun detailsButton_IsWorked() {
        scenario.onActivity {
            val button = it.findViewById<Button>(R.id.toDetailsActivityButton)
            button.performClick()
            val count = 42
            val intent = DetailsActivity.getIntent(context, count)
            val bundle = intent.extras
            assertEquals(count, bundle?.getInt(DetailsActivity.TOTAL_COUNT_EXTRA, 0))
        }
    }

    @After
    fun close() {
        scenario.close()
    }
}
