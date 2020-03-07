package com.ibrahim.androidTask.Fragments.Upload.Presenter;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import okhttp3.MultipartBody;

public interface UploadViewPresenter {
    void uploadImage(String email,String type,MultipartBody.Part multipartBodyImage,boolean imageSelected,TextView tvUploadImage,
                     TextView tvUploadVideo,RelativeLayout rlNewMedia,ImageView ivMedia,VideoView uploadVideo);
}
