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
import com.example.photo_gallery.data.models.photo.PhotoImpl;
import com.example.photo_gallery.databinding.FragmentPhotoDetailsBinding;
import com.example.photo_gallery.ui.shared.viewModels.PhotoViewModel;

import org.jetbrains.annotations.NotNull;

public class PhotoDetailsFragment extends Fragment {

    private String currentPhotoId;
    private PhotoViewModel photoViewModel;
    private FragmentPhotoDetailsBinding binding;

    private TextView authorName;
    private TextView originalWidth;
    private TextView originalHeight;
    private TextView photoImageUrl;
    private ImageView photoImage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        photoViewModel =
                new ViewModelProvider(this).get(PhotoViewModel.class);

        binding = FragmentPhotoDetailsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        authorName = binding.authorName;
        originalWidth = binding.originalWidth;
        originalHeight = binding.originalHeight;
        photoImageUrl = binding.photoImageUrl;
        photoImage = binding.photoImage;

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
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void subscribeObservers() {
        photoViewModel.getPhotoById(currentPhotoId).observe(getViewLifecycleOwner(), new PhotoDetailsObserver());
    }

    private class PhotoDetailsObserver implements Observer<PhotoImpl> {
        @Override
        public void onChanged(PhotoImpl photo) {
            String singleWhiteSpace = " ";

            authorName.setText(getString(R.string.photo_details_author_name_ticket) + singleWhiteSpace + photo.getAuthor());
            originalWidth.setText(getString(R.string.photo_details_original_width_ticket) + singleWhiteSpace + String.valueOf(photo.getWidth()));
            originalHeight.setText(getString(R.string.photo_details_original_height_ticket) + singleWhiteSpace + String.valueOf(photo.getHeight()));
            photoImageUrl.setText(getString(R.string.photo_details_photo_image_url_ticket) + singleWhiteSpace + photo.getDownload_url());

            Glide.with(photoImage.getContext())
                    .load(photo.getDownload_url())
                    .into(photoImage);
        }
    }
}
