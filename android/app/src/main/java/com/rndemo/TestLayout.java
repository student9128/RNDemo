package com.rndemo;

import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 2019-10-28<br/>
 * Describe:<br/>
 */
public class TestLayout extends SimpleViewManager<LinearLayout> {
    private AlbumAdapter adapter;
    private List<String> mUriList = new ArrayList<>();
    private static LinearLayout mContainer;
    private static ThemedReactContext mContext;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    @NonNull
    @Override
    public String getName() {
        return "LinearWidget";
    }

    @NonNull
    @Override
    protected LinearLayout createViewInstance(@NonNull ThemedReactContext reactContext) {
        mContext = reactContext;
        mContainer = new LinearLayout(reactContext);
//        {
//            @Override
//            public void requestLayout() {
//                super.requestLayout();
//                post(new Runnable() {
//                    @Override
//                    public void run() {
//                        measure(
//                                MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY),
//                                MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.EXACTLY)
//                        );
//                        layout(getLeft(), getTop(), getRight(), getBottom());
//                    }
//                });
//            }
//        };
//        TextView textView = new TextView(mContext);
//        LinearLayout.LayoutParams ll = new LinearLayout.LayoutParams(200, 100);
//        textView.setLayoutParams(ll);
//        textView.setText("Hi-------");
//        for (int i = 0; i < 30; i++) {
//            mUriList.add("item-" + i);
//        }

        //初始化adapter,
//        adapter = new AlbumAdapter(mContext, mUriList);
////        mAdapter = new AlbumMediaAdapter()
//        //添加RecyclerView
//        mRecyclerView = new RecyclerView(mContext);
//        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -1);
//        mRecyclerView.setLayoutParams(lp);
//        linearLayoutManager = new LinearLayoutManager(mContext);
//        mRecyclerView.setLayoutManager(linearLayoutManager);
//        mRecyclerView.setAdapter(adapter);
//        mContainer.addView(mRecyclerView);
//        mContainer.addView(textView);
        return mContainer;
    }
}
