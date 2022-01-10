package com.example.photo_gallery.ui.shared.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.photo_gallery.data.models.photo.PhotoImpl;
import com.example.photo_gallery.data.repositories.photo.PhotoRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PhotoViewModel extends ViewModel {

    private PhotoRepository photoRepository;

    @Inject
    public PhotoViewModel(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
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

    public List<PhotoImpl> sortPhotosAscending (List<PhotoImpl> photoList) {
        Collections.sort(photoList, (photoA, photoB) -> photoA.getAuthor().compareTo(photoB.getAuthor()));
        return photoList;
    }

    public List<PhotoImpl> sortPhotosDescending (List<PhotoImpl> photoList) {
        photoList = this.sortPhotosAscending(photoList);
        Collections.reverse(photoList);
        return photoList;
    }

    public List<PhotoImpl> filterPhotosByAuthorName (String searchInput) {
        List<PhotoImpl> allPhotos = this.getAllPhotos().getValue();
        List<PhotoImpl> searchResult = null;

        if (allPhotos != null) {
            searchResult = allPhotos.stream().filter(photo -> photo.getAuthor().toLowerCase()
                    .contains(searchInput.toLowerCase())).collect(Collectors.toList());

            if (searchResult.size() == 0){
                return allPhotos;
            }
        }

        return searchResult;
    }
}