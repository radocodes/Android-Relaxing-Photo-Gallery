package com.example.photo_gallery.data.network;

import com.example.photo_gallery.data.models.photo.PhotoDataImpl;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PhotoWebApiClient {

    @GET("/v2/list")
    Single<List<PhotoDataImpl>> getAll();

    @GET("/id/{id}/info")
    Single<PhotoDataImpl> getPhotoById(@Path("id") String id);
}
