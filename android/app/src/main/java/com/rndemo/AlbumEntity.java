package com.rndemo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by Kevin on 2019-10-29<br/>
 * Describe:<br/>
 */
public class AlbumEntity implements Serializable, Parcelable {
    public String url;
    public int isSelect;

    public AlbumEntity() {
    }

    protected AlbumEntity(Parcel in) {
        url = in.readString();
        isSelect = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
        dest.writeInt(isSelect);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AlbumEntity> CREATOR = new Creator<AlbumEntity>() {
        @Override
        public AlbumEntity createFromParcel(Parcel in) {
            return new AlbumEntity(in);
        }

        @Override
        public AlbumEntity[] newArray(int size) {
            return new AlbumEntity[size];
        }
    };
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AlbumEntity{");
        sb.append("url='").append(url).append('\'');
        sb.append(", isSelect=").append(isSelect);
        sb.append('}');
        return sb.toString();
    }
    @Override
    public int hashCode() {//使用hashcode和equals方法防止重复
        if(url != null)return url.hashCode();
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof AlbumEntity){
            return o.hashCode() == this.hashCode();
        }
        return super.equals(o);

    }
}
