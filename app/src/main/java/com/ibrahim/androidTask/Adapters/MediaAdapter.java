package com.ibrahim.androidTask.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.ibrahim.androidTask.Models.MediaModel.Datum;
import com.ibrahim.androidTask.R;
import com.ibrahim.androidTask.Utils.ParentClass;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.ViewHolder> {

    ArrayList<Datum> mediaList;
    Context context;
    LayoutInflater layoutInflater;
    ShimmerRecyclerView rvMedia;

    public MediaAdapter(Context context,ShimmerRecyclerView rvMedia) {
        this.mediaList = new ArrayList<>();
        this.context = context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.rvMedia = rvMedia;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {
        View v = layoutInflater.from(parent.getContext()).inflate(R.layout.item_media,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder,int position) {
        if (mediaList.size() != 0) {
            if (mediaList.get(position).getType().equals("image")) {
                holder.viewVideo.setVisibility(View.GONE);
                ParentClass.LoadImageWithGlide(mediaList.get(position).getValue(),context,holder.ivMedia);
            } else {
                holder.ivMedia.setVisibility(View.GONE);
                holder.viewVideo.setVideoPath(mediaList.get(position).getValue());
                holder.viewVideo.start();
            }
        }

    }

    @Override
    public int getItemCount() {
        if (mediaList.size() == 0) {
            rvMedia.showShimmerAdapter();
            return 10;
        } else {
            return mediaList.size();
        }
    }

    public int getItemViewType(int position) {
        return position;
    }

    public void addAll(List<Datum> data) {
        mediaList.clear();
        mediaList.addAll(data);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivMedia)
        ImageView ivMedia;
        @BindView(R.id.viewVideo)
        VideoView viewVideo;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }
}
