package com.movieapp.pokemonapp.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.movieapp.pokemonapp.data.repository.DetailsRepository;
import com.movieapp.pokemonapp.model.DetailsModal;

public class DetailViewModal extends ViewModel {

    MutableLiveData<DetailsModal> mutableLiveData;
    private DetailsRepository detailsRepository;


    public LiveData<DetailsModal> getDetailsLiveData(int id) {

        if (mutableLiveData == null) {

            mutableLiveData = new MutableLiveData<>();
            //Loading data asynchronously from the server top this method.
            loadDetails(id);
        }

        return mutableLiveData;
    }


    private void loadDetails(int id) {

        detailsRepository = new DetailsRepository();
        mutableLiveData = detailsRepository.getPokemonDetails(id);
    }
}
