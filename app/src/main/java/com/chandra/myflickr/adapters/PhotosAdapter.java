package com.chandra.myflickr.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewAnimator;

import com.chandra.myflickr.R;
import com.chandra.myflickr.models.FlickrPhoto;
import com.chandra.myflickr.utils.DeviceUtils;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.ViewHolder> {

    private final Logger logger = LoggerFactory.getLogger(PhotosAdapter.class.getSimpleName());
    private static int imageHeight = 0;

    private OnPhotoItemClickListener listener;
    private ArrayList<FlickrPhoto> mDataArray;
    private Context mContext;

    public PhotosAdapter(Context context, ArrayList<FlickrPhoto> data) {
        mContext = context;
        mDataArray = data;
        listener = (OnPhotoItemClickListener) context;
        imageHeight = DeviceUtils.getDeviceScreenHeight(context) / 3;
    }

    public void updateDataArray(ArrayList<FlickrPhoto> newDataArray) {
        this.mDataArray = newDataArray;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        if (position < 0 || position >= mDataArray.size())
            return;

        final FlickrPhoto photo = mDataArray.get(position);
        String imageUrl = "";

        if (photo != null) {
            imageUrl = photo.getUrl();
        }

        //Config
        ViewGroup.LayoutParams params = holder.ivPhoto.getLayoutParams();
        params.height = imageHeight;
        holder.ivPhoto.setLayoutParams(params);

        loadImage(holder, imageUrl);

        holder.ivPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPhotoItemClicked(position);
            }
        });
    }

    private void loadImage(final ViewHolder holder, String imageUrl) {
        // Index 1 is the progress bar. Show it while we're loading the image.
        holder.animator.setDisplayedChild(1);

        Picasso.with(mContext).load(imageUrl).into(holder.ivPhoto, new Callback.EmptyCallback() {
            @Override public void onSuccess() {
                // Index 0 is the image view.
                holder.animator.setDisplayedChild(0);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mDataArray == null)
            return 0;
        return mDataArray.size();
    }

    public interface OnPhotoItemClickListener {
        void onPhotoItemClicked(int position);
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view)
        ImageView ivPhoto;

        @BindView(R.id.animator)
        ViewAnimator animator;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
