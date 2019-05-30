package com.movieapp.pokemonapp.model;

import com.google.gson.annotations.SerializedName;

public class Pokemon {

    @SerializedName("is_hidden")
    public Boolean mIsHidden;
    @SerializedName("name")
    public String mName;
    @SerializedName("pokemon")
    public Pokemon mPokemon;
    @SerializedName("slot")
    public Long mSlot;
    @SerializedName("url")
    public String mUrl;

}
