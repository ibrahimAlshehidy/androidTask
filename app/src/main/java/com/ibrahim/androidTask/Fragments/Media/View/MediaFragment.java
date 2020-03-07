package com.ibrahim.androidTask.Fragments.Media.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.ibrahim.androidTask.Fragments.Media.Presenter.MediaPresenter;
import com.ibrahim.androidTask.R;
import com.ibrahim.androidTask.Utils.ParentClass;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MediaFragment extends Fragment {
    MediaPresenter mediaPresenter;
    @BindView(R.id.rvMedia)
    ShimmerRecyclerView rvMedia;

    public View onCreateView(LayoutInflater inflater,@Nullable ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.media_fragment,container,false);
        ButterKnife.bind(this,view);
        mediaPresenter = new MediaPresenter(getActivity());
        mediaPresenter.getAllMedia(ParentClass.sharedPrefManager.getUserDate().getEmail(),rvMedia);
        return view;
    }


}
