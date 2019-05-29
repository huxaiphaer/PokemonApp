
package com.movieapp.pokemonapp.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PokemonApiResponse {

    @SerializedName("count")
    public Long mCount;
    @SerializedName("results")
    public List<Result> mResults;

}
