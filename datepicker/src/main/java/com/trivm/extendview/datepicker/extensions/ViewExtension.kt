package com.trivm.extendview.datepicker.extensions

import android.os.SystemClock
import android.view.View
import androidx.databinding.BindingAdapter

class SingleClick(
    private var defaultInterval: Int = 800,
    private val onSingleCLick: (View) -> Unit
) : View.OnClickListener {
    private var lastTimeClicked: Long = 0
    override fun onClick(v: View) {
        if (SystemClock.elapsedRealtime() - lastTimeClicked < defaultInterval) {
            return
        }
        lastTimeClicked = SystemClock.elapsedRealtime()
        onSingleCLick(v)
    }
}

interface OnSingleClickListener {
    fun onSingleClick(view: View)
}

@BindingAdapter("onSingleClick")
fun View.setOnSingleClickListener(onSingleClick: OnSingleClickListener?) {
    val singleClickListener = SingleClick {
        onSingleClick?.onSingleClick(it)
    }
    setOnClickListener(singleClickListener)
}