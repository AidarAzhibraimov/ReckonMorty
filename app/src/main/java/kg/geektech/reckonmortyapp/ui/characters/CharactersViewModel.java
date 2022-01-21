package kg.geektech.reckonmortyapp.ui.characters;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import kg.geektech.reckonmortyapp.App;
import kg.geektech.reckonmortyapp.common.Resource;
import kg.geektech.reckonmortyapp.data.models.MainResponse;

public class CharactersViewModel extends ViewModel {

    public LiveData<Resource<MainResponse>> charactersLiveData;

    public void getCharacters(){
        charactersLiveData = App.repository.getCharacters();
    }



}
