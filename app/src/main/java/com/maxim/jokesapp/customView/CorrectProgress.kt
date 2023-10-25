package com.maxim.jokesapp.customView

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import com.maxim.jokesapp.ShowView

class CorrectProgress: ProgressBar, ShowView {
    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )
    //endregion

    override fun show(show: Boolean) {
        visibility = if (show) View.VISIBLE else View.INVISIBLE
    }
}