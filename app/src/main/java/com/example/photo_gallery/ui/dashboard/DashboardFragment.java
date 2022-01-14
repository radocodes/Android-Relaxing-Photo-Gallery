package com.example.photo_gallery.ui.dashboard;

import com.example.photo_gallery.ui.base.fragments.DashboardFragmentBase;

public class DashboardFragment extends DashboardFragmentBase {

    @Override
    protected void loadPhotoCollection() {
        super.photoViewModel.fetchAllPhotos();
    }
}