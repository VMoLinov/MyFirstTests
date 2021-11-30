package com.geekbrains.myfirsttests

import android.text.Editable
import android.text.TextWatcher

class NumberChecker : TextWatcher {

    internal var value: Int? = null

    override fun afterTextChanged(editable: Editable) {
        if (editable.toString() != "") {
            value = checkValue(editable.toString().toInt())
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

    companion object {
        fun checkValue(int: Int): Int? {
            return when (int) {
                in 0..9 -> null
                in 10..19 -> 1
                in 20..29 -> 2
                else -> 3
            }
        }

        fun intArray(): IntArray {
            return intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)
        }
    }
}
