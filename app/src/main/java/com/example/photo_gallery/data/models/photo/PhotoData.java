package com.example.photo_gallery.data.models.photo;

public interface PhotoData {

    String getId();

    void setId(String id);

    String getAuthor();

    int getWidth();

    int getHeight();

    String getUrl();

    String getDownload_url();
}
