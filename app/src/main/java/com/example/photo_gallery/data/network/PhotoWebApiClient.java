package com.example.photo_gallery.data.network;

import com.example.photo_gallery.data.models.photo.Photo;
import com.example.photo_gallery.data.models.photo.PhotoImpl;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PhotoWebApiClient {

    @GET("/v2/list")
    Call<List<PhotoImpl>> getAll();

    @GET("/id/{id}/info")
    Call<PhotoImpl> getPhotoById(@Path("id") String id);
}
