package com.trivm.extendview.datepicker.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.trivm.extendview.datepicker.R
import com.trivm.extendview.datepicker.databinding.FragmentDatePickerBinding
import com.trivm.extendview.datepicker.extensions.hideKeyboard
import com.trivm.extendview.datepicker.extensions.toTimeStamp
import com.trivm.extendview.datepicker.listener.DateTimePickerButtonsListener
import com.trivm.extendview.datepicker.listener.DateTimePickerDialogListener
import com.trivm.extendview.datepicker.state.DateTimePickerDialogThemeUI
import java.util.*

open class DateTimePickerDialog(private val stateUI: DateTimePickerDialogThemeUI? = null) : BaseDialogFragment<FragmentDatePickerBinding>() {

    private var selectedListener: DateTimePickerDialogListener? = null
    var timeStampSelected: Long? = null

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDatePickerBinding {
        return DataBindingUtil.inflate(inflater, R.layout.fragment_date_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.activity?.hideKeyboard()
        setupView()
        binding.listener = object : DateTimePickerButtonsListener {
            override fun doneButtonPressed() {
                this@DateTimePickerDialog.dismiss()
                datePickerSelected()
            }

            override fun cancelButtonPressed() {
                this@DateTimePickerDialog.dismiss()
            }
        }
    }

    private fun setupView() {
        timeStampSelected?.let {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = it
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)
            binding.datePicker.init(year, month, dayOfMonth, null)

        }
        binding.datePicker.minDate = System.currentTimeMillis() - 3155695200000
        binding.datePicker.maxDate = System.currentTimeMillis()
        binding.separationView.setBackgroundColor(ContextCompat.getColor(this.requireContext(), this.stateUI?.separationLineColor ?: R.color.blue))
        setBackgroundButtons(this.stateUI?.backgroundButtons)
    }

    private fun datePickerSelected() {
        selectedListener?.onSelected(binding.datePicker.year,
            binding.datePicker.month,
            binding.datePicker.dayOfMonth,
            binding.datePicker.toTimeStamp())
    }

    fun setDatePickerSelectListener(listener: DateTimePickerDialogListener?) {
        this.selectedListener = listener
    }

    private fun setBackgroundButtons(drawableRes: Drawable?) {
        drawableRes?.let {
            binding.cancelButton.background = drawableRes
            binding.doneButton.background = drawableRes
        } ?: kotlin.run {
            binding.cancelButton.background = (ContextCompat.getDrawable(this.requireContext(), R.drawable.button_background_selector))
            binding.doneButton.background = (ContextCompat.getDrawable(this.requireContext(), R.drawable.button_background_selector))
        }
    }
}