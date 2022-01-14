package com.example.photo_gallery.data.repositories.photo;

import com.example.photo_gallery.data.db.FavoritePhotoIdsDao;
import com.example.photo_gallery.data.models.photo.FavoritePhotoId;
import com.example.photo_gallery.data.models.photo.PhotoDataImpl;
import com.example.photo_gallery.data.network.PhotoWebApiClient;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public class PhotoRepositoryImpl implements PhotoRepository {

    private PhotoWebApiClient photoWebApiClient;
    private FavoritePhotoIdsDao favoritePhotoIdsDao;

    @Inject
    public PhotoRepositoryImpl(PhotoWebApiClient photoWebApiClient, FavoritePhotoIdsDao favoritePhotoIdsDao) {
        this.photoWebApiClient = photoWebApiClient;
        this.favoritePhotoIdsDao = favoritePhotoIdsDao;
    }

    @Override
    public Single<List<PhotoDataImpl>> getAllPhotos() {
        return photoWebApiClient.getAll();
    }

    @Override
    public Single<PhotoDataImpl> getPhotoById(String id) {
        return this.photoWebApiClient.getPhotoById(id);
    }

    @Override
    public Single<List<FavoritePhotoId>> getAllFavoriteIds() {
        return this.favoritePhotoIdsDao.getAll();
    }

    @Override
    public Single<FavoritePhotoId> getBookmarkedByExternalId(String externalId) {
        return this.favoritePhotoIdsDao.getByExternalId(externalId);
    }

    @Override
    public Completable addToFavorites(FavoritePhotoId favoritePhotoId) {
        return this.favoritePhotoIdsDao.insert(favoritePhotoId);
    }

    @Override
    public Completable removeFromFavorites(String externalId) {
        return this.favoritePhotoIdsDao.delete(externalId);
    }
}
