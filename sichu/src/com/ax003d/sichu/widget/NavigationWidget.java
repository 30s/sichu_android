
package com.ax003d.sichu.widget;

import org.holoeverywhere.LayoutInflater;
import org.holoeverywhere.widget.LinearLayout;
import org.holoeverywhere.widget.ListView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;

import com.ax003d.sichu.R;

public class NavigationWidget extends LinearLayout {
    private final ListView list;

    public NavigationWidget(Context context) {
        this(context, null);
    }

    public NavigationWidget(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationWidget(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.inflate(context, R.layout.navigation_widget, this, true);
        list = (ListView) findViewById(R.id.navigationListView);
    }

    public ListView getListView() {
        return list;
    }
    
    public void setAdapter(ListAdapter adapter) {
    	list.setAdapter(adapter);
    }
    
    public void setOnItemClickListener(OnItemClickListener listener) {
    	list.setOnItemClickListener(listener);
    }
    
    public void performItemClick(int position) {
    	list.performItemClick(null, position, 0);
    }
}
