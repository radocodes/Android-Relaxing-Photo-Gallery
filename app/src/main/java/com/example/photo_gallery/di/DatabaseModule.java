package com.example.photo_gallery.di;

import android.app.Application;

import androidx.room.Room;

import com.example.photo_gallery.data.db.FavoritePhotoIdsDao;
import com.example.photo_gallery.data.db.PhotoGalleryDatabase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class DatabaseModule {

    @Provides
    public static PhotoGalleryDatabase getDatabase(Application application) {
        return Room.databaseBuilder(application.getApplicationContext(), PhotoGalleryDatabase.class, PhotoGalleryDatabase.class.getName())
                .build();
    }

    @Provides
    public static FavoritePhotoIdsDao getFavoritePhotoIDsDao(PhotoGalleryDatabase database) {
        return database.favoritePhotoIdsDao();
    }
}
