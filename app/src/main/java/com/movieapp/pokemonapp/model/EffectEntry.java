
package com.movieapp.pokemonapp.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class EffectEntry {

    @SerializedName("effect")
    public String mEffect;
    @SerializedName("language")
    public Language mLanguage;
    @SerializedName("short_effect")
    public String mShortEffect;

}
