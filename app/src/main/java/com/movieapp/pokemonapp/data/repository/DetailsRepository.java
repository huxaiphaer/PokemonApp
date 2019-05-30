package com.movieapp.pokemonapp.data.repository;

import android.arch.lifecycle.MutableLiveData;

import com.movieapp.pokemonapp.data.remote.RetrofitClient;
import com.movieapp.pokemonapp.model.DetailsModal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsRepository {

    public MutableLiveData<DetailsModal> getPokemonDetails(int id) {

        MutableLiveData<DetailsModal> pokemonData = new MutableLiveData<>();

        RetrofitClient.getInstance()
                .getApi()
                .getSingelPokemon(id)
                .enqueue(new Callback<DetailsModal>() {
                    @Override
                    public void onResponse(Call<DetailsModal> call, Response<DetailsModal> response) {


                        if (response.isSuccessful()) {

                            pokemonData.setValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<DetailsModal> call, Throwable t) {
                        pokemonData.setValue(null);
                    }
                });

        return pokemonData;

    }
}
