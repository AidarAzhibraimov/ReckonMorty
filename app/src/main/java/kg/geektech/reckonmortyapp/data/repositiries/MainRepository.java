package kg.geektech.reckonmortyapp.data.repositiries;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import kg.geektech.reckonmortyapp.common.Resource;
import kg.geektech.reckonmortyapp.data.models.MainResponse;
import kg.geektech.reckonmortyapp.data.remote.RickAndMortyApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    private RickAndMortyApi api;

    public MainRepository(RickAndMortyApi api) {
        this.api = api;
    }

    public MutableLiveData<Resource<MainResponse>> getCharacters(){
        MutableLiveData<Resource<MainResponse>> liveData = new MutableLiveData<>();
        liveData.setValue(Resource.loading());
        api.getCharacters().enqueue(new Callback<MainResponse>() {
            @Override
            public void onResponse(Call<MainResponse> call, Response<MainResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                liveData.setValue(Resource.success(response.body()));
            }else {
                liveData.setValue(Resource.error(Resource.loading().msg, null));
            }
        }
            @Override
            public void onFailure(Call<MainResponse> call, Throwable t) {
                liveData.setValue(Resource.error(t.getLocalizedMessage(),null));
            }
        });
        return liveData;
    }

}
