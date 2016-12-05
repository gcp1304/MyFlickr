package com.chandra.myflickr.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

import com.chandra.myflickr.R;
import com.chandra.myflickr.models.FlickrPhoto;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.SingleItemRowHolder> {


    private Context mContext;
    private ArrayList<FlickrPhoto> mDataArray;


    public HorizontalRecyclerViewAdapter(Context mContext, ArrayList<FlickrPhoto> dataArray) {
        this.mContext = mContext;
        mDataArray = dataArray;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_single_card, parent, false);
        SingleItemRowHolder vh = new SingleItemRowHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, final int position) {
        FlickrPhoto photo = mDataArray.get(position);
        loadImage(holder, photo.getSquareUrl());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Photo Clicked position : " + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void loadImage(final SingleItemRowHolder holder, String imageUrl) {
        // Index 1 is the progress bar. Show it while we're loading the image.
        holder.animator.setDisplayedChild(1);

        Picasso.with(mContext).load(imageUrl).into(holder.imageView, new Callback.EmptyCallback() {
            @Override public void onSuccess() {
                // Index 0 is the image view.
                holder.animator.setDisplayedChild(0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataArray.size();
    }

    public static class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected ImageView imageView;
        protected ViewAnimator animator;

        public SingleItemRowHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.itemImage);
            animator = (ViewAnimator) view.findViewById(R.id.animator);
        }

    }


}
