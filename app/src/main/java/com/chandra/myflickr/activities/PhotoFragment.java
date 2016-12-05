package com.chandra.myflickr.activities;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chandra.myflickr.R;
import com.chandra.myflickr.adapters.PhotoViewerPagerAdapter;
import com.chandra.myflickr.models.FlickrPhoto;

import java.util.ArrayList;

import butterknife.BindView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoFragment extends Fragment {


    protected static ArrayList<FlickrPhoto> mDataArray;
    protected PhotoViewerPagerAdapter mAdapter;
    protected static int position;
    protected static Context mContext;

    ViewPager mViewPager;

    public static PhotoFragment newInstance(Context context, ArrayList<FlickrPhoto> dataArray, int pos) {
        mContext = context;
        mDataArray = dataArray;
        position = pos;
        return new PhotoFragment();
    }


    public PhotoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_images_slider);
        mAdapter = new PhotoViewerPagerAdapter(getActivity(), mDataArray);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(position);

        FragmentManager manager = ((PhotoBrowserActivity)mContext).getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = manager.beginTransaction();

        PhotoRecommendationsFragment photoRecommendationsFragment = PhotoRecommendationsFragment.newInstance(mContext, mDataArray);
        fragmentTransaction.add(R.id.photo_recommendations_fragment_container, photoRecommendationsFragment);
        fragmentTransaction.commit();

        return view;
    }
}
