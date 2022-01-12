package com.example.photo_gallery.data.repositories.photo;

import androidx.lifecycle.MutableLiveData;

import com.example.photo_gallery.data.models.photo.PhotoImpl;
import com.example.photo_gallery.data.network.PhotoWebApiClient;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;

public class PhotoRepositoryImpl implements PhotoRepository {

    private PhotoWebApiClient photoWebApiClient;

    private MutableLiveData<List<PhotoImpl>> allPhotosResult;
    private MutableLiveData<PhotoImpl> singlePhotoResult;

    @Inject
    public PhotoRepositoryImpl(PhotoWebApiClient photoWebApiClient) {
        this.photoWebApiClient = photoWebApiClient;
        allPhotosResult = new MutableLiveData<>();
        singlePhotoResult = new MutableLiveData<>();
    }

    @Override
    public Single<List<PhotoImpl>> getAllPhotos() {
        return photoWebApiClient.getAll();
    }

    @Override
    public Single<PhotoImpl> getPhotoById(String id) {
        return photoWebApiClient.getPhotoById(id);
    }
}
