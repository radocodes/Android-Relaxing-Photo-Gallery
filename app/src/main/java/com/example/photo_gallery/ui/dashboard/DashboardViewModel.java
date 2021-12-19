package com.example.photo_gallery.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.photo_gallery.data.models.photo.PhotoImpl;
import com.example.photo_gallery.data.repositories.photo.PhotoRepository;
import com.example.photo_gallery.data.repositories.photo.PhotoRepositoryImpl;

import java.util.List;

public class DashboardViewModel extends ViewModel {

    private PhotoRepository photoRepository;

    public DashboardViewModel() {
        this.photoRepository = new PhotoRepositoryImpl();
    }

    public LiveData<List<PhotoImpl>> getAllPhotos () {
        return this.photoRepository.getAll();
    }

    public  LiveData<PhotoImpl> getPhotoById (String id) {
        return  this.photoRepository.getPhotoById(id);
    }
}