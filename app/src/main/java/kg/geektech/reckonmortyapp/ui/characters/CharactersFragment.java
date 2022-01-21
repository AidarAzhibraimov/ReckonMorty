package kg.geektech.reckonmortyapp.ui.characters;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import kg.geektech.reckonmortyapp.Base.BaseFragment;
import kg.geektech.reckonmortyapp.R;
import kg.geektech.reckonmortyapp.common.Resource;
import kg.geektech.reckonmortyapp.data.models.MainResponse;
import kg.geektech.reckonmortyapp.databinding.FragmentCharactersBinding;


public class CharactersFragment extends BaseFragment<FragmentCharactersBinding> {
    private CharactersViewModel viewModel;
    private CharactersAdapter adapter;

    @Override
    protected FragmentCharactersBinding bind() {
        return FragmentCharactersBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new  ViewModelProvider(requireActivity()).get(CharactersViewModel.class);
        adapter = new CharactersAdapter();
    }

    @Override
    protected void setupObservers() {
        viewModel.charactersLiveData.observe(getViewLifecycleOwner(), new Observer<Resource<MainResponse>>() {
            @Override
            public void onChanged(Resource<MainResponse> resource) {
                switch (resource.status){
                    case ERROR:{
                        viewBinding.progress.setVisibility(View.GONE);
                        viewBinding.rvCharacter.setVisibility(View.GONE);
                        Snackbar.make(viewBinding.getRoot(),resource.msg, BaseTransientBottomBar.LENGTH_SHORT);
                        Log.e("TAG","onChanged" + resource.msg);
                        break;
                    }
                    case LOADING:{
                        viewBinding.progress.setVisibility(View.VISIBLE);
                        viewBinding.rvCharacter.setVisibility(View.GONE);
                        break;
                    }
                    case SUCCESS:{
                        viewBinding.progress.setVisibility(View.GONE);
                        viewBinding.rvCharacter.setVisibility(View.VISIBLE);
                        adapter.setCharacters(resource.data.getResults());
                        break;
                    }

                }
            }
        });
    }

    @Override
    protected void setupListeners() {

    }

    @Override
    protected void setupViews() {

        viewBinding.rvCharacter.setAdapter(adapter);
    }

    protected void callRequest() {
        viewModel.getCharacters();
    }
}