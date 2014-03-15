package com.ax003d.sichu;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.widget.TextView;

import android.os.Bundle;

import com.ax003d.sichu.models.Book;
import com.ax003d.sichu.models.BookOwn;

public class BorrowRecordActivity extends Activity {

	private BookOwn mBookOwn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_borrow_record);
		
		Bundle extras = getIntent().getExtras();
		mBookOwn = extras.getParcelable("bookown");
		Book book = mBookOwn.getBook();
		
		TextView tv_title = (TextView) findViewById(R.id.tv_title);
		tv_title.setText(book.getTitle());
	}

}
