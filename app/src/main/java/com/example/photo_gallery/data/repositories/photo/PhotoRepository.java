package com.example.photo_gallery.data.repositories.photo;

import com.example.photo_gallery.data.models.photo.FavoritePhotoId;
import com.example.photo_gallery.data.models.photo.PhotoDataImpl;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

public interface PhotoRepository{

    Single<List<PhotoDataImpl>> getAllPhotos();

    Single<PhotoDataImpl> getPhotoById (String id);

    Single<List<FavoritePhotoId>> getAllFavoriteIds();

    Single<FavoritePhotoId> getBookmarkedByExternalId(String externalId);

    Completable addToFavorites(FavoritePhotoId favoritePhotoId);

    Completable removeFromFavorites(String externalId);
}
