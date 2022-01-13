package com.example.photo_gallery.data.repositories.photo;

import com.example.photo_gallery.data.models.photo.PhotoDataImpl;
import com.example.photo_gallery.data.network.PhotoWebApiClient;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class PhotoRepositoryImpl implements PhotoRepository {

    private PhotoWebApiClient photoWebApiClient;

    @Inject
    public PhotoRepositoryImpl(PhotoWebApiClient photoWebApiClient) {
        this.photoWebApiClient = photoWebApiClient;
    }

    @Override
    public Single<List<PhotoDataImpl>> getAllPhotos() {
        return photoWebApiClient.getAll();
    }

    @Override
    public Single<PhotoDataImpl> getPhotoById(String id) {
        return photoWebApiClient.getPhotoById(id);
    }
}
