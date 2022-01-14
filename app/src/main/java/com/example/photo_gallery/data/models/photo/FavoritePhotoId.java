package com.example.photo_gallery.data.models.photo;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "FavoritePhotoIds")
public class FavoritePhotoId {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String externalId;

    public FavoritePhotoId() {

    }

    public FavoritePhotoId(String externalId) {
        this.externalId = externalId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }
}
