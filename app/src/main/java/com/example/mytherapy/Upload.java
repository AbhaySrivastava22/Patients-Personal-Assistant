package com.example.mytherapy;

import com.google.firebase.database.Exclude;

class Upload {
    private String mImageUrl;
    private String mKey;
    private String mName;

    public Upload() {
    }

    public Upload(String name, String imageUrl) {
        if (name.trim().equals("")) {
            name = "No Name";
        }
        this.mName = name;
        this.mImageUrl = imageUrl;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    @Exclude
    public String getKey() {
        return this.mKey;
    }

    @Exclude
    public void setKey(String key) {
        this.mKey = key;
    }
}
