package com.example.photo_gallery.ui.photoDetails;

import androidx.lifecycle.ViewModel;

import com.example.photo_gallery.data.models.photo.PhotoData;

public class PhotoDetailsViewModel extends ViewModel {

    private final String SINGLE_WHITE_SPACE = " ";
    private final String NEW_ROW = "\n";

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
