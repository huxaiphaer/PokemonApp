package com.movieapp.pokemonapp.data;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.movieapp.pokemonapp.data.remote.RetrofitClient;
import com.movieapp.pokemonapp.model.PokemonApiResponse;
import com.movieapp.pokemonapp.model.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultDataSource extends PageKeyedDataSource<Integer, Result> {


    public static final int PAGE_LIMIT = 10;
    private static final int OFFSET = 10;


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Result> callback) {

        RetrofitClient
                .getmInstance()
                .getApi()
                .getPokemons(PAGE_LIMIT, OFFSET)
                .enqueue(new Callback<PokemonApiResponse>() {
                    @Override
                    public void onResponse(Call<PokemonApiResponse> call, Response<PokemonApiResponse> response) {

                        if (response.body() != null) {

                            callback.onResult(response.body().getResults(), null, OFFSET + 10);
                        }
                    }

                    @Override
                    public void onFailure(Call<PokemonApiResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {

        RetrofitClient
                .getmInstance()
                .getApi()
                .getPokemons(PAGE_LIMIT, params.key)
                .enqueue(new Callback<PokemonApiResponse>() {
                    @Override
                    public void onResponse(Call<PokemonApiResponse> call, Response<PokemonApiResponse> response) {



                        if (response.body() != null) {

                            Integer key = (params.key > 1) ? params.key - 10 : null;
                            callback.onResult(response.body().getResults(), key);
                        }

                    }

                    @Override
                    public void onFailure(Call<PokemonApiResponse> call, Throwable t) {

                    }
                });

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {


        RetrofitClient
                .getmInstance()
                .getApi()
                .getPokemons(PAGE_LIMIT, params.key)
                .enqueue(new Callback<PokemonApiResponse>() {
                    @Override
                    public void onResponse(Call<PokemonApiResponse> call, Response<PokemonApiResponse> response) {



                        if (response.body() != null) {

                            Integer key = response.body().getCount() > params.key ? params.key + 10 : null;
                            callback.onResult(response.body().getResults(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<PokemonApiResponse> call, Throwable t) {

                    }
                });


    }
}
