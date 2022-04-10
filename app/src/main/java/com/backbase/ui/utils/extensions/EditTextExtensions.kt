package com.backbase.ui.utils.extensions

import android.view.inputmethod.EditorInfo
import android.widget.EditText

fun EditText.onImeDone(listener: () -> Unit) {
    this.setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            listener.invoke()
            true
        } else false
    }
}
