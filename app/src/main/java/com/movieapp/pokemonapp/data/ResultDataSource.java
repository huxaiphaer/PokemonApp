package com.movieapp.pokemonapp.data;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.movieapp.pokemonapp.data.remote.RetrofitClient;
import com.movieapp.pokemonapp.model.PokemonApiResponse;
import com.movieapp.pokemonapp.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class helps in pagination of the API.
 */
public class ResultDataSource extends PageKeyedDataSource<Integer, Result> {


    public static final int PAGE_LIMIT = 10;
    private static final int OFFSET = 10;


    /***
     * This method ensures that first batch of items to be loaded , if at all you are using
     * a paginated API.
     * @param params
     * @param callback
     */
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Result> callback) {

        RetrofitClient
                .getInstance()
                .getApi()
                .getPokemons(PAGE_LIMIT, OFFSET)
                .enqueue(new Callback<PokemonApiResponse>() {
                    @Override
                    public void onResponse(Call<PokemonApiResponse> call, Response<PokemonApiResponse> response) {

                        if (response.body() != null) {

                            List<Result> results = response.body().mResults;
                            callback.onResult(results, null, OFFSET + 10);
                        }
                    }

                    @Override
                    public void onFailure(Call<PokemonApiResponse> call, Throwable t) {

                    }
                });
    }

    /**
     * This method keeps track of the reverse scrolling (list scrolling), since the list
     * gets data from a paginated API.
     * @param params
     * @param callback
     */

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {

        RetrofitClient
                .getInstance()
                .getApi()
                .getPokemons(PAGE_LIMIT, params.key)
                .enqueue(new Callback<PokemonApiResponse>() {
                    @Override
                    public void onResponse(Call<PokemonApiResponse> call, Response<PokemonApiResponse> response) {

                        if (response.body() != null) {

                            Integer key = (params.key > 1) ? params.key - 10 : null;
                            callback.onResult(response.body().mResults, key);
                        }
                    }

                    @Override
                    public void onFailure(Call<PokemonApiResponse> call, Throwable t) {

                    }
                });

    }

    /***
     * This methods keeps on incrementing the offset of the API to
     * ensure that the items keep on loading form a paginated API.
     * @param params
     * @param callback
     */

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Result> callback) {


        RetrofitClient
                .getInstance()
                .getApi()
                .getPokemons(PAGE_LIMIT, params.key)
                .enqueue(new Callback<PokemonApiResponse>() {
                    @Override
                    public void onResponse(Call<PokemonApiResponse> call, Response<PokemonApiResponse> response) {

                        if (response.body() != null) {

                            Integer key = response.body().mCount > params.key ? params.key + 10 : null;
                            callback.onResult(response.body().mResults, key);
                        }
                    }

                    @Override
                    public void onFailure(Call<PokemonApiResponse> call, Throwable t) {

                    }
                });


    }
}
