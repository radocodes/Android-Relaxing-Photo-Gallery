package com.example.photo_gallery.ui.shared.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.photo_gallery.data.models.photo.PhotoImpl;
import com.example.photo_gallery.data.repositories.photo.PhotoRepository;
import com.example.photo_gallery.data.repositories.photo.PhotoRepositoryImpl;

import java.util.Collections;
import java.util.List;

public class PhotoViewModel extends ViewModel {

    private PhotoRepository photoRepository;

    public PhotoViewModel() {
        this.photoRepository = new PhotoRepositoryImpl();
    }

    public void fetchAllPhotos() {
        this.photoRepository.fetchAll();
    }

    public MutableLiveData<List<PhotoImpl>> getAllPhotos () {
        return this.photoRepository.getAll();
    }

    public  LiveData<PhotoImpl> getPhotoById (String id) {
        return  this.photoRepository.getPhotoById(id);
    }

    public List<PhotoImpl> SortPhotosAscending (List<PhotoImpl> photoList) {
        Collections.sort(photoList, (photoA, photoB) -> photoA.getAuthor().compareTo(photoB.getAuthor()));
        return photoList;
    }

    public List<PhotoImpl> SortPhotosDescending (List<PhotoImpl> photoList) {
        photoList = this.SortPhotosAscending(photoList);
        Collections.reverse(photoList);
        return photoList;
    }
}