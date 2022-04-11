package com.backbase.ui.utils.textwatcher

import android.text.Editable
import android.text.TextWatcher

class SimpleTextWatcher(
    private val afterChange: ((String) -> Unit)? = null,
    private val onChange: ((String) -> Unit)? = null,
    private val beforeChange: ((String) -> Unit)? = null
) : TextWatcher {

    override fun afterTextChanged(p0: Editable?) {
        afterChange?.invoke(p0.toString())
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        beforeChange?.invoke(p0.toString())
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        onChange?.invoke(p0.toString())
    }
}
