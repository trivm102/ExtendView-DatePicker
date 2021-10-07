package com.trivm.extendview.datepicker.extensions

import android.os.SystemClock
import android.view.View

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

fun View.setOnSingleClickListener(onSingleClick: (View) -> Unit) {
    val singleClickListener = SingleClick {
        onSingleClick(it)
    }
    setOnClickListener(singleClickListener)
}