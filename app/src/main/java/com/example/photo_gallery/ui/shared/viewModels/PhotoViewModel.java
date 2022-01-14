package com.example.photo_gallery.ui.shared.viewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.photo_gallery.data.models.photo.PhotoDataImpl;
import com.example.photo_gallery.data.repositories.photo.PhotoRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class PhotoViewModel extends ViewModel {

    private PhotoRepository photoRepository;

    private MutableLiveData<List<PhotoDataImpl>> allPhotos;

    @Inject
    public PhotoViewModel(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
        this.allPhotos = new MutableLiveData<>();
    }

    public void fetchAllPhotos() {
        this.photoRepository.getAllPhotos()
                .subscribeOn(Schedulers.io())
                .subscribe(photos -> { this.allPhotos.postValue(photos); },
                        throwable -> {throwable.printStackTrace();});
    }

    public MutableLiveData<List<PhotoDataImpl>> getAllPhotos () {
        return this.allPhotos;
    }

    public void fetchFavorites() {
        photoRepository.getAllFavoriteIds()
                .subscribeOn(Schedulers.io())
                .flattenAsObservable(idHolder -> idHolder)
                .flatMapSingle(idHolder -> this.photoRepository.getPhotoById(idHolder.getExternalId()))
                .toList()
                .subscribe(allFavorites -> {
                    allPhotos.postValue(allFavorites);
                    }, throwable -> {throwable.printStackTrace();});

    }

    public List<PhotoDataImpl> sortPhotosAscending(List<PhotoDataImpl> photoList) {
        Collections.sort(photoList, (photoA, photoB) -> photoA.getAuthor().compareTo(photoB.getAuthor()));
        return photoList;
    }

    public List<PhotoDataImpl> sortPhotosDescending(List<PhotoDataImpl> photoList) {
        photoList = this.sortPhotosAscending(photoList);
        Collections.reverse(photoList);
        return photoList;
    }

    public List<PhotoDataImpl> filterPhotosByAuthorName(String searchInput) {
        List<PhotoDataImpl> allPhotos = this.getAllPhotos().getValue();
        List<PhotoDataImpl> searchResult = null;

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