package com.movieapp.pokemonapp.model;

import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("name")
    public String mName;
    @SerializedName("url")
    public String mUrl;

    /**
     * This method helps to get the ID of each pokemon.
     *
     * @return
     */
    public int getId() {

        String[] urlPattern = mUrl.split("/");
        return Integer.parseInt(urlPattern[urlPattern.length - 1]);
    }
}
