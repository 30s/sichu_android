package com.ax003d.sichu.fragments;

import java.util.Calendar;

import org.holoeverywhere.app.DatePickerDialog;
import org.holoeverywhere.app.Dialog;
import org.holoeverywhere.app.DialogFragment;

import android.os.Bundle;

public class DatePickerFragment extends DialogFragment {

	private DatePickerDialog.OnDateSetListener onDateSetListener;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		return new DatePickerDialog(getActivity(), onDateSetListener, year,
				month, day);
	}

	/**
	 * Note: must be called before showed.
	 * 
	 * @param onDateSetListener
	 */
	public void setOnDateSetListener(
			DatePickerDialog.OnDateSetListener onDateSetListener) {
		this.onDateSetListener = onDateSetListener;
	}
}