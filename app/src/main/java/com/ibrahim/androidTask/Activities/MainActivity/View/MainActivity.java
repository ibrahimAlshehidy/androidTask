package com.ibrahim.androidTask.Activities.MainActivity.View;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ibrahim.androidTask.Activities.MainActivity.Presenter.MainActivityPresenter;
import com.ibrahim.androidTask.Fragments.Media.View.MediaFragment;
import com.ibrahim.androidTask.Fragments.Upload.View.UploadFragment;
import com.ibrahim.androidTask.R;
import com.ibrahim.androidTask.Utils.ParentClass;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends ParentClass implements MainActivityView {
    MainActivityPresenter mainActivityPresenter;
    MediaFragment mediaFragment;
    UploadFragment uploadFragment;
    @BindView(R.id.mainFrame)
    public FrameLayout mainFrame;
    @BindView(R.id.rlUpload)
    RelativeLayout rlUpload;
    @BindView(R.id.ivUpload)
    ImageView ivUpload;
    @BindView(R.id.tvUpload)
    TextView tvUpload;
    @BindView(R.id.rlMedia)
    RelativeLayout rlMedia;
    @BindView(R.id.ivMedia)
    ImageView ivMedia;
    @BindView(R.id.tvMedia)
    TextView tvMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mainActivityPresenter = new MainActivityPresenter(MainActivity.this,this);
        initUI();
    }

    private void initUI() {
        mediaFragment = new MediaFragment();
        uploadFragment = new UploadFragment();
        mainActivityPresenter.changeFragment(uploadFragment,rlUpload,ivUpload,tvUpload,rlMedia,ivMedia,tvMedia);

    }

    @Override
    public void goToMedia() {
        replaceFragment(mediaFragment,mainFrame);
    }

    @Override
    public void goToUpload() {
        replaceFragment(uploadFragment,mainFrame);
    }

    @OnClick(R.id.rlUpload)
    void uploadPic() {
        mainActivityPresenter.changeFragment(uploadFragment,rlUpload,ivUpload,tvUpload,rlMedia,ivMedia,tvMedia);
    }

    @OnClick(R.id.rlMedia)
    void allMedia() {
        mainActivityPresenter.changeFragment(mediaFragment,rlUpload,ivUpload,tvUpload,rlMedia,ivMedia,tvMedia);

    }

    @Override
    public void onBackPressed() {

        try {
            if (fragments.size() > 1) {
                fragments.remove(fragments.size() - 1);

            } else {
                finish();
                System.exit(0);
            }
            super.onBackPressed();

        } catch (Exception e) {

        }
    }
}
