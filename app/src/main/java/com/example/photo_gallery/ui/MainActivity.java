package com.example.photo_gallery.ui;

import android.os.Bundle;

import com.example.photo_gallery.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.IdRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.photo_gallery.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    public  void replaceFragment(@IdRes int containerViewId, @NotNull Fragment newFragment, @Nullable String newFragmentTag, @Nullable String oldFragmentTag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerViewId, newFragment, newFragmentTag)
                .addToBackStack(oldFragmentTag)
                .commit();
    }
}