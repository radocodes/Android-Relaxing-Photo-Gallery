package com.example.photo_gallery.data.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.photo_gallery.data.models.photo.FavoritePhotoId;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

@Dao
public interface FavoritePhotoIdsDao {

    @Query("SELECT * FROM FavoritePhotoIds")
    Single<List<FavoritePhotoId>> getAll();

    @Query("SELECT * FROM FavoritePhotoIds WHERE externalId = :externalId")
    Single<FavoritePhotoId> getByExternalId(String externalId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insert(FavoritePhotoId favoritePhotoId);

    @Query("DELETE FROM FavoritePhotoIds WHERE externalId = :externalId")
    Completable delete(String externalId);
}
