package com.geekbrains.tests

import android.view.View
import com.geekbrains.tests.presenter.details.DetailsPresenter
import com.geekbrains.tests.view.details.ViewDetailsContract
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class DetailsPresenterTests {

    private lateinit var presenter: DetailsPresenter

    @Mock
    private lateinit var view: View

    @Mock
    private lateinit var viewContract: ViewDetailsContract

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        presenter = DetailsPresenter(viewContract)
        presenter.onAttach(view)
    }

    @Test
    fun setCounter_Test() {
        presenter.setCounter(0)
        assertEquals(0, presenter.count)
    }

    @Test
    fun increaseCount_Test() {
        presenter.onIncrement()
        assertEquals(1, presenter.count)
    }

    @Test
    fun decreaseCount_Test() {
        presenter.onDecrement()
        assertEquals(-1, presenter.count)
    }

    @Test
    fun onAttach_Test() {
        assertEquals(view, presenter.view)
    }

    @Test
    fun onDetach_Test() {
        presenter.onDetach()
        assertNull(presenter.view)
    }
}
