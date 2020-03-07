package com.ibrahim.androidTask.Activities.MainActivity.Presenter;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public interface MainActivityViewPresenter {
    void changeFragment(Fragment fragment,RelativeLayout rlUpload,ImageView ivUpload,TextView tvUpload,RelativeLayout rlMedia,ImageView ivMedia,TextView tvMedia);
}
