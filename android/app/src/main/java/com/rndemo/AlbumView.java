package com.rndemo;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rndemo.util.AlbumUtils;
import com.rndemo.util.DisplayUtils;
import com.zhihu.matisse.internal.ui.MediaSelectionFragment;
import com.zhihu.matisse.internal.ui.adapter.AlbumMediaAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Kevin on 2019-10-28<br/>
 * Describe:<br/>
 */
public class AlbumView extends LinearLayout {
    private Context context;
    private AlbumMediaAdapter mAdapter;
    private AlbumAdapter adapter;
    private List<String> mUriList = new ArrayList<>();
    private MediaSelectionFragment.SelectionProvider mSelectionProvider;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager linearLayoutManager;
    private List<String> mDirPaths = new ArrayList<>();
    private ArrayList<AlbumEntity> alist = new ArrayList<>();
    private AlbumEntity albumEntity;
    private List<AlbumEntity> photoList = new ArrayList<>();
    private TextView tvApply;

    public AlbumView(Context context) {
        super(context);
        this.initView(context, ((FragmentActivity) context).getSupportFragmentManager());
    }

    public AlbumView(Context context, FragmentManager fragmentManager) {
        super(context);
        this.initView(context, fragmentManager);
    }

    public AlbumView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.initView(context, ((FragmentActivity) context).getSupportFragmentManager());
    }

    public AlbumView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initView(context, ((FragmentActivity) context).getSupportFragmentManager());
    }

    private void initView(Context context, FragmentManager fm) {
        this.context = context;
        setOrientation(LinearLayout.VERTICAL);
//        for (int i = 0; i < 100; i++) {
//            mUriList.add("item-" + i);
//        }

        //初始化adapter,
        mRecyclerView = new RecyclerView(context);
        adapter = new AlbumAdapter(context, alist);
        adapter.setOnAlumItemClickListener(new AlbumAdapter.OnAlbumItemClickListener() {
            @Override
            public void onAlbumItemClick(int position) {
                Toast.makeText(context, "点击了" + position, Toast.LENGTH_SHORT).show();
                Log.d("AlbumView", "alist.size=" + alist.size());
                AlbumEntity albumEntity = alist.get(position);
                String url = albumEntity.url;
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                String pathName;
                Bitmap bitmap = BitmapFactory.decodeFile(url, options);
                int height = options.outHeight;
                int width = options.outWidth;
                Log.d("AlbumView", "width=" + width + ", height=" + height);
                Log.d("AlbumView", albumEntity.toString());
//                setAlbumEntity(albumEntity);
                if (listener != null) {
                    listener.photoItemClick(albumEntity);
                }

            }

            @Override
            public void onAlbumItemChecked(boolean isChecked, List<AlbumEntity> checkList) {
//                String url = albumEntity.url;
//                BitmapFactory.Options options = new BitmapFactory.Options();
//                options.inJustDecodeBounds = true;
//                Bitmap bitmap = BitmapFactory.decodeFile(url,options);
//                int height = options.outHeight;
//                int width = options.outWidth;
                photoList = checkList;
                if (photoList.size() > 0) {
                    tvApply.setClickable(true);
                    tvApply.setTextColor(Color.BLACK);
                } else {
                    tvApply.setClickable(false);
                    tvApply.setTextColor(Color.GRAY);

                }
            }
        });
//        mAdapter = new AlbumMediaAdapter(context, mSelectionProvider.provideSelectedItemCollection(), mRecyclerView);
        //添加RecyclerView
        LayoutParams lp = new LayoutParams(-1, -1);
        lp.weight = 1;
        mRecyclerView.setLayoutParams(lp);
        linearLayoutManager = new GridLayoutManager(context, 3);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);
        addView(mRecyclerView);
        RelativeLayout rlContainer = new RelativeLayout(context);
        RelativeLayout.LayoutParams l = new RelativeLayout.LayoutParams(-1, DisplayUtils.dip2px(this.context, 60));
        rlContainer.setLayoutParams(l);
        TextView tvPreview = new TextView(context);
        RelativeLayout.LayoutParams lpPreview = new RelativeLayout.LayoutParams(-2, -2);
        lpPreview.addRule(RelativeLayout.ALIGN_LEFT);
        lpPreview.addRule(RelativeLayout.CENTER_VERTICAL);
        lpPreview.leftMargin = DisplayUtils.dip2px(context, 16);
        tvPreview.setLayoutParams(lpPreview);
        tvPreview.setText("预览");
        tvPreview.setTextSize(16);
        tvPreview.setGravity(Gravity.RIGHT);
        tvPreview.setClickable(false);
        tvPreview.setTextColor(Color.GRAY);
        rlContainer.addView(tvPreview);
        tvApply = new TextView(context);
        RelativeLayout.LayoutParams tvLp = new RelativeLayout.LayoutParams(-2, -2);
        tvLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        tvLp.addRule(RelativeLayout.CENTER_IN_PARENT);
        tvLp.rightMargin = DisplayUtils.dip2px(context, 16);
        tvApply.setLayoutParams(tvLp);
        tvApply.setText("使用");
        tvApply.setTextSize(16);
        tvApply.setGravity(Gravity.RIGHT);
        tvApply.setClickable(false);
        tvApply.setTextColor(Color.GRAY);
        rlContainer.addView(tvApply);
        addView(rlContainer);
        tvApply.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.photoItemChecked(photoList);
                }
            }
        });

