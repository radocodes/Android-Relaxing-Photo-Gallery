package com.example.photo_gallery.data.repositories.photo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.photo_gallery.data.models.photo.PhotoImpl;

import java.util.List;

public interface PhotoRepository {

    public void fetchAll ();

    public MutableLiveData<List<PhotoImpl>> getAll ();

    public LiveData<PhotoImpl> getPhotoById (String id);
}
