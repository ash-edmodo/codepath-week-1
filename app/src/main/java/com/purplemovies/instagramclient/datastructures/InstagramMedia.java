package com.purplemovies.instagramclient.datastructures;

import java.util.Date;

public class InstagramMedia {

    private final String mProfileImageUrl;
    private int mImageHeight;
    private int mLikesCount;
    private final Date createTime;
    private String mType;
    private String mCaption;
    private String mImageUrl;
    private String mAuthorName;

    public InstagramMedia(String type, String caption, String imageUrl, String authorName, String profileImageUrl, int imageHeight, int likesCount, Date createTime) {
        mType = type;
        mCaption = caption;
        mImageUrl = imageUrl;
        mAuthorName = authorName;
        mProfileImageUrl = profileImageUrl;
        mImageHeight = imageHeight;
        mLikesCount = likesCount;
        this.createTime = createTime;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getCaption() {
        return mCaption;
    }

    public void setCaption(String caption) {
        mCaption = caption;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getAuthorName() {
        return mAuthorName;
    }

    public void setAuthorName(String authorName) {
        mAuthorName = authorName;
    }

    public int getLikesCount() {
        return mLikesCount;
    }

    public void setLikesCount(int mLikesCount) {
        this.mLikesCount = mLikesCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getmProfileImageUrl() {
        return mProfileImageUrl;
    }
}