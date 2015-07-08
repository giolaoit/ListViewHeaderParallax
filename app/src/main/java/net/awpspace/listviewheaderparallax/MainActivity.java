package net.awpspace.listviewheaderparallax;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements AbsListView.OnScrollListener {

    private static final int SIZE = 50;
    private int lastTopValue = 0;

    private List<String> mData = new ArrayList<>();
    private ListView mListView;
    private ImageView mIvHeader;
    private ArrayAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.activity_main_lv_list);

        for (int i = 0; i < SIZE; i++) {
            mData.add("AwpSpace " + i);
        }

        mAdapter = new ArrayAdapter(this, R.layout.layout_list_row, mData);

        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.layout_list_header, mListView, false);
        mListView.addHeaderView(header, null, false);

        mListView.setAdapter(mAdapter);

        mIvHeader = (ImageView) header.findViewById(R.id.layout_list_header_iv_image);
        mListView.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Rect rect = new Rect();
        mIvHeader.getLocalVisibleRect(rect);
        if (lastTopValue != rect.top) {
            lastTopValue = rect.top;
            mIvHeader.setY((float) (rect.top / 2.0));
        }
    }
}
