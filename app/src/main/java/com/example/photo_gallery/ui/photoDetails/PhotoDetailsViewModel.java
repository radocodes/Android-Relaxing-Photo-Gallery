package com.example.photo_gallery.ui.photoDetails;

import androidx.lifecycle.ViewModel;

import com.example.photo_gallery.data.models.photo.Photo;

public class PhotoDetailsViewModel extends ViewModel {

    private final String SINGLE_WHITE_SPACE = " ";
    private final String NEW_ROW = "\n";

    public String PrepareTextDetailsForPreview(
            String authorNameTicket,
            String originalWidthTicket,
            String originalHeightTicket,
            String photoImageUrlTicket,
            Photo photo) {

        return authorNameTicket + SINGLE_WHITE_SPACE + photo.getAuthor() + NEW_ROW
                + originalWidthTicket + SINGLE_WHITE_SPACE + photo.getWidth() + NEW_ROW
                + originalHeightTicket + SINGLE_WHITE_SPACE + photo.getHeight() + NEW_ROW
                + photoImageUrlTicket + SINGLE_WHITE_SPACE + photo.getDownload_url();
    }
}
