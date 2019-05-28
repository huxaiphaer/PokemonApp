package com.movieapp.pokemonapp.data.remote;

import com.movieapp.pokemonapp.model.PokemonApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("/api/v2/pokemon/")
    Call<PokemonApiResponse> getPokemons(@Query("limit") int limit,
                                         @Query("offset") int Offset);

}
