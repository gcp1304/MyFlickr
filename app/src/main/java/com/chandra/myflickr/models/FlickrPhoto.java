package com.chandra.myflickr.models;

import com.googlecode.flickrjandroid.photos.Photo;

import java.io.Serializable;

public class FlickrPhoto implements Serializable {
    //https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg

    private static final String IMAGE_URL = "https://farm";
    private static final String STATIC_FLICKR = ".staticflickr.com/";

    private String uid;
    private String name;
    private String url;
    private int commentSum = 0;
    private String squareUrl;

    public FlickrPhoto(Photo photo) {
        this.uid = photo.getId();
        this.name = photo.getTitle();
        squareUrl = createSquareUrl(photo);

        //this.commentSum = photo.getComments();
        this.url = createUrl(photo);

    }

    private String createUrl(Photo photo) {
        StringBuilder sbUrl = new StringBuilder(IMAGE_URL);
        sbUrl.append(photo.getFarm());
        sbUrl.append(STATIC_FLICKR);
        sbUrl.append(photo.getServer());
        sbUrl.append("/");
        sbUrl.append(uid);
        sbUrl.append("_");
        sbUrl.append(photo.getSecret());
        sbUrl.append(".");
        sbUrl.append(photo.getOriginalFormat());
        return sbUrl.toString();
    }

    private String createSquareUrl(Photo photo) {
        StringBuilder sbUrl = new StringBuilder(IMAGE_URL);
        sbUrl.append(photo.getFarm());
        sbUrl.append(STATIC_FLICKR);
        sbUrl.append(photo.getServer());
        sbUrl.append("/");
        sbUrl.append(uid);
        sbUrl.append("_");
        sbUrl.append(photo.getSecret());
        sbUrl.append("_q");
        sbUrl.append(".");
        sbUrl.append(photo.getOriginalFormat());
        return sbUrl.toString();
    }

    public String getSquareUrl() { return squareUrl; }
    public String getPhotoId() { return uid; }

    public void setCommentSum(int commentCountSum) {
        commentSum = commentCountSum;
    }

    public int getCommentSum() {
        return commentSum;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }
}