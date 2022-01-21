package kg.geektech.reckonmortyapp;

import android.app.Application;

import kg.geektech.reckonmortyapp.data.remote.RetrofitClient;
import kg.geektech.reckonmortyapp.data.remote.RickAndMortyApi;
import kg.geektech.reckonmortyapp.data.repositiries.MainRepository;

public class App extends Application {

    private RetrofitClient client;
    private RickAndMortyApi api;
    public static MainRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        client = new RetrofitClient();
        api = client.provideApi();
        repository = new MainRepository(api);

    }
}
