package com.trivm.extendview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.trivm.extendview.datepicker.listener.DateTimePickerDialogListener
import com.trivm.extendview.datepicker.view.DateTimePickerDialog

class MainActivity : AppCompatActivity() {
    private var dateTimePickerDialog: DateTimePickerDialog? = null
    private var timeStampSelected: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: TextView = findViewById(R.id.helloButton)
        button.setOnClickListener {
            Log.d("TRIVM", "click....")
            showDatePicker()
        }
    }

    private fun showDatePicker() {
        if (dateTimePickerDialog == null) {
            dateTimePickerDialog = DateTimePickerDialog()
            dateTimePickerDialog?.setDatePickerSelectListener(object :
                DateTimePickerDialogListener {
                override fun onSelected(year: Int, monthOfYear: Int, dayOfMonth: Int, timeStamp: Long?) {
                    Log.d("TRIVM", "year: $year - month: $monthOfYear day: $dayOfMonth")
                    timeStampSelected = timeStamp
                }

            })
        }
        dateTimePickerDialog?.timeStampSelected = timeStampSelected
        dateTimePickerDialog?.show(this.supportFragmentManager, this.javaClass.simpleName)
    }
}