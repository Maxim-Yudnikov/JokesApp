package com.maxim.jokesapp.customView

import android.content.Context
import android.util.AttributeSet
import com.maxim.jokesapp.ShowText

class CorrectTextView : androidx.appcompat.widget.AppCompatTextView, ShowText {
    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    //endregion

    override fun show(text: String) {
        setText(text)
    }
}