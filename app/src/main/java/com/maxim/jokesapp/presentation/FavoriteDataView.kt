package com.maxim.jokesapp.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.LinearLayout
import com.maxim.jokesapp.R
import com.maxim.jokesapp.core.presentation.CommonItemViewModel

class FavoriteDataView : LinearLayout {
    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }
    //endregion

    private val checkBox: CheckBox
    private val textView: CorrectTextView
    private val changeButton: CorrectImageButton
    private val actionButton: CorrectButton
    private val progress: CorrectProgress

    init {
        orientation = VERTICAL
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.favorite_data_view, this, true)
        checkBox = getChildAt(0) as CheckBox
        val linear = getChildAt(1) as LinearLayout
        textView = linear.findViewById(R.id.jokeText)
        changeButton = linear.findViewById(R.id.favoriteButton)
        progress = getChildAt(2) as CorrectProgress
        actionButton = getChildAt(3) as CorrectButton
    }

    fun linkWith(viewModel: CommonItemViewModel) {
        listenChanges { isChecked ->
            viewModel.chooseFavorites(isChecked)
        }
        handleChangeButton {
            viewModel.changeItemStatus()
        }
        handleActionButton {
            viewModel.getItem()
        }
    }

    fun init(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.FavoriteDataView, 0, 0).apply {
            try {
                val actionButtonText = getString(R.styleable.FavoriteDataView_actionButtonText)
                val checkBoxText = getString(R.styleable.FavoriteDataView_checkBoxText)
                checkBox.text = checkBoxText
                actionButton.text = actionButtonText
            } finally {
                recycle()
            }
        }
    }

    fun checkBoxText(text: String) {
        checkBox.text = text
    }

    fun actionButtonText(text: String) {
        actionButton.text = text
    }

    fun listenChanges(block: (checked: Boolean) -> Unit) =
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            block.invoke(isChecked)
        }
    fun handleChangeButton(block: () -> Unit) = changeButton.setOnClickListener {
        block.invoke()
    }
    fun handleActionButton(block: () -> Unit) = actionButton.setOnClickListener {
        block.invoke()
    }

    fun show(state: State) = state.show(progress, actionButton, textView, changeButton)
}