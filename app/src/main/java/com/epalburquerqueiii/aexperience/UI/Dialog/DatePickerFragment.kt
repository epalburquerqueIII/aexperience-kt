package com.epalburquerqueiii.aexperience.UI.Dialog

import android.app.DatePickerDialog
import android.app.Dialog
//import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*


class DatePickerFragment : DialogFragment() {
    private var listener: DatePickerDialog.OnDateSetListener? = null


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dialog = DatePickerDialog(activity, listener, year, month, day)

        return dialog
    }

    companion object {
        fun newInstance(listener: DatePickerDialog.OnDateSetListener): DatePickerFragment {
            val fragment = DatePickerFragment()
            fragment.listener = listener

            return fragment
        }
    }
}