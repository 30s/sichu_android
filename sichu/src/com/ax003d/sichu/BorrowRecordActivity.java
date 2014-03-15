package com.ax003d.sichu;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.app.DatePickerDialog;
import org.holoeverywhere.app.DatePickerDialog.OnDateSetListener;
import org.holoeverywhere.widget.DatePicker;
import org.holoeverywhere.widget.EditText;
import org.holoeverywhere.widget.TextView;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.ax003d.sichu.fragments.DatePickerFragment;
import com.ax003d.sichu.models.Book;
import com.ax003d.sichu.models.BookBorrowRecord2;
import com.ax003d.sichu.models.BookOwn;

public class BorrowRecordActivity extends Activity {

	private BookBorrowRecord2 mRecord;
	private BookOwn mBookOwn;
	private EditText et_borrower;
	private EditText et_remark;
	private DatePickerFragment datePickerDlg;

	private OnClickListener onClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v.getId() == R.id.et_borrow_date) {
				datePickerDlg.setOnDateSetListener(onBorrowDateSetListener);
				datePickerDlg.show(getSupportFragmentManager());
			} else if (v.getId() == R.id.et_returned_date) {
				datePickerDlg.setOnDateSetListener(onReturnedDateSetListener);
				datePickerDlg.show(getSupportFragmentManager());
			} else if (v.getId() == R.id.btn_save) {

			}
		}
	};

	private DatePickerDialog.OnDateSetListener onBorrowDateSetListener = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub

		}
	};

	private DatePickerDialog.OnDateSetListener onReturnedDateSetListener = new OnDateSetListener() {

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_borrow_record);

		mRecord = new BookBorrowRecord2();

		Bundle extras = getIntent().getExtras();
		mBookOwn = extras.getParcelable("bookown");
		Book book = mBookOwn.getBook();

		TextView tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText(book.getTitle());
		findViewById(R.id.et_borrow_date).setOnClickListener(onClick);
		findViewById(R.id.et_returned_date).setOnClickListener(onClick);
		findViewById(R.id.btn_save).setOnClickListener(onClick);

		et_borrower = (EditText) findViewById(R.id.et_borrower);
		et_remark = (EditText) findViewById(R.id.et_remark);

		datePickerDlg = new DatePickerFragment();
	}

}
