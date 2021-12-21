package com.geekbrains.tests.screens

import com.geekbrains.tests.R
import com.geekbrains.tests.view.details.DetailsActivity
import com.geekbrains.tests.view.search.MainActivity
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

internal object MainScreen : KScreen<MainScreen>() {
    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewClass: Class<*>
        get() = MainActivity::class.java

    val searchEditText = KEditText { withId(R.id.searchEditText) }
    val toDetailsActivityButton = KButton { withId(R.id.toDetailsActivityButton) }
    val totalCountTextView = KTextView { withId(R.id.totalCountTextView) }
}

internal object DetailsScreen : KScreen<DetailsScreen>() {
    override val layoutId: Int
        get() = R.layout.activity_details
    override val viewClass: Class<*>
        get() = DetailsActivity::class.java

    val incrementButton = KButton { withId(R.id.incrementButton) }
    val decrementButton = KButton { withId(R.id.decrementButton) }
    val totalCountTextView = KTextView { withId(R.id.totalCountTextView) }
}
