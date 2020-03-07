package com.ibrahim.androidTask.Fragments.Upload.Presenter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.ibrahim.androidTask.Fragments.Upload.View.UploadFragment;
import com.ibrahim.androidTask.Models.UploadModel;
import com.ibrahim.androidTask.Network.APIServices;
import com.ibrahim.androidTask.R;
import com.ibrahim.androidTask.Utils.ParentClass;
import com.ibrahim.androidTask.Utils.RetroWeb;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadPresenter extends ParentClass implements UploadViewPresenter {

    Context context;


    public UploadPresenter(Context context) {
        this.context = context;

    }

    @Override
    public void uploadImage(String email,String type,MultipartBody.Part multipartBodyImage,boolean imageSelected,TextView tvUploadImage,
                            TextView tvUploadVideo,RelativeLayout rlNewMedia,ImageView ivMedia,VideoView uploadVideo) {
        if (!imageSelected) {
            makeErrorToast(context,context.getString(R.string.chooseMedia));
        } else {
            showFlipDialog();
            RetroWeb.getClient().create(APIServices.class).uploadImage(email,type,multipartBodyImage).enqueue(new Callback<UploadModel>() {
                @Override
                public void onResponse(Call<UploadModel> call,Response<UploadModel> response) {
                    dismissFlipDialog();

                    if (response.body() != null) {
                        if (response.body().getStatus().equals("ok")) {
                            ivMedia.setVisibility(View.GONE);
                            uploadVideo.setVisibility(View.GONE);
                            rlNewMedia.setVisibility(View.VISIBLE);
                            tvUploadImage.setVisibility(View.VISIBLE);
                            tvUploadVideo.setVisibility(View.VISIBLE);
                            UploadFragment.selected_video = false;
                            UploadFragment.selected_image = false;
                            makeSuccessToast(context,response.body().getStatus());
                        } else {
                            makeErrorToast(context,response.body().getStatus());
                        }

                    } else {
                        makeErrorToast(context,context.getString(R.string.somethingWentWrong));
                    }
                }

                @Override
                public void onFailure(Call<UploadModel> call,Throwable t) {
                    dismissFlipDialog();
                    handleException(context,t);
                }
            });
        }


    }
}
