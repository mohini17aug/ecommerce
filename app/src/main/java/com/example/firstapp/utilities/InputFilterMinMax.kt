package com.example.firstapp.utilities

import android.text.InputFilter
import android.text.Spanned


class InputFilterMinMax(private val mMin: Long, private val mMax: Long) : InputFilter {

    override fun filter(
        source: CharSequence,
        start: Int,
        end: Int,
        dest: Spanned,
        dstart: Int,
        dend: Int,
    ): CharSequence {
        try {
            val newValueString: String = dest.subSequence(0, dstart).toString() +
                    source.subSequence(start, end).toString() +
                    dest.subSequence(dend, dest.length)
            val newValueInt = newValueString.toLong()
            if (isInRange(mMin, mMax, newValueInt)) return source
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }
        return ""
    }

    private fun isInRange(min: Long, max: Long, value: Long): Boolean {
        return if (max > min) {
            value in min..max
        } else {
            value in max..min
        }
    }
}