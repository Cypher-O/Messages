package com.example.messages;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class ListViewHelper {
    public static void getListViewSize(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null){
            return;
        }
        // set listadapter in loop for getting final size
        int totalHeight = 0;
        int size;
        for(size = 0; size < listAdapter.getCount(); size++){
            View viewListItem = listAdapter.getView(size, null, listView);
            viewListItem.measure(0, 0);
            totalHeight += viewListItem.getMeasuredHeight();
        }
        // setting listview item in adapter
        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams();
        layoutParams.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() -1));
        listView.setLayoutParams(layoutParams);
        // print height of adapter on log
        Log.i("heih=ght of listitem: ", String.valueOf(totalHeight));
    }
}
