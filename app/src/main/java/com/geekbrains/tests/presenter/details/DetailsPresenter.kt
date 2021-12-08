package com.geekbrains.tests.presenter.details

import android.view.View
import com.geekbrains.tests.view.details.ViewDetailsContract

internal class DetailsPresenter internal constructor(
    private val viewContract: ViewDetailsContract,
    internal var count: Int = 0
) : PresenterDetailsContract {

    internal var view: View? = null
        private set

    override fun setCounter(count: Int) {
        this.count = count
        viewContract.setCount(count)
    }

    override fun onIncrement() {
        viewContract.setCount(++count)
    }

    override fun onDecrement() {
        viewContract.setCount(--count)
    }

    override fun onAttach(view: View) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
    }
}
