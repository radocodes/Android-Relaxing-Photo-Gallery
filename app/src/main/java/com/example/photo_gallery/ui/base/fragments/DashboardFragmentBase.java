package com.example.photo_gallery.ui.base.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photo_gallery.R;
import com.example.photo_gallery.data.models.photo.PhotoData;
import com.example.photo_gallery.data.models.photo.PhotoDataImpl;
import com.example.photo_gallery.databinding.FragmentDashboardBinding;
import com.example.photo_gallery.ui.dashboard.PhotoListAdapter;
import com.example.photo_gallery.ui.shared.viewModels.PhotoViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DashboardFragmentBase extends Fragment {

    protected PhotoViewModel photoViewModel;
    private FragmentDashboardBinding binding;
    private PhotoListAdapter adapter;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // set up the RecyclerView
        recyclerView = binding.getRoot().findViewById(R.id.photos_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        photoViewModel = new ViewModelProvider(this).get(PhotoViewModel.class);

        subscribeObservers();

        loadPhotoCollection();

        addButtonsFunctionality();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * You can write code to load different photo collection in this method according your needs,
     * when you override this method through class inheritance
     */
    protected void loadPhotoCollection() {

    }

    private void addButtonsFunctionality() {
        Button ascendingFilterButton = (Button) binding.getRoot().findViewById(R.id.button_ascending_order);
        ascendingFilterButton.setOnClickListener(new com.example.photo_gallery.ui.base.fragments.DashboardFragmentBase.AscendingFilterButtonClickListener());

        Button descendingFilterButton = (Button) binding.getRoot().findViewById(R.id.button_descending_order);
        descendingFilterButton.setOnClickListener(new com.example.photo_gallery.ui.base.fragments.DashboardFragmentBase.DescendingFilterButtonClickListener());

        Button PhotoSearchButton = (Button) binding.getRoot().findViewById(R.id.button_search);
        PhotoSearchButton.setOnClickListener(new com.example.photo_gallery.ui.base.fragments.DashboardFragmentBase.SearchButtonClickListener());
    }

    private void subscribeObservers() {
        photoViewModel.getPhotoCollection().observe(getViewLifecycleOwner(), new com.example.photo_gallery.ui.base.fragments.DashboardFragmentBase.PhotoListObserver());
    }

    private class PhotoListObserver implements Observer<List<PhotoDataImpl>> {
        @Override
        public void onChanged(List<PhotoDataImpl> photos) {
            ArrayList<PhotoData> photoDataArrayList = new ArrayList<>();
            photoDataArrayList.addAll(photos);

            adapter = new PhotoListAdapter(getContext(), photoDataArrayList);
            adapter.setClickListener(new com.example.photo_gallery.ui.base.fragments.DashboardFragmentBase.PhotoListClickListener());
            recyclerView.setAdapter(adapter);
        }
    }

    private class PhotoListClickListener implements PhotoListAdapter.ItemClickListener {
        @Override
        public void onItemClick(View view, int position) {
            PhotoData chosenPhoto = adapter.getItem(position);

            Bundle bundle = new Bundle();
            bundle.putString(getString(R.string.chosen_photo_id_bundle_key), chosenPhoto.getId());

            Navigation.findNavController(view).navigate(R.id.PhotoDetailsAction, bundle);
        }
    }

    private class AscendingFilterButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            List<PhotoDataImpl> allPhotos = photoViewModel.getPhotoCollection().getValue();

            if (allPhotos != null) {
                photoViewModel.sortPhotosAscending(allPhotos);
                photoViewModel.getPhotoCollection().postValue(allPhotos);
            }
        }
    }

    private class DescendingFilterButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            List<PhotoDataImpl> allPhotos = photoViewModel.getPhotoCollection().getValue();

            if (allPhotos != null) {
                photoViewModel.sortPhotosDescending(allPhotos);
                photoViewModel.getPhotoCollection().postValue(allPhotos);
            }
        }
    }

    private class SearchButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(getString(R.string.search_alert_dialog_title));

            // Set up the input
            final EditText input = new EditText(getContext());
            builder.setView(input);

            // Set up the buttons
            builder.setPositiveButton(getString(R.string.search_alert_dialog_positive_button), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String searchText = input.getText().toString();
                    List<PhotoDataImpl> SearchResult = photoViewModel.filterPhotosByAuthorName(searchText);
                    photoViewModel.getPhotoCollection().postValue(SearchResult);
                }
            });
            builder.setNegativeButton(getString(R.string.search_alert_dialog_negative_button), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        }
    }
}
