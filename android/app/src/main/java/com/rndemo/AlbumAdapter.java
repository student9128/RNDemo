package com.rndemo;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Kevin on 2019-10-28<br/>
 * Describe:<br/>
 */
public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    private Context context;
    private List<AlbumEntity> uriList;
    private List<AlbumEntity> checkList;

    public AlbumAdapter(Context context, List<AlbumEntity> uriList) {
        this.context = context;
        this.uriList = uriList;
        checkList = new ArrayList<>();
    }

    public void updateData(List<AlbumEntity> d) {
        uriList.addAll(d);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AlbumAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumAdapter.ViewHolder holder, int position) {
//        holder.tvItem.setText(uriList.get(position).url);
        final AlbumEntity albumEntity = uriList.get(position);
        Glide.with(context)
                .load(albumEntity.url)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
//                .override(200,200)
                .into(holder.tvItem);
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onAlbumItemClick(position);
                }
            }
        });
        holder.checkView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkList.size()>=9){
                    holder.checkView.setChecked(false);
//                    holder.checkView.setClickable(false);
//                    holder.checkView.setEnabled(false);
                    Toast.makeText(context,"最多只能选择9张",Toast.LENGTH_SHORT).show();
                    return;
                }
                albumEntity.isSelect = isChecked ? 1 : 0;
                if (isChecked) {
                    checkList.add(albumEntity);
                } else {
//                    for (AlbumEntity a : photoList) {
//                        a.url = albumEntity.url;
//                        photoList.remove(albumEntity);
//                    }
                    //   java.util.ConcurrentModificationException
                    //        at java.util.ArrayList$Itr.next(ArrayList.java:860)
                    Iterator<AlbumEntity> iterator = checkList.iterator();
                    while (iterator.hasNext()) {
                        AlbumEntity next = iterator.next();
                        if (next.url==albumEntity.url){
                            checkList.remove(albumEntity);
                        }
                    }

                }
                if (listener != null) {
                    listener.onAlbumItemChecked(isChecked,checkList);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return uriList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView tvItem;
        private RelativeLayout container;
        private AppCompatCheckBox checkView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.iv_image);
            container = itemView.findViewById(R.id.container);
            checkView = itemView.findViewById(R.id.cb_checkBox);
        }
    }

    @Override
    public int getItemViewType(int position) {
        //当集合数据为0或null的时候，返回占位图,数据正在加载中... TYPE_EMPTY
        return super.getItemViewType(position);
    }

    private OnAlbumItemClickListener listener;

    public void setOnAlumItemClickListener(OnAlbumItemClickListener l) {
        listener = l;

    }

    public interface OnAlbumItemClickListener {
        void onAlbumItemClick(int position);

        void onAlbumItemChecked(boolean isChecked,List<AlbumEntity> checkList);
    }
}
