package com.maxim.jokesapp.customView

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.maxim.jokesapp.EnableView

class CorrectButton: AppCompatButton, EnableView {
    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    //endregion

    override fun enable(enable: Boolean) {
        isEnabled = enable
    }
}