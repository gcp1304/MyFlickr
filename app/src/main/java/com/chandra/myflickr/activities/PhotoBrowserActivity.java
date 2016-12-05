package com.chandra.myflickr.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.chandra.myflickr.R;
import com.chandra.myflickr.models.FlickrPhoto;

import java.util.ArrayList;

public class PhotoBrowserActivity extends BaseActivity {

    private static final String EXTRACT_DATA = "data-extract";
    private static final String EXTRACT_POSITION = "position-extract";


    protected ArrayList<FlickrPhoto> mDataArray;
    protected int position;

    public static Intent newInstance(Context context, ArrayList<FlickrPhoto> mDataArray, int position) {
        Intent intent = new Intent(context, PhotoBrowserActivity.class);
        intent.putExtra(EXTRACT_DATA, mDataArray);
        intent.putExtra(EXTRACT_POSITION, position);
        return intent;
    }

    @Override
    protected void setLayoutResource() {
        setContentView(R.layout.activity_photo_browser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null)
            finish();

        position = bundle.getInt(EXTRACT_POSITION);
        mDataArray = (ArrayList<FlickrPhoto>) bundle.getSerializable(EXTRACT_DATA);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        PhotoFragment photoFragment = PhotoFragment.newInstance(this, mDataArray, position);
        fragmentTransaction.add(R.id.photo_fragment_container, photoFragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
