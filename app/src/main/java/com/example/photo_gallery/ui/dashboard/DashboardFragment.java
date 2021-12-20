package com.example.photo_gallery.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photo_gallery.R;
import com.example.photo_gallery.data.models.photo.Photo;
import com.example.photo_gallery.data.models.photo.PhotoImpl;
import com.example.photo_gallery.databinding.FragmentDashboardBinding;
import com.example.photo_gallery.ui.MainActivity;
import com.example.photo_gallery.ui.photoDetails.PhotoDetailsFragment;
import com.example.photo_gallery.ui.shared.viewModels.PhotoViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private PhotoViewModel photoViewModel;
    private FragmentDashboardBinding binding;
    private PhotoListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        photoViewModel =
                new ViewModelProvider(this).get(PhotoViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        subscribeObservers();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void subscribeObservers() {
        photoViewModel.getAllPhotos().observe(getViewLifecycleOwner(), new PhotoListObserver());
    }

    private class PhotoListObserver implements Observer<List<PhotoImpl>> {
        @Override
        public void onChanged(List<PhotoImpl> photos) {
            ArrayList<Photo> photoArrayList = new ArrayList<>();
            photoArrayList.addAll(photos);

            // set up the RecyclerView
            RecyclerView recyclerView = binding.getRoot().findViewById(R.id.photos_list);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
            adapter = new PhotoListAdapter(getContext(), photoArrayList);
            adapter.setClickListener(new PhotoListClickListener());
            recyclerView.setAdapter(adapter);
        }
    }

    private class PhotoListClickListener implements PhotoListAdapter.ItemClickListener {
        @Override
        public void onItemClick(View view, int position) {
            Photo chosenPhoto = adapter.getItem(position);
            PhotoDetailsFragment fragment = new PhotoDetailsFragment();
            Bundle bundle = new Bundle();
            bundle.putString(fragment.CHOSEN_PHOTO_ID_BUNDLE_KEY, chosenPhoto.getId());
            fragment.setArguments(bundle);

            ((MainActivity) getActivity()).replaceFragment(R.id.container,
                    fragment,
                    PhotoDetailsFragment.class.getClass().getName(),
                    DashboardFragment.class.getClass().getName());
        }
    }
}