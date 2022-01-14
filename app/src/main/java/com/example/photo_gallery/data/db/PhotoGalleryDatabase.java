package com.example.photo_gallery.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.photo_gallery.data.models.photo.FavoritePhotoId;

@Database(entities = {FavoritePhotoId.class}, version = 1)
public abstract class PhotoGalleryDatabase extends RoomDatabase {

    public abstract FavoritePhotoIdsDao favoritePhotoIdsDao();
}
