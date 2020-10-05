package com.example.wallpaerapp;

public class WallpaperModel {

    private  int id;
    private String Origialurl,MediumUrl;

    public WallpaperModel(int id, String origialurl, String mediumUrl) {
        this.id = id;
        Origialurl = origialurl;
        MediumUrl = mediumUrl;
    }

    public int getId() {
        return id;
    }

    public String getOrigialurl() {
        return Origialurl;
    }

    public String getMediumUrl() {
        return MediumUrl;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrigialurl(String origialurl) {
        Origialurl = origialurl;
    }

    public void setMediumUrl(String mediumUrl) {
        MediumUrl = mediumUrl;
    }
}
