package com.trivm.extendview.datepicker.view

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.databinding.DataBindingUtil
import com.trivm.extendview.datepicker.R
import com.trivm.extendview.datepicker.databinding.FragmentDatePickerBinding
import com.trivm.extendview.datepicker.extensions.hideKeyboard
import com.trivm.extendview.datepicker.extensions.setOnSingleClickListener
import com.trivm.extendview.datepicker.extensions.toTimeStamp
import com.trivm.extendview.datepicker.listener.DateTimePickerDialogListener
import java.util.*

class DateTimePickerDialog : AppCompatDialogFragment() {

    private val layoutID: Int = R.layout.fragment_date_picker

    private lateinit var binding : FragmentDatePickerBinding

    private var selectedListener: DateTimePickerDialogListener? = null

    var timeStampSelected: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLocaleDefaultVN()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.hideKeyboard()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding = DataBindingUtil.inflate(inflater, layoutID, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        binding.doneButton.setOnSingleClickListener {
            datePickerSelected()
            this.dismiss()
        }

        binding.cancelButton.setOnSingleClickListener {
            this.dismiss()
        }
    }

    private fun setLocaleDefaultVN() {
        Locale.setDefault(Locale("vi","VN"))
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
}