package com.rndemo.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import com.rndemo.AlbumEntity;
import com.rndemo.AlbumView;
import com.rndemo.MultiAsynTask;

import java.util.ArrayList;

/**
 * Created by Kevin on 2019-11-02<br/>
 * Describe:<br/>
 */
public class AlbumUtils {
    public static void getAllImages(Context context, AlbumView.AlbumCallback callback) {
        new MultiAsynTask<Void, Void, ArrayList<AlbumEntity>>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
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
                        String size = cursor.getString(cursor.getColumnIndex("_size"));
                        String mime_type = cursor.getString(cursor.getColumnIndex("mime_type"));
                        String display_name = cursor.getString(cursor.getColumnIndex("_display_name"));
                        String dateAdded = cursor.getString(cursor.getColumnIndex("date_added"));
                        String dateModified = cursor.getString(cursor.getColumnIndex("date_modified"));
                        String datetaken = cursor.getString(cursor.getColumnIndex("datetaken"));
//                        Log.d("AlbumView", "size=" + size + ", mimetype=" + mime_type+", displayName="+display_name+", dateAdd="+dateAdded+", dateModified="+dateModified+", dateTaken="+datetaken);
                        AlbumEntity albumEntity = new AlbumEntity();
                        albumEntity.url = path;
                        albumEntity.size = size;
                        albumEntity.mimeType = mime_type;
                        albumEntity.displayName = display_name;
                        albumEntity.dateAdded = dateAdded;
                        albumEntity.dateTaken = datetaken;
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
                Toast.makeText(context, "完成了", Toast.LENGTH_SHORT).show();
                callback.onSuccess(albumEntities);
            }
        }.executeX();
    }
}
