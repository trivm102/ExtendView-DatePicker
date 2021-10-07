package com.trivm.extendview.datepicker.extensions

import android.widget.DatePicker
import java.text.SimpleDateFormat
import java.util.*

fun DatePicker.toTimeStamp(): Long? {
    val stringDate = "$dayOfMonth/${month + 1}/$year"
    val formatDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatDate.parse(stringDate)?.time
}