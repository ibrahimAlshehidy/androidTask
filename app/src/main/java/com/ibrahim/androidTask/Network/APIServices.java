package com.ibrahim.androidTask.Network;

import com.ibrahim.androidTask.Models.MediaModel.MediaModel;
import com.ibrahim.androidTask.Models.UploadModel;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIServices {
    @Multipart
    @POST(APIUrl.uploadPic)
    Call<UploadModel> uploadImage(
            @Query("email") String email,
            @Query("type") String type,
            @Part MultipartBody.Part image
    );

    @FormUrlEncoded
    @POST(APIUrl.media)
    Call<MediaModel> getMedia(
            @Field("email") String email
    );
}
