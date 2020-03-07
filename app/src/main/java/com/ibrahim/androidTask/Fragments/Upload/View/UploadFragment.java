package com.ibrahim.androidTask.Fragments.Upload.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ibrahim.androidTask.Fragments.Upload.Presenter.UploadPresenter;
import com.ibrahim.androidTask.R;
import com.ibrahim.androidTask.Utils.ParentClass;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;

public class UploadFragment extends Fragment {
    UploadPresenter uploadPresenter;
    String type = "";
    Bitmap bitmap;
    private static int PICK_IMAGE_REQUEST = 1;
    private static int RESULT_LOAD_VIDEO = 1;
    boolean selected_image = false;
    boolean selected_video = false;
    MultipartBody.Part multipartImage;
    MultipartBody.Part multipartVideo;
    private Uri uri;
    private String pathToStoredVideo;
    @BindView(R.id.ivUploadNewPic)
    ImageView ivUploadNewPic;
    @BindView(R.id.viewVideo)
    VideoView viewUploadVideo;
    @BindView(R.id.rlNewMedia)
    RelativeLayout rlNewMedia;
    @BindView(R.id.tvUploadVideo)
    TextView tvUploadVideo;
    @BindView(R.id.tvUploadImage)
    TextView tvUploadImage;

    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.upload_fragment,container,false);
        ButterKnife.bind(this,view);
        uploadPresenter = new UploadPresenter(getActivity());
        return view;
    }


    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent,PICK_IMAGE_REQUEST);
    }

    public void loadVideoFromGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent,RESULT_LOAD_VIDEO);
    }

    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == RESULT_OK && null != data) {
            rlNewMedia.setVisibility(View.GONE);
            tvUploadImage.setVisibility(View.GONE);
            tvUploadVideo.setVisibility(View.GONE);
            if (type.equals("image")) {
                selected_image = true;
                File file = new File(getRealPathFromUri(getActivity(),data.getData()));
                final Uri imageUri = data.getData();
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"),file);
                multipartImage = MultipartBody.Part.createFormData("value",file.getName(),requestFile);
                RequestBody filename = RequestBody.create(MediaType.parse("text/plain"),file.getName());
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(),imageUri);
                    ivUploadNewPic.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (type.equals("video")) {
                ivUploadNewPic.setVisibility(View.GONE);
                viewUploadVideo.setVisibility(View.VISIBLE);
                selected_video = true;
                uri = data.getData();
                viewUploadVideo.setVideoURI(uri);
                viewUploadVideo.start();
                pathToStoredVideo = getRealPathFromURIPath(uri,getActivity());
                //Store the video to your server
                File videoFile = new File(pathToStoredVideo);
                RequestBody videoBody = RequestBody.create(MediaType.parse("video/*"),videoFile);
                multipartVideo = MultipartBody.Part.createFormData("value",videoFile.getName(),videoBody);


            }

        }
    }

    public String getRealPathFromUri(Context context,Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri,proj,null,null,null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private String getRealPathFromURIPath(Uri contentURI,Activity activity) {
        Cursor cursor = activity.getContentResolver().query(contentURI,null,null,null,null);
        if (cursor == null) {
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }

    @OnClick(R.id.tvUploadImage)
    void chooseImage() {
        openGallery();
        type = "image";
    }

    @OnClick(R.id.tvUploadVideo)
    void chooseVideo() {
        loadVideoFromGallery();
        type = "video";
    }

    @OnClick(R.id.tvUpload)
    void uploadImage() {
        if (type.equals("image")) {
            uploadPresenter.uploadImage(ParentClass.sharedPrefManager.getUserDate().getEmail(),type,multipartImage,selected_image);
        } else {
            uploadPresenter.uploadImage(ParentClass.sharedPrefManager.getUserDate().getEmail(),type,multipartVideo,selected_video);
        }

    }

}
