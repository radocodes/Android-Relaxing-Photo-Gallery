package com.example.photo_gallery.ui.photoDetails;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.photo_gallery.R;
import com.example.photo_gallery.data.models.photo.PhotoDataImpl;
import com.example.photo_gallery.databinding.FragmentPhotoDetailsBinding;
import com.example.photo_gallery.ui.shared.viewModels.PhotoViewModel;

import org.jetbrains.annotations.NotNull;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PhotoDetailsFragment extends Fragment {

    private String currentPhotoId;
    private ViewModelProvider viewModelProvider;
    private PhotoDetailsViewModel photoDetailsViewModel;
    private PhotoViewModel photoViewModel;
    private FragmentPhotoDetailsBinding binding;

    private TextView textViewPhotoDetails;
    private ImageView photoImage;
    private ImageView bookmarkImageButton;
    private TextView textViewBookmark;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        this.viewModelProvider = new ViewModelProvider(this);
        this.photoViewModel = this.viewModelProvider.get(PhotoViewModel.class);
        this.photoDetailsViewModel = this.viewModelProvider.get(PhotoDetailsViewModel.class);

        binding = FragmentPhotoDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        textViewPhotoDetails = binding.textPhotoDetails;
        photoImage = binding.photoImage;
        bookmarkImageButton = binding.bookmarkButton;
        textViewBookmark = binding.textBookmark;


        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            currentPhotoId = bundle.getString(getString(R.string.chosen_photo_id_bundle_key));
        }

        subscribeObservers();

        this.photoDetailsViewModel.fetchPhotoDetailsByPhotoId(currentPhotoId);

        addButtonsFunctionality();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void subscribeObservers() {
        this.photoDetailsViewModel.getPhotoDetails().observe(getViewLifecycleOwner(), new PhotoDetailsObserver());
        this.photoDetailsViewModel.isBookmarked().observe(getViewLifecycleOwner(), new BookmarkObserver());
    }

    private void addButtonsFunctionality() {
        bookmarkImageButton.setOnClickListener(new BookmarkButtonOnClickListener());
    }

    private class PhotoDetailsObserver implements Observer<PhotoDataImpl> {
        @Override
        public void onChanged(PhotoDataImpl photoData) {
            photoDetailsViewModel.fetchIsBookmarked(photoData.getId());

            textViewPhotoDetails.setText(photoDetailsViewModel.PrepareTextDetailsForPreview(
                    getString(R.string.photo_details_author_name_ticket),
                    getString(R.string.photo_details_original_width_ticket),
                    getString(R.string.photo_details_original_height_ticket),
                    getString(R.string.photo_details_photo_image_url_ticket),
                    photoData));

            Glide.with(photoImage.getContext())
                    .load(photoData.getDownload_url())
                    .into(photoImage);
        }
    }

    private class BookmarkObserver implements Observer<Boolean> {
        @Override
        public void onChanged(Boolean isBookmarked) {
            if (isBookmarked) {
                textViewBookmark.setText(getString(R.string.text_bookmark_remove_action));
                bookmarkImageButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_bookmark_button_fill));
            }
            else {
                textViewBookmark.setText(getString(R.string.text_bookmark_add_action));
                bookmarkImageButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_bookmark_button_stroke));
            }
        }
    }

    private class BookmarkButtonOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (photoDetailsViewModel.isBookmarked().getValue()) {
                photoDetailsViewModel.removeFromFavorites(photoDetailsViewModel.getPhotoDetails().getValue().getId());
            }
            else {
                photoDetailsViewModel.addToFavorites(photoDetailsViewModel.getPhotoDetails().getValue());
            }
        }
    }
}
