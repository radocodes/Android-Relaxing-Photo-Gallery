package com.example.photo_gallery.data.repositories.photo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.photo_gallery.data.models.photo.PhotoImpl;
import com.example.photo_gallery.data.network.PhotoWebApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotoRepositoryImpl implements PhotoRepository {

    private final String PHOTO_WEB_API_BASE_URL = "https://picsum.photos";
    private Retrofit retrofit;

    private MutableLiveData<List<PhotoImpl>> allPhotosResult;
    private MutableLiveData<PhotoImpl> singlePhotoResult;

    public PhotoRepositoryImpl() {
        this.retrofit = createRetrofitInstance();
        allPhotosResult = new MutableLiveData<>();
        singlePhotoResult = new MutableLiveData<>();
    }

    @Override
    public void fetchAll() {
        getPhotoWebApiClientImpl().getAll().enqueue(new Callback<List<PhotoImpl>>() {
            @Override
            public void onResponse(Call<List<PhotoImpl>> call, Response<List<PhotoImpl>> response) {
                if (response.isSuccessful()) {
                    allPhotosResult.postValue(response.body());
                }else {
                    allPhotosResult.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<PhotoImpl>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public MutableLiveData<List<PhotoImpl>> getAll() {
        return allPhotosResult;
    }

    @Override
    public LiveData<PhotoImpl> getPhotoById(String id) {
        getPhotoWebApiClientImpl().getPhotoById(id).enqueue(new Callback<PhotoImpl>() {
            @Override
            public void onResponse(Call<PhotoImpl> call, Response<PhotoImpl> response) {

                if (response.isSuccessful()) {
                    singlePhotoResult.postValue(response.body());
                }else {
                    singlePhotoResult.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<PhotoImpl> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return singlePhotoResult;
    }

    private Retrofit createRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(PHOTO_WEB_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private PhotoWebApiClient getPhotoWebApiClientImpl(){
        return this.retrofit.create(PhotoWebApiClient.class);
    }
}
