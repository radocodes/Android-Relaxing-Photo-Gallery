package com.example.photo_gallery.data.repositories.photo;

import com.example.photo_gallery.data.models.photo.PhotoDataImpl;

import java.util.List;

import io.reactivex.rxjava3.core.Single;

public interface PhotoRepository{

    public Single<List<PhotoDataImpl>> getAllPhotos();

    public Single<PhotoDataImpl> getPhotoById (String id);
}
