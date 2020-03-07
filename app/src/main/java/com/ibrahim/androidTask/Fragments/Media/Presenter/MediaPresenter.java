package com.ibrahim.androidTask.Fragments.Media.Presenter;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.ibrahim.androidTask.Adapters.MediaAdapter;
import com.ibrahim.androidTask.Models.MediaModel.MediaModel;
import com.ibrahim.androidTask.Network.APIServices;
import com.ibrahim.androidTask.R;
import com.ibrahim.androidTask.Utils.ParentClass;
import com.ibrahim.androidTask.Utils.RetroWeb;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MediaPresenter extends ParentClass implements MediaViewPresenter {
    Context context;

    public MediaPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void getAllMedia(String email,ShimmerRecyclerView rvMedia) {
        MediaAdapter mediaAdapter = new MediaAdapter(context,rvMedia);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context,RecyclerView.VERTICAL,false);
        rvMedia.setLayoutManager(layoutManager);
        RetroWeb.getClient().create(APIServices.class).getMedia(email).enqueue(new Callback<MediaModel>() {
            @Override
            public void onResponse(Call<MediaModel> call,Response<MediaModel> response) {
                if (response.body() != null) {
                    if (response.body().getStatus().equals("ok")) {
                        rvMedia.hideShimmerAdapter();
                        if (response.body().getData().size() != 0) {
                            mediaAdapter.addAll(response.body().getData());
                            mediaAdapter.notifyDataSetChanged();

                        } else {
                            rvMedia.setVisibility(View.GONE);
                            makeErrorToast(context,context.getString(R.string.emptyMedia));
                        }
                    } else {
                        rvMedia.setVisibility(View.GONE);
                        makeErrorToast(context,response.body().getStatus());
                    }

                } else {
                    rvMedia.setVisibility(View.GONE);
                    makeErrorToast(context,context.getString(R.string.somethingWentWrong));
                }
            }

            @Override
            public void onFailure(Call<MediaModel> call,Throwable t) {
                rvMedia.setVisibility(View.GONE);
                handleException(context,t);
            }
        });
        rvMedia.setAdapter(mediaAdapter);

    }
}
