package com.ibrahim.androidTask.Fragments.Upload.Presenter;

import okhttp3.MultipartBody;

public interface UploadViewPresenter {
    void uploadImage(String email,String type,MultipartBody.Part multipartBodyImage,boolean imageSelected);
}
