package com.example.photo_gallery.data.repositories.photo;

import com.example.photo_gallery.data.models.photo.PhotoImpl;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface PhotoRepository{

    public Single<List<PhotoImpl>> getAllPhotos();

    public Single<PhotoImpl> getPhotoById (String id);
}
