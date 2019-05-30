package com.movieapp.pokemonapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailsModal {
    @SerializedName("effect_entries")
    public List<EffectEntry> mEffectEntries;
}
