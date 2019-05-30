package com.movieapp.pokemonapp.data.remote;

import com.movieapp.pokemonapp.model.DetailsModal;
import com.movieapp.pokemonapp.model.PokemonApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    /**
     * This is the API call to a specific endpoint.
     * @param limit This is the limit of the page.
     * @param offset This is the offset.
     * @return
     */
    @GET("/api/v2/pokemon")
    Call<PokemonApiResponse> getPokemons(@Query("limit") int limit,
                                         @Query("offset") int offset);

    @GET("/api/v2/ability/{id}")
    Call<DetailsModal> getSingelPokemon(@Path("id") int id);
}
