package com.example.photo_gallery.data.repositories.photo;

import androidx.lifecycle.LiveData;

import com.example.photo_gallery.data.models.photo.Photo;
import com.example.photo_gallery.data.models.photo.PhotoImpl;

import java.util.List;

public interface PhotoRepository {

    public LiveData<List<PhotoImpl>> getAll ();

    public LiveData<PhotoImpl> getPhotoById (String id);
}
