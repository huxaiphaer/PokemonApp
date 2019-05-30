package com.movieapp.pokemonapp.model;

import com.google.gson.annotations.SerializedName;

public class Name {

    @SerializedName("language")
    public Language mLanguage;
    @SerializedName("name")
    public String mName;

}
