package com.example.photo_gallery.ui.home;

import com.example.photo_gallery.ui.base.fragments.DashboardFragmentBase;

public class FavoritesFragment extends DashboardFragmentBase {

    @Override
    protected void loadPhotoCollection() {
        super.photoViewModel.fetchFavorites();
    }
}