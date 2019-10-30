package com.rndemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Kevin on 2019-10-28<br/>
 * Describe:<br/>
 */
public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    private Context context;
    private List<AlbumEntity> uriList;

    public AlbumAdapter(Context context, List<AlbumEntity> uriList) {
        this.context = context;
        this.uriList = uriList;
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
        Glide.with(context)
                .load(uriList.get(position).url)
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

    }

    @Override
    public int getItemCount() {
        return uriList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView tvItem;
        private RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(R.id.iv_image);
            container = itemView.findViewById(R.id.container);
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
    }
}
