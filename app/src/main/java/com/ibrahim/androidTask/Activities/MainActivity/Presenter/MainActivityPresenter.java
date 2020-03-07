package com.ibrahim.androidTask.Activities.MainActivity.Presenter;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.ibrahim.androidTask.Activities.MainActivity.View.MainActivityView;
import com.ibrahim.androidTask.R;
import com.ibrahim.androidTask.Utils.ParentClass;

public class MainActivityPresenter extends ParentClass implements MainActivityViewPresenter {
    MainActivityView mainActivityView;
    Context context;

    public MainActivityPresenter(Context context,MainActivityView mainActivityView) {
        this.context = context;
        this.mainActivityView = mainActivityView;
    }

    @Override
    public void changeFragment(Fragment fragment,RelativeLayout rlUpload,ImageView ivUpload,TextView tvUpload,
                               RelativeLayout rlMedia,ImageView ivMedia,TextView tvMedia) {

        if (fragment.getClass().getName().equals("com.ibrahim.androidTask.Fragments.Upload.View.UploadFragment")) {
            YoYo.with(Techniques.Bounce).duration(1500).playOn(ivUpload);
            ivUpload.setImageResource(R.mipmap.upload_blue);
            ivMedia.setImageResource(R.mipmap.media_grey);
            tvUpload.setTextColor(Color.parseColor("#2c51c9"));
            tvMedia.setTextColor(Color.parseColor("#676767"));
            mainActivityView.goToUpload();
        }

        if (fragment.getClass().getName().equals("com.ibrahim.androidTask.Fragments.Media.View.MediaFragment")) {
            YoYo.with(Techniques.Bounce).duration(1500).playOn(ivMedia);
            ivUpload.setImageResource(R.mipmap.upload_grey);
            ivMedia.setImageResource(R.mipmap.media_blue);
            tvUpload.setTextColor(Color.parseColor("#676767"));
            tvMedia.setTextColor(Color.parseColor("#2c51c9"));
            mainActivityView.goToMedia();
        }

    }
}
