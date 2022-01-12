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

    private MutableLiveData<List<PhotoImpl>> allPhotos;
    private MutableLiveData<PhotoImpl> photoDetailsDto;

    @Inject
    public PhotoViewModel(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
        this.allPhotos = new MutableLiveData<>();
        this.photoDetailsDto = new MutableLiveData<>();
    }

    public void fetchAllPhotos() {
        this.photoRepository.getAllPhotos().subscribe(photos -> { this.allPhotos.postValue(photos); },
                throwable -> {throwable.printStackTrace();});
    }

    public MutableLiveData<List<PhotoImpl>> getAllPhotos () {
        return this.allPhotos;
    }

    public void fetchPhotoDetailsDtoByPhotoId(String id) {
        photoRepository.getPhotoById(id).subscribe(photo -> {this.photoDetailsDto.postValue(photo);},
                throwable -> {throwable.printStackTrace();});
    }

    public LiveData<PhotoImpl> getPhotoDetailsDto() {
        return this.photoDetailsDto;
    }

    public List<PhotoImpl> sortPhotosAscending(List<PhotoImpl> photoList) {
        Collections.sort(photoList, (photoA, photoB) -> photoA.getAuthor().compareTo(photoB.getAuthor()));
        return photoList;
    }

    public List<PhotoImpl> sortPhotosDescending(List<PhotoImpl> photoList) {
        photoList = this.sortPhotosAscending(photoList);
        Collections.reverse(photoList);
        return photoList;
    }

    public List<PhotoImpl> filterPhotosByAuthorName(String searchInput) {
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