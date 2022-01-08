package com.example.photo_gallery.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photo_gallery.R;
import com.example.photo_gallery.data.models.photo.Photo;
import com.example.photo_gallery.data.models.photo.PhotoImpl;
import com.example.photo_gallery.databinding.FragmentDashboardBinding;
import com.example.photo_gallery.ui.shared.viewModels.PhotoViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class DashboardFragment extends Fragment {

    private PhotoViewModel photoViewModel;
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
        photoViewModel.fetchAllPhotos();
        addButtonsFunctionality();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void addButtonsFunctionality() {
        Button ascendingFilter = (Button) binding.getRoot().findViewById(R.id.button_ascending_order);
        ascendingFilter.setOnClickListener(new AscendingFilterButtonClickListener());

        Button descendingFilter = (Button) binding.getRoot().findViewById(R.id.button_descending_order);
        descendingFilter.setOnClickListener(new DescendingFilterButtonClickListener());
    }

    private void subscribeObservers() {
        photoViewModel.getAllPhotos().observe(getViewLifecycleOwner(), new PhotoListObserver());
    }

    private class PhotoListObserver implements Observer<List<PhotoImpl>> {
        @Override
        public void onChanged(List<PhotoImpl> photos) {
            ArrayList<Photo> photoArrayList = new ArrayList<>();
            photoArrayList.addAll(photos);

            adapter = new PhotoListAdapter(getContext(), photoArrayList);
            adapter.setClickListener(new PhotoListClickListener());
            recyclerView.setAdapter(adapter);
        }
    }

    private class PhotoListClickListener implements PhotoListAdapter.ItemClickListener {
        @Override
        public void onItemClick(View view, int position) {
            Photo chosenPhoto = adapter.getItem(position);

            Bundle bundle = new Bundle();
            bundle.putString(getString(R.string.chosen_photo_id_bundle_key), chosenPhoto.getId());

            Navigation.findNavController(view).navigate(R.id.PhotoDetailsAction, bundle);
        }
    }

    private class AscendingFilterButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            List<PhotoImpl> allPhotos = photoViewModel.getAllPhotos().getValue();

            if (allPhotos != null) {
                photoViewModel.SortPhotosAscending(allPhotos);
                photoViewModel.getAllPhotos().postValue(allPhotos);
            }
        }
    }

    private class DescendingFilterButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            List<PhotoImpl> allPhotos = photoViewModel.getAllPhotos().getValue();

            if (allPhotos != null) {
                photoViewModel.SortPhotosDescending(allPhotos);
                photoViewModel.getAllPhotos().postValue(allPhotos);
            }
        }
    }
}