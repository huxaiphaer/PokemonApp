package com.movieapp.pokemonapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailsModal {

    @SerializedName("effect_changes")
    public List<Object> mEffectChanges;
    @SerializedName("effect_entries")
    public List<EffectEntry> mEffectEntries;
    @SerializedName("flavor_text_entries")
    public List<FlavorTextEntry> mFlavorTextEntries;
    @SerializedName("generation")
    public Generation mGeneration;
    @SerializedName("id")
    public Long mId;
    @SerializedName("is_main_series")
    public Boolean mIsMainSeries;
    @SerializedName("name")
    public String mName;
    @SerializedName("names")
    public List<Name> mNames;
    @SerializedName("pokemon")
    public List<Pokemon> mPokemon;
}
