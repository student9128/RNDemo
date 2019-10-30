package com.rndemo;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhihu.matisse.internal.ui.MediaSelectionFragment;
import com.zhihu.matisse.internal.ui.adapter.AlbumMediaAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
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
//        for (int i = 0; i < 100; i++) {
//            mUriList.add("item-" + i);
//        }

        //初始化adapter,
        mRecyclerView = new RecyclerView(context);
        adapter = new AlbumAdapter(context, alist);
//        mAdapter = new AlbumMediaAdapter(context, mSelectionProvider.provideSelectedItemCollection(), mRecyclerView);
        //添加RecyclerView
        LayoutParams lp = new LayoutParams(-1, -1);
        mRecyclerView.setLayoutParams(lp);
        linearLayoutManager = new GridLayoutManager(context, 4);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);
        addView(mRecyclerView);
//        getImages();
        getAllImages(context, new AlbumCallback() {
            @Override
            public void onSuccess(ArrayList<AlbumEntity> albumList) {
                //刷新视图
                adapter.updateData(albumList);
            }
        });
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
//            setAdapterData();//设置图片的显示
//            cancelLoading();//取消加载框
//            initListDirPopupWindw();//初始化小相册的popupWindow
//            initChekcBox();//初始化checkbox集合，防止checkBox的错乱
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
//        alist = new ArrayList<>();
//        showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver mContentResolver = context.getContentResolver();

                Cursor mCursor = mContentResolver.query(mImageUri, null,
                        MediaStore.Images.Media.MIME_TYPE + "=? or " +
                                MediaStore.Images.Media.MIME_TYPE + "=? or " +
                                MediaStore.Images.Media.MIME_TYPE + "=?",
                        new String[]{"image/jpeg", "image/png", "image/jpg"},
                        MediaStore.Images.Media.DATE_TAKEN + " DESC");//获取图片的cursor，按照时间倒序（发现没卵用)

                int count = mCursor.getCount();
                Log.d("AlbumView", "count=" + count);
                while (mCursor.moveToNext()) {
                    String path = mCursor.getString(mCursor.getColumnIndex(MediaStore.Images.Media.DATA));// 1.获取图片的路径
                    File parentFile = new File(path).getParentFile();
                    if (parentFile == null)
                        continue;//不获取sd卡根目录下的图片
                    String parentPath = parentFile.getAbsolutePath();//2.获取图片的文件夹信息
                    String parentName = parentFile.getName();
                    ImageFloder imageFloder;//自定义一个model，来保存图片的信息
//                    Log.d("AlbumView", "parentName-" + parentName);
//                    Log.d("AlbumView", "path=" + path);
                    AlbumEntity albumEntity = new AlbumEntity();
                    albumEntity.url = path;
                    alist.add(albumEntity);
                    //这个操作，可以提高查询的效率，将查询的每一个图片的文件夹的路径保存到集合中，
                    //如果存在，就直接查询下一个，避免对每一个文件夹进行查询操作
//                    if (mDirPaths.contains(parentPath)) {
//                        continue;
//                    } else {
//                        mDirPaths.add(parentPath);//将父路径添加到集合中
//                        imageFloder = new ImageFloder();
//                        imageFloder.setFirstImagePath(path);
//                        imageFloder.setDir(parentPath);
//                        imageFloder.setName(parentName);
//                    }
//                    List<String> strings = null;
//                    try {
////                        strings =  Arrays.asList(parentFile.list(getFileterImage()));
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    int picSize = strings.size();//获取每个文件夹下的图片个数
//                    imageFloder.setCount(picSize);//传入每个相册的图片个数
//                    mImageFloders.add(imageFloder);//添加每一个相册
                    //获取图片最多的文件夹信息（父目录对象和个数，使得刚开始显示的是最多图片的相册
//                    if (picSize > mPicsSize) {
//                        mPicsSize = picSize;
//                        mImgDir = parentFile;
//                    }
                }
                mCursor.close();
                mDirPaths = null;
                mHandler.sendEmptyMessage(1);
            }
        }).start();
    }

    public static void getAllImages(Context context, AlbumCallback callback) {
        new MultiAsynTask<Void, Void, ArrayList<AlbumEntity>>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.d("Album", "pre");
            }

            @Override
            protected ArrayList<AlbumEntity> doInBackground(Void... voids) {
                ArrayList<AlbumEntity> albumEntityList = new ArrayList<>();
                Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                ContentResolver contentResolver = context.getContentResolver();
//                Cursor cursor = contentResolver.query(mImageUri, null, MediaStore.Images.Media.MIME_TYPE + "=? or " +
//                                MediaStore.Images.Media.MIME_TYPE + "=? or " +
//                                MediaStore.Images.Media.MIME_TYPE + "=?", new String[]{"image/jpeg", "image/jpg", "image/png"},
//                        MediaStore.Images.Media.DATE_ADDED + " DESC");
                Cursor cursor1 = contentResolver.query(mImageUri, new String[]{"COUNT(*) "}, MediaStore.Images.Media.MIME_TYPE + "=? or " +
                                MediaStore.Images.Media.MIME_TYPE + "=? or " +
                                MediaStore.Images.Media.MIME_TYPE + "=?", new String[]{"image/jpeg", "image/jpg", "image/png"},
                        MediaStore.Images.Media.DATE_ADDED + " DESC");
                if (cursor1 == null) return null;
                cursor1.moveToFirst();
                int photoCount = cursor1.getInt(0);
                Log.d("AlbumView", "count---" + photoCount);
                //一次扫描500张
//                String sortOrder = MediaStore.Images.Media.DATE_ADDED + " DESC";
                int index = 0;
                while (index < photoCount) {
                    String sortOrder = MediaStore.Images.Media.DATE_ADDED + " DESC limit " + index + ",500";
                    Cursor cursor = contentResolver.query(mImageUri, null, MediaStore.Images.Media.MIME_TYPE + "=? or " +
                                    MediaStore.Images.Media.MIME_TYPE + "=? or " +
                                    MediaStore.Images.Media.MIME_TYPE + "=?", new String[]{"image/jpeg", "image/jpg", "image/png"},
                            sortOrder);
                    index += cursor.getCount();
                    while (cursor.moveToNext()) {
                        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));// 1.获取图片的路径
                        AlbumEntity albumEntity = new AlbumEntity();
                        albumEntity.url = path;
                        albumEntityList.add(albumEntity);
                    }
                    cursor.close();
                }

                return albumEntityList;
            }

            @Override
            protected void onPostExecute(ArrayList<AlbumEntity> albumEntities) {
                super.onPostExecute(albumEntities);
                if (albumEntities == null || albumEntities.size() == 0) {
                    return;
                }
                Toast.makeText(context,"完成了",Toast.LENGTH_SHORT).show();
                Log.d("Album", "over");
                callback.onSuccess(albumEntities);
            }
        }.executeX();
    }

    public interface AlbumCallback {
        void onSuccess(ArrayList<AlbumEntity> albumList);
    }

}