//        getImages();
        AlbumUtils.getAllImages(context, new AlbumCallback() {
            @Override
            public void onSuccess(ArrayList<AlbumEntity> albumList) {
                //刷新视图
                adapter.updateData(albumList);
            }
        });
    }

    public void setAlbumEntity(AlbumEntity a) {
        albumEntity = a;
    }

    public AlbumEntity getEntity() {
        return albumEntity;

    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Log.d("AlbumView", "成功了" + alist.size());
                    adapter.updateData(alist);
                    break;
            }
        }
    };

    @Override
    public void requestLayout() {
        super.requestLayout();
        post(new Runnable() {
            @Override
            public void run() {
                measure(
                        MeasureSpec.makeMeasureSpec(getWidth(), MeasureSpec.EXACTLY),
                        MeasureSpec.makeMeasureSpec(getHeight(), MeasureSpec.EXACTLY)
                );
                layout(getLeft(), getTop(), getRight(), getBottom());
            }
        });
    }

    //获取图片的路径和父路径 及 图片size
    private void getImages() {
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.d("AlbumView", "检测到没有内存卡");
            return;
        }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
//                ContentResolver mContentResolver = context.getContentResolver();
//
//                Cursor mCursor = mContentResolver.query(mImageUri, null,
//                        MediaStore.Images.Media.MIME_TYPE + "=? or " +
//                                MediaStore.Images.Media.MIME_TYPE + "=? or " +
//                                MediaStore.Images.Media.MIME_TYPE + "=?",
//                        new String[]{"image/jpeg", "image/png", "image/jpg"},
//                        MediaStore.Images.Media.DATE_TAKEN + " DESC");//获取图片的cursor，按照时间倒序（发现没卵用)
//
//                int count = mCursor.getCount();
//                Log.d("AlbumView", "count=" + count);
//                while (mCursor.moveToNext()) {
//                    String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));// 1.获取图片的路径
//                    File parentFile = new File(path).getParentFile();
//                    if (parentFile == null)
//                        continue;//不获取sd卡根目录下的图片
//                    String parentPath = parentFile.getAbsolutePath();//2.获取图片的文件夹信息
//                    String parentName = parentFile.getName();
//                    ImageFloder imageFloder;//自定义一个model，来保存图片的信息
////                    Log.d("AlbumView", "parentName-" + parentName);
////                    Log.d("AlbumView", "path=" + path);
//                    AlbumEntity albumEntity = new AlbumEntity();
//                    albumEntity.url = path;
//                    alist.add(albumEntity);
//                }
//                mCursor.close();
//                mDirPaths = null;
//                mHandler.sendEmptyMessage(1);
//            }
//        }).start();
    }


    public interface AlbumCallback {
        void onSuccess(ArrayList<AlbumEntity> albumList);
    }

    public interface PhotoItemClickListener {
        void photoItemClick(AlbumEntity albumEntity);

        void photoItemChecked(List<AlbumEntity> albumEntityList);
    }

    private PhotoItemClickListener listener;

    public void setOnPhotoItemClickListener(PhotoItemClickListener l) {
        listener = l;
    }


}
