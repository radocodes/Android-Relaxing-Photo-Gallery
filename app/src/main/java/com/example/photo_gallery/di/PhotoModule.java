package com.example.photo_gallery.di;

import com.example.photo_gallery.data.repositories.photo.PhotoRepository;
import com.example.photo_gallery.data.repositories.photo.PhotoRepositoryImpl;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class PhotoModule {

    @Provides
    public static PhotoRepository getPhotoRepository (PhotoRepositoryImpl photoRepositoryImpl) {
        return photoRepositoryImpl;
    }
}
