package com.example.photo_gallery.di;

import com.example.photo_gallery.data.network.PhotoWebApiClient;
import com.example.photo_gallery.data.repositories.photo.PhotoRepository;
import com.example.photo_gallery.data.repositories.photo.PhotoRepositoryImpl;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class PhotoModule {

    public static final String PHOTO_WEB_API_BASE_URL = "https://picsum.photos";

    @Provides
    public static PhotoWebApiClient getPhotoWebApiClient() {
        return new Retrofit.Builder()
                .baseUrl(PHOTO_WEB_API_BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(PhotoWebApiClient.class);
    }

    @Provides
    public static PhotoRepository getPhotoRepository (PhotoRepositoryImpl photoRepositoryImpl) {
        return photoRepositoryImpl;
    }
}
