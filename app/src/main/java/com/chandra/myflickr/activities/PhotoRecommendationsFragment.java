package com.chandra.myflickr.activities;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chandra.myflickr.R;
import com.chandra.myflickr.models.FlickrPhoto;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoRecommendationsFragment extends Fragment {


    private static Context mContext;
    private static ArrayList<FlickrPhoto> mDataArray;

    public static PhotoRecommendationsFragment newInstance(Context context, ArrayList<FlickrPhoto> dataArray) {
        mContext = context;
        mDataArray = dataArray;
        return new PhotoRecommendationsFragment();
    }

    public PhotoRecommendationsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_recommendations, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_list);
        HorizontalRecyclerViewAdapter mAdapter = new HorizontalRecyclerViewAdapter(getActivity(), mDataArray);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(mAdapter);

        return view;
    }

}
