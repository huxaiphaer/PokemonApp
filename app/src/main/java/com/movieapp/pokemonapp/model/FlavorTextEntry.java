package com.movieapp.pokemonapp.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class FlavorTextEntry {

    @SerializedName("flavor_text")
    public String mFlavorText;
    @SerializedName("language")
    public Language mLanguage;
    @SerializedName("version_group")
    public VersionGroup mVersionGroup;

}
