package com.example.photo_gallery.ui.photoDetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.photo_gallery.data.models.photo.FavoritePhotoId;
import com.example.photo_gallery.data.models.photo.PhotoData;
import com.example.photo_gallery.data.models.photo.PhotoDataImpl;
import com.example.photo_gallery.data.repositories.photo.PhotoRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.schedulers.Schedulers;

@HiltViewModel
public class PhotoDetailsViewModel extends ViewModel {

    private final String SINGLE_WHITE_SPACE = " ";
    private final String NEW_ROW = "\n";

    private PhotoRepository photoRepository;

    private MutableLiveData<PhotoDataImpl> photoData;
    private MutableLiveData<Boolean> isBookmarked;

    @Inject
    public  PhotoDetailsViewModel(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
        this.photoData = new MutableLiveData<>();
        this.isBookmarked = new MutableLiveData<>();
    }

    public void fetchPhotoDetailsByPhotoId(String id) {
        this.photoRepository.getPhotoById(id)
                .subscribeOn(Schedulers.io())
                .subscribe(photoData -> {
                    photoData.setId(id);
                    this.photoData.postValue(photoData);
        }, throwable -> {throwable.printStackTrace();});
    }

    public LiveData<PhotoDataImpl> getPhotoDetails() {
        return this.photoData;
    }

    public LiveData<Boolean> isBookmarked() {
        return this.isBookmarked;
    }

    public void fetchIsBookmarked(String externalId) {
        photoRepository.getBookmarkedByExternalId(externalId)
                .subscribeOn(Schedulers.io())
                .onErrorReturn(defaultValue -> new FavoritePhotoId())
                .subscribe(bookmarkedPhotoIdHolder -> {
                    if (bookmarkedPhotoIdHolder.getId() == 0) {
                        this.isBookmarked.postValue(false);
                    }
                    else {
                        this.isBookmarked.postValue(true);
                    }
        }, throwable -> {throwable.printStackTrace();});
    }

    public void addToFavorites(PhotoDataImpl photoData) {
        this.photoRepository.addToFavorites(new FavoritePhotoId(photoData.getId()))
                .subscribeOn(Schedulers.io())
                .subscribe(() -> {
                    this.isBookmarked.postValue(true);
        });
    }

    public void removeFromFavorites(String externalId) {
        this.photoRepository.removeFromFavorites(externalId)
                .subscribeOn(Schedulers.io())
                .subscribe(() -> {
                    this.isBookmarked.postValue(false);
                });
    }

    public String PrepareTextDetailsForPreview(
            String authorNameTicket,
            String originalWidthTicket,
            String originalHeightTicket,
            String photoImageUrlTicket,
            PhotoData photoData) {

        return authorNameTicket + SINGLE_WHITE_SPACE + photoData.getAuthor() + NEW_ROW
                + originalWidthTicket + SINGLE_WHITE_SPACE + photoData.getWidth() + NEW_ROW
                + originalHeightTicket + SINGLE_WHITE_SPACE + photoData.getHeight() + NEW_ROW
                + photoImageUrlTicket + SINGLE_WHITE_SPACE + photoData.getDownload_url();
    }
}
