package com.example.firstapp.utilities

import android.text.Editable
import android.text.TextWatcher

class ExpiryDateTextWatcher : TextWatcher {

    private var current = ""

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        // Not used
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        // Not used
    }

    override fun afterTextChanged(s: Editable) {
        if (s.toString() != current) {
            val input = s.toString().replace("/", "")
            val monthAndYear = if (input.length >= 2) {
                input.substring(0, 2) + "/" + input.substring(2)
            } else {
                input
            }

            current = formatExpiryDate(monthAndYear)
            s.replace(0, s.length, current)
        }
    }

    private fun formatExpiryDate(date: String): String {
        var formatted = date
        if (formatted.length > 2) {
            val month = try {
                formatted.substring(0, 2).toInt()
            } catch (e: NumberFormatException) {
                0
            }
            if (month < 1 || month > 12) {
                formatted = "12/"
            }
        }
        if (formatted.length > 5) {
            formatted = formatted.substring(0, 5)
        }
        return formatted
    }
}
