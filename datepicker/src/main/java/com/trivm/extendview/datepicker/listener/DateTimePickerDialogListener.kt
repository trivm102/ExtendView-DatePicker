package com.trivm.extendview.datepicker.listener

interface DateTimePickerDialogListener {
    fun onSelected(year: Int, monthOfYear: Int, dayOfMonth: Int, timeStamp: Long?)
}

internal interface DateTimePickerButtonsListener {
    fun doneButtonPressed()
    fun cancelButtonPressed()
}